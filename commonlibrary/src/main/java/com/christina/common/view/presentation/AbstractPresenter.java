package com.christina.common.view.presentation;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.event.NoticeEventHandler;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;

@Accessors(prefix = "_")
public abstract class AbstractPresenter<TPresentableView extends PresentableView>
    implements Presenter<TPresentableView> {
    @Nullable
    @Override
    public TPresentableView getPresentableView() {
        return _presentableView;
    }

    @CallSuper
    @Override
    public void setPresentableView(@Nullable final TPresentableView presentableView) {
        if (presentableView != _presentableView) {
            if (_presentableView != null) {
                onUnbindPresentableView(_presentableView);
            }

            _presentableView = presentableView;

            if (_presentableView != null) {
                onBindPresentableView(_presentableView);
            }
        }
    }

    @CallSuper
    protected void onBindPresentableView(@NonNull final TPresentableView presentableView) {
        presentableView.getOnViewCreateEvent().addHandler(getViewCreateHandler());
        presentableView.getOnViewAppearEvent().addHandler(getViewAppearHandler());
        presentableView.getOnViewDisappearEvent().addHandler(getViewDisappearHandler());
        presentableView.getOnViewDestroyEvent().addHandler(getViewDestroyHandler());
    }

    @CallSuper
    protected void onUnbindPresentableView(@NonNull final TPresentableView presentableView) {
        presentableView.getOnViewCreateEvent().removeHandler(getViewCreateHandler());
        presentableView.getOnViewAppearEvent().removeHandler(getViewAppearHandler());
        presentableView.getOnViewDisappearEvent().removeHandler(getViewDisappearHandler());
        presentableView.getOnViewDestroyEvent().removeHandler(getViewDestroyHandler());
    }

    @CallSuper
    protected void onViewAppear(@NonNull final TPresentableView presentableView) {
    }

    @CallSuper
    protected void onViewCreate(@NonNull final TPresentableView presentableView) {
    }

    @CallSuper
    protected void onViewDestroy(@NonNull final TPresentableView presentableView) {
    }

    @CallSuper
    protected void onViewDisappear(@NonNull final TPresentableView presentableView) {
    }

    @Nullable
    private TPresentableView _presentableView;

    @Getter(value = AccessLevel.PRIVATE, lazy = true)
    @NonNull
    private final NoticeEventHandler _viewCreateHandler = new NoticeEventHandler() {
        @Override
        public void onEvent() {
            final val presentableView = getPresentableView();

            if (presentableView != null) {
                onViewCreate(presentableView);
            } else {
                throw new IllegalStateException("Presentable view can not be in internal event.");
            }
        }
    };

    @Getter(value = AccessLevel.PRIVATE, lazy = true)
    @NonNull
    private final NoticeEventHandler _viewDestroyHandler = new NoticeEventHandler() {
        @Override
        public void onEvent() {
            final val presentableView = getPresentableView();

            if (presentableView != null) {
                onViewDestroy(presentableView);
            } else {
                throw new IllegalStateException("Presentable view can not be in internal event.");
            }
        }
    };

    @Getter(value = AccessLevel.PRIVATE, lazy = true)
    @NonNull
    private final NoticeEventHandler _viewDisappearHandler = new NoticeEventHandler() {
        @Override
        public void onEvent() {
            final val presentableView = getPresentableView();

            if (presentableView != null) {
                onViewDisappear(presentableView);
            } else {
                throw new IllegalStateException("Presentable view can not be in internal event.");
            }
        }
    };

    @Getter(value = AccessLevel.PRIVATE, lazy = true)
    @NonNull
    private final NoticeEventHandler _viewAppearHandler = new NoticeEventHandler() {
        @Override
        public void onEvent() {
            final val presentableView = getPresentableView();

            if (presentableView != null) {
                onViewAppear(presentableView);
            } else {
                throw new IllegalStateException("Presentable view can not be in internal event.");
            }
        }
    };
}
