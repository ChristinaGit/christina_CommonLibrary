package com.christina.common.view.presentation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.event.NoticeEventHandler;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
public abstract class AbstractPresenter<TPresentableView extends PresentableView>
    implements Presenter<TPresentableView> {
    @Nullable
    @Override
    public TPresentableView getPresentableView() {
        return _presentableView;
    }

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

    protected void onBindPresentableView(@NonNull final TPresentableView presentableView) {
        presentableView.getOnViewCreateEvent().addHandler(getViewCreateHandler());
        presentableView.getOnViewAppearEvent().addHandler(getViewAppearHandler());
        presentableView.getOnViewDisappearEvent().addHandler(getViewDisappearHandler());
        presentableView.getOnViewDestroyEvent().addHandler(getViewDestroyHandler());
    }

    protected void onUnbindPresentableView(@NonNull final TPresentableView presentableView) {
        presentableView.getOnViewCreateEvent().removeHandler(getViewCreateHandler());
        presentableView.getOnViewAppearEvent().removeHandler(getViewAppearHandler());
        presentableView.getOnViewDisappearEvent().removeHandler(getViewDisappearHandler());
        presentableView.getOnViewDestroyEvent().removeHandler(getViewDestroyHandler());
    }

    protected void onViewAppear() {
    }

    protected void onViewCreate() {
    }

    protected void onViewDestroy() {
    }

    protected void onViewDisappear() {
    }

    @Getter(value = AccessLevel.PRIVATE, lazy = true)
    @NonNull
    private final NoticeEventHandler _viewAppearHandler = new NoticeEventHandler() {
        @Override
        public void onEvent() {
            onViewAppear();
        }
    };

    @Getter(value = AccessLevel.PRIVATE, lazy = true)
    @NonNull
    private final NoticeEventHandler _viewCreateHandler = new NoticeEventHandler() {
        @Override
        public void onEvent() {
            onViewCreate();
        }
    };

    @Getter(value = AccessLevel.PRIVATE, lazy = true)
    @NonNull
    private final NoticeEventHandler _viewDestroyHandler = new NoticeEventHandler() {
        @Override
        public void onEvent() {
            onViewDestroy();
        }
    };

    @Getter(value = AccessLevel.PRIVATE, lazy = true)
    @NonNull
    private final NoticeEventHandler _viewDisappearHandler = new NoticeEventHandler() {
        @Override
        public void onEvent() {
            onViewDisappear();
        }
    };

    @Nullable
    private TPresentableView _presentableView;
}
