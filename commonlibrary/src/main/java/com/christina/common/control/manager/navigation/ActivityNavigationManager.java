package com.christina.common.control.manager.navigation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.val;

import com.christina.common.contract.Contracts;
import com.christina.common.control.adviser.ResourceAdviser;
import com.christina.common.control.manager.ReleasableManager;
import com.christina.common.event.generic.EventHandler;
import com.christina.common.extension.activity.ObservableActivity;
import com.christina.common.extension.eventArgs.ActivityResultEventArgs;

@Accessors(prefix = "_")
public abstract class ActivityNavigationManager extends ReleasableManager {
    public ActivityNavigationManager(
        @NonNull final ResourceAdviser resourceAdviser,
        @NonNull final ObservableActivity observableActivity) {
        super(Contracts.requireNonNull(resourceAdviser, "resourceAdviser == null"));
        Contracts.requireNonNull(observableActivity, "observableActivity == null");

        _observableActivity = observableActivity;
    }

    @NonNull
    protected final AppCompatActivity getActivity() {
        return getObservableActivity().asActivity();
    }

    protected final void registerNavigationCallback(
        int requestCode, @NonNull final NavigationCallback callback) {
        Contracts.requireNonNull(callback, "callback == null");

        getNavigationCallbacks().append(requestCode, callback);
    }

    protected final void unregisterNavigationCallback(int requestCode) {
        getNavigationCallbacks().remove(requestCode);
    }

    @Override
    protected void onAcquireResources() {
        getObservableActivity().getActivityResultEvent().addHandler(_activityResultHandler);
    }

    @Override
    protected void onReleaseResources() {
        getObservableActivity().getActivityResultEvent().removeHandler(_activityResultHandler);
    }

    protected abstract void onActivityResult(
        final int requestCode,
        final int resultCode,
        @Nullable final Intent data,
        @Nullable final NavigationCallback callback);

    @Getter(value = AccessLevel.PRIVATE)
    @NonNull
    private final SparseArray<NavigationCallback> _navigationCallbacks = new SparseArray<>();

    @Getter(value = AccessLevel.PROTECTED)
    @NonNull
    private final ObservableActivity _observableActivity;

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    private boolean _autoReleaseCallbacks = false;

    @NonNull
    private final EventHandler<ActivityResultEventArgs> _activityResultHandler =
        new EventHandler<ActivityResultEventArgs>() {
            @Override
            public void onEvent(@NonNull final ActivityResultEventArgs eventArgs) {
                Contracts.requireNonNull(eventArgs, "eventArgs == null");

                final int requestCode = eventArgs.getRequestCode();
                final int resultCode = eventArgs.getResultCode();
                final val data = eventArgs.getData();

                final val callbacks = getNavigationCallbacks();
                final val callback = callbacks.get(requestCode);

                onActivityResult(requestCode, resultCode, data, callback);

                if (isAutoReleaseCallbacks()) {
                    unregisterNavigationCallback(requestCode);
                }
            }
        };
}
