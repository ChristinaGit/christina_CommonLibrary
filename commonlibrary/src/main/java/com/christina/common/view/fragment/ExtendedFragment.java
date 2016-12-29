package com.christina.common.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.christina.common.contract.Contracts;
import com.christina.common.view.ViewBinder;
import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.RxLifecycle;
import com.trello.rxlifecycle.android.FragmentEvent;
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
public abstract class ExtendedFragment extends Fragment
    implements ViewBinder, ObservableFragment, LifecycleProvider<FragmentEvent> {

    @Override
    @NonNull
    @CheckResult
    public final Observable<FragmentEvent> lifecycle() {
        return getLifecycleSubject().asObservable();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull final FragmentEvent event) {
        Contracts.requireNonNull(event, "event == null");

        return RxLifecycle.bindUntilEvent(getLifecycleSubject(), event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindFragment(getLifecycleSubject());
    }

    @Override
    public final void registerFragmentListener(@NonNull final FragmentListener listener) {
        Contracts.requireNonNull(listener, "listener == null");

        if (listener instanceof FragmentLifecycleListener) {
            synchronized (_fragmentLifecycleListenersLock) {
                getFragmentLifecycleListeners().add((FragmentLifecycleListener) listener);
            }
        }

        if (listener instanceof FragmentInstanceStateListener) {
            synchronized (_fragmentInstanceStateListenersLock) {
                getFragmentInstanceStateListeners().add((FragmentInstanceStateListener) listener);
            }
        }

        if (listener instanceof FragmentResultListener) {
            synchronized (_fragmentResultListenersLock) {
                getFragmentResultListeners().add((FragmentResultListener) listener);
            }
        }
    }

    @Override
    public final void unregisterFragmentListener(@NonNull final FragmentListener listener) {
        Contracts.requireNonNull(listener, "listener == null");

        if (listener instanceof FragmentLifecycleListener) {
            synchronized (_fragmentLifecycleListenersLock) {
                getFragmentLifecycleListeners().remove(listener);
            }
        }

        if (listener instanceof FragmentInstanceStateListener) {
            synchronized (_fragmentInstanceStateListenersLock) {
                getFragmentInstanceStateListeners().remove(listener);
            }
        }

        if (listener instanceof FragmentResultListener) {
            synchronized (_fragmentResultListenersLock) {
                getFragmentResultListeners().remove(listener);
            }
        }
    }

    @CallSuper
    @Override
    public void bindViews() {
        unbindViews();

        final val view = getView();
        if (view != null) {
            _unbinder = ButterKnife.bind(this, view);
        }
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
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        _notifyOnActivityResult(requestCode, resultCode, data);
    }

    @CallSuper
    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);

        getLifecycleSubject().onNext(FragmentEvent.ATTACH);
    }

    @CallSuper
    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLifecycleSubject().onNext(FragmentEvent.CREATE);
    }

    @CallSuper
    @Nullable
    @Override
    public View onCreateView(
        final LayoutInflater inflater,
        @Nullable final ViewGroup container,
        @Nullable final Bundle savedInstanceState) {
        final val view = super.onCreateView(inflater, container, savedInstanceState);

        onInject();

        _notifyOnCreateView(savedInstanceState);

        return view;
    }

    @CallSuper
    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getLifecycleSubject().onNext(FragmentEvent.CREATE_VIEW);
    }

    @CallSuper
    @Override
    public void onViewStateRestored(@Nullable final Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        _notifyOnViewStateRestored(savedInstanceState);
    }

    @CallSuper
    @Override
    public void onStart() {
        super.onStart();

        getLifecycleSubject().onNext(FragmentEvent.START);

        _notifyOnStart();
    }

    @CallSuper
    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        _notifyOnSaveInstanceState(outState);
    }

    @CallSuper
    @Override
    public void onPause() {
        getLifecycleSubject().onNext(FragmentEvent.PAUSE);

        super.onPause();
    }

    @CallSuper
    @Override
    public void onStop() {
        getLifecycleSubject().onNext(FragmentEvent.STOP);

        super.onStop();

        _notifyOnStop();
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        getLifecycleSubject().onNext(FragmentEvent.DESTROY_VIEW);

        super.onDestroyView();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        getLifecycleSubject().onNext(FragmentEvent.DESTROY);

        super.onDestroy();

        onReleaseInject();

        _notifyOnDestroy();
    }

    @CallSuper
    protected void onInject() {
    }

    @CallSuper
    protected void onReleaseInject() {
    }

    @Getter(value = AccessLevel.PRIVATE)
    private final Collection<FragmentInstanceStateListener> _fragmentInstanceStateListeners =
        new ArrayList<>();

    @NonNull
    private final Object _fragmentInstanceStateListenersLock = new Object();

    @Getter(value = AccessLevel.PRIVATE)
    private final Collection<FragmentLifecycleListener> _fragmentLifecycleListeners =
        new ArrayList<>();

    @NonNull
    private final Object _fragmentLifecycleListenersLock = new Object();

    @Getter(value = AccessLevel.PRIVATE)
    private final Collection<FragmentResultListener> _fragmentResultListeners = new ArrayList<>();

    @NonNull
    private final Object _fragmentResultListenersLock = new Object();

    @Getter(AccessLevel.PRIVATE)
    private final BehaviorSubject<FragmentEvent> _lifecycleSubject = BehaviorSubject.create();

    @Getter(AccessLevel.PROTECTED)
    @Nullable
    private Unbinder _unbinder;

    private void _notifyOnActivityResult(
        final int requestCode, final int resultCode, final Intent data) {
        synchronized (_fragmentResultListenersLock) {
            for (final val listener : getFragmentResultListeners()) {
                listener.onActivityResultIntoFragment(requestCode, resultCode, data);
            }
        }
    }

    private void _notifyOnCreateView(final @Nullable Bundle savedInstanceState) {
        synchronized (_fragmentLifecycleListenersLock) {
            for (final val listener : getFragmentLifecycleListeners()) {
                listener.onFragmentCreateView();
            }
        }
        synchronized (_fragmentInstanceStateListenersLock) {
            for (final val listener : getFragmentInstanceStateListeners()) {
                listener.onFragmentCreateView(savedInstanceState);
            }
        }
    }

    private void _notifyOnDestroy() {
        synchronized (_fragmentLifecycleListenersLock) {
            for (final val listener : getFragmentLifecycleListeners()) {
                listener.onFragmentDestroy();
            }
        }
    }

    private void _notifyOnSaveInstanceState(final @Nullable Bundle outState) {
        synchronized (_fragmentInstanceStateListenersLock) {
            for (final val listener : getFragmentInstanceStateListeners()) {
                listener.onFragmentSaveInstanceState(outState);
            }
        }
    }

    private void _notifyOnStart() {
        synchronized (_fragmentLifecycleListenersLock) {
            for (final val listener : getFragmentLifecycleListeners()) {
                listener.onFragmentStart();
            }
        }
    }

    private void _notifyOnStop() {
        synchronized (_fragmentLifecycleListenersLock) {
            for (final val listener : getFragmentLifecycleListeners()) {
                listener.onFragmentStop();
            }
        }
    }

    private void _notifyOnViewStateRestored(final @Nullable Bundle savedInstanceState) {
        synchronized (_fragmentInstanceStateListenersLock) {
            for (final val listener : getFragmentInstanceStateListeners()) {
                listener.onFragmentViewStateRestored(savedInstanceState);
            }
        }
    }
}
