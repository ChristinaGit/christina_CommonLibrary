package com.christina.common.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.christina.common.contract.Contracts;
import com.christina.common.view.ViewBinder;
import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.RxLifecycle;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.android.RxLifecycleAndroid;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;
import rx.Observable;
import rx.subjects.BehaviorSubject;

@Accessors(prefix = "_")
public abstract class ExtendedActivity extends AppCompatActivity
    implements ViewBinder, ObservableActivity, LifecycleProvider<ActivityEvent> {
    @Override
    @NonNull
    @CheckResult
    public final Observable<ActivityEvent> lifecycle() {
        return getLifecycleSubject().asObservable();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull final ActivityEvent event) {
        Contracts.requireNonNull(event, "event == null");

        return RxLifecycle.bindUntilEvent(getLifecycleSubject(), event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(getLifecycleSubject());
    }

    @Override
    public final void registerActivityListener(@NonNull final ActivityListener listener) {
        Contracts.requireNonNull(listener, "listener == null");

        if (listener instanceof ActivityLifecycleListener) {
            synchronized (_activityLifecycleListenersLock) {
                getActivityLifecycleListeners().add((ActivityLifecycleListener) listener);
            }
        }

        if (listener instanceof ActivityInstanceStateListener) {
            synchronized (_activityInstanceStateListenersLock) {
                getActivityInstanceStateListeners().add((ActivityInstanceStateListener) listener);
            }
        }

        if (listener instanceof ActivityResultListener) {
            synchronized (_activityResultListenersLock) {
                getActivityResultListeners().add((ActivityResultListener) listener);
            }
        }
    }

    @Override
    public final void unregisterActivityListener(@NonNull final ActivityListener listener) {
        Contracts.requireNonNull(listener, "listener == null");

        if (listener instanceof ActivityLifecycleListener) {
            synchronized (_activityLifecycleListenersLock) {
                getActivityLifecycleListeners().remove(listener);
            }
        }

        if (listener instanceof ActivityInstanceStateListener) {
            synchronized (_activityInstanceStateListenersLock) {
                getActivityInstanceStateListeners().remove(listener);
            }
        }

        if (listener instanceof ActivityResultListener) {
            synchronized (_activityResultListenersLock) {
                getActivityResultListeners().remove(listener);
            }
        }
    }

    @CallSuper
    @Override
    public void bindViews() {
        unbindViews();

        _unbinder = ButterKnife.bind(this);
    }

    @CallSuper
    @Override
    public void bindViews(@NonNull final View source) {
        Contracts.requireNonNull(source, "source == null");

        unbindViews();

        _unbinder = ButterKnife.bind(this, source);
    }

    @CallSuper
    @Override
    public void unbindViews() {
        final val unbinder = getUnbinder();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @CallSuper
    @Override
    protected void onActivityResult(
        final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        _notifyOnActivityResult(requestCode, resultCode, data);
    }

    @CallSuper
    @Override
    protected void onPause() {
        getLifecycleSubject().onNext(ActivityEvent.PAUSE);

        super.onPause();
    }

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();

        getLifecycleSubject().onNext(ActivityEvent.RESUME);
    }

    @CallSuper
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLifecycleSubject().onNext(ActivityEvent.CREATE);

        onInject();

        _notifyOnCreate(savedInstanceState);
    }

    @CallSuper
    @Override
    protected void onStart() {
        super.onStart();

        getLifecycleSubject().onNext(ActivityEvent.START);

        _notifyOnStart();
    }

    @CallSuper
    @Override
    protected void onStop() {
        getLifecycleSubject().onNext(ActivityEvent.STOP);

        super.onStop();

        _notifyOnStop();
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        getLifecycleSubject().onNext(ActivityEvent.DESTROY);

        super.onDestroy();

        onReleaseInject();

        _notifyOnDestroy();
    }

    @CallSuper
    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        _notifyOnSaveInstanceState(outState);
    }

    @CallSuper
    protected void onInject() {
    }

    @CallSuper
    protected void onReleaseInject() {
    }

    @CallSuper
    @Override
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        _notifyOnRestoreInstanceState(savedInstanceState);
    }

    @Getter(value = AccessLevel.PRIVATE)
    private final Collection<ActivityInstanceStateListener> _activityInstanceStateListeners =
        new ArrayList<>();

    @NonNull
    private final Object _activityInstanceStateListenersLock = new Object();

    @Getter(value = AccessLevel.PRIVATE)
    private final Collection<ActivityLifecycleListener> _activityLifecycleListeners =
        new ArrayList<>();

    @NonNull
    private final Object _activityLifecycleListenersLock = new Object();

    @Getter(value = AccessLevel.PRIVATE)
    private final Collection<ActivityResultListener> _activityResultListeners = new ArrayList<>();

    @NonNull
    private final Object _activityResultListenersLock = new Object();

    @Getter(AccessLevel.PRIVATE)
    @NonNull
    private final BehaviorSubject<ActivityEvent> _lifecycleSubject = BehaviorSubject.create();

    @Getter(AccessLevel.PROTECTED)
    @Nullable
    private Unbinder _unbinder;

    private void _notifyOnActivityResult(
        final int requestCode, final int resultCode, final Intent data) {
        synchronized (_activityResultListenersLock) {
            for (final val listener : getActivityResultListeners()) {
                listener.onActivityResultIntoActivity(requestCode, resultCode, data);
            }
        }
    }

    private void _notifyOnCreate(final @Nullable Bundle savedInstanceState) {
        synchronized (_activityLifecycleListenersLock) {
            for (final val listener : getActivityLifecycleListeners()) {
                listener.onActivityCreate();
            }
        }
        synchronized (_activityInstanceStateListenersLock) {
            for (final val listener : getActivityInstanceStateListeners()) {
                listener.onActivityCreate(savedInstanceState);
            }
        }
    }

    private void _notifyOnDestroy() {
        synchronized (_activityLifecycleListenersLock) {
            for (final val listener : getActivityLifecycleListeners()) {
                listener.onActivityDestroy();
            }
        }
    }

    private void _notifyOnRestoreInstanceState(final @Nullable Bundle savedInstanceState) {
        synchronized (_activityInstanceStateListenersLock) {
            for (final val listener : getActivityInstanceStateListeners()) {
                listener.onActivityRestoreInstanceState(savedInstanceState);
            }
        }
    }

    private void _notifyOnSaveInstanceState(final @Nullable Bundle outState) {
        synchronized (_activityInstanceStateListenersLock) {
            for (final val listener : getActivityInstanceStateListeners()) {
                listener.onActivitySaveInstanceState(outState);
            }
        }
    }

    private void _notifyOnStart() {
        synchronized (_activityLifecycleListenersLock) {
            for (final val listener : getActivityLifecycleListeners()) {
                listener.onActivityStart();
            }
        }
    }

    private void _notifyOnStop() {
        synchronized (_activityLifecycleListenersLock) {
            for (final val listener : getActivityLifecycleListeners()) {
                listener.onActivityStop();
            }
        }
    }
}
