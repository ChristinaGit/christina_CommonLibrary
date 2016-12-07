package com.christina.common.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public abstract class ExtendedFragment extends Fragment
    implements BindableViews, ObservableFragment {
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

    @Override
    public void bindViews() {
        unbindViews();

        final val view = getView();
        if (view != null) {
            _unbinder = ButterKnife.bind(this, view);
        }
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
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        _notifyOnActivityResult(requestCode, resultCode, data);
    }

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

    @Override
    public void onViewStateRestored(@Nullable final Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        _notifyOnViewStateRestored(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        _notifyOnStart();
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        _notifyOnSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();

        _notifyOnStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        onReleaseInject();

        _notifyOnDestroy();
    }

    protected void onInject() {
    }

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
