package com.christina.common.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.christina.common.contract.Contracts;
import com.christina.common.view.BindableViews;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;

@Accessors(prefix = "_")
public abstract class ExtendedActivity extends AppCompatActivity
    implements BindableViews, ObservableActivity {
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

    @Override
    public void bindViews() {
        unbindViews();

        _unbinder = ButterKnife.bind(this);
    }

    @Override
    public void bindViews(@NonNull final View source) {
        Contracts.requireNonNull(source, "source == null");

        unbindViews();

        _unbinder = ButterKnife.bind(this, source);
    }

    @Override
    public void unbindViews() {
        final val unbinder = getUnbinder();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    protected void onActivityResult(
        final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        _notifyOnActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onInject();

        _notifyOnCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        _notifyOnStart();
    }

    @Override
    protected void onStop() {
        super.onStop();

        _notifyOnStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        onReleaseInject();

        _notifyOnDestroy();
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        _notifyOnSaveInstanceState(outState);
    }

    protected void onInject() {
    }

    protected void onReleaseInject() {
    }

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
