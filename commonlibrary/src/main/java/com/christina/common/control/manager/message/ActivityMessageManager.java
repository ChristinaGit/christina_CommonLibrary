package com.christina.common.control.manager.message;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;

import com.christina.common.contract.Contracts;
import com.christina.common.extension.activity.ObservableActivity;

@Accessors(prefix = "_")
public class ActivityMessageManager implements MessageManager {
    public ActivityMessageManager(
        @NonNull final ObservableActivity observableActivity,
        @IdRes final int contentContainerViewId) {
        Contracts.requireNonNull(observableActivity, "observableActivity == null");

        _observableActivity = observableActivity;
        _contentContainerViewId = contentContainerViewId;
    }

    @Override
    public void showInfoMessage(@NonNull final CharSequence message) {
        Contracts.requireNonNull(message, "message == null");

        final val contentView = getCoordinatorView();
        if (contentView != null) {
            Snackbar.make(contentView, message, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showInfoMessage(@StringRes final int messageId) {
        final val contentView = getCoordinatorView();
        if (contentView != null) {
            Snackbar.make(contentView, messageId, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showNotificationMessage(@NonNull final CharSequence message) {
        Contracts.requireNonNull(message, "message == null");

        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNotificationMessage(@StringRes final int messageId) {
        Toast.makeText(getActivity(), messageId, Toast.LENGTH_SHORT).show();
    }

    @NonNull
    protected final AppCompatActivity getActivity() {
        return getObservableActivity().asActivity();
    }

    @Nullable
    protected final View getCoordinatorView() {
        if (_contentContainerView == null) {
            _contentContainerView = getActivity().findViewById(getContentContainerViewId());
        }

        return _contentContainerView;
    }

    @IdRes
    @Getter(AccessLevel.PROTECTED)
    private final int _contentContainerViewId;

    @Getter(AccessLevel.PROTECTED)
    @NonNull
    private final ObservableActivity _observableActivity;

    @Nullable
    private View _contentContainerView;
}
