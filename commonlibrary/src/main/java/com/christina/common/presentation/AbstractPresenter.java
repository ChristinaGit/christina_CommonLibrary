package com.christina.common.presentation;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.contract.Contracts;
import com.christina.common.event.notice.NoticeEventHandler;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;

@Accessors(prefix = "_")
public abstract class AbstractPresenter<TScreen extends Screen> implements Presenter<TScreen> {
    @Override
    public void bindScreen(@NonNull final TScreen screen) {
        Contracts.requireNonNull(screen, "screen == null");

        if (_screen != null) {
            throw new IllegalStateException("Presenter is already bound.");
        }

        onBindScreen(screen);

        _screen = screen;
    }

    @Override
    public final void unbindScreen() {
        if (_screen == null) {
            throw new IllegalStateException("Presenter is already unbound.");
        }

        onUnbindScreen(_screen);
    }

    @CallSuper
    protected void onBindScreen(@NonNull final TScreen screen) {
        Contracts.requireNonNull(screen, "screen == null");

        screen.getScreenCreateEvent().addHandler(getScreenCreateHandler());
        screen.getScreenAppearEvent().addHandler(getScreenAppearHandler());
        screen.getScreenDisappearEvent().addHandler(getScreenDisappearHandler());
        screen.getScreenDestroyEvent().addHandler(getScreenDestroyHandler());
    }

    @CallSuper
    protected void onScreenAppear(@NonNull final TScreen screen) {
        Contracts.requireNonNull(screen, "screen == null");
    }

    @CallSuper
    protected void onScreenCreate(@NonNull final TScreen screen) {
        Contracts.requireNonNull(screen, "screen == null");
    }

    @CallSuper
    protected void onScreenDestroy(@NonNull final TScreen screen) {
        Contracts.requireNonNull(screen, "screen == null");
    }

    @CallSuper
    protected void onScreenDisappear(@NonNull final TScreen screen) {
        Contracts.requireNonNull(screen, "screen == null");
    }

    @CallSuper
    protected void onUnbindScreen(@NonNull final TScreen screen) {
        Contracts.requireNonNull(screen, "screen == null");

        screen.getScreenCreateEvent().removeHandler(getScreenCreateHandler());
        screen.getScreenAppearEvent().removeHandler(getScreenAppearHandler());
        screen.getScreenDisappearEvent().removeHandler(getScreenDisappearHandler());
        screen.getScreenDestroyEvent().removeHandler(getScreenDestroyHandler());
    }

    @Getter(value = AccessLevel.PRIVATE)
    @NonNull
    private final NoticeEventHandler _screenAppearHandler = new NoticeEventHandler() {
        @Override
        public void onEvent() {
            final val screen = getScreen();

            if (screen != null) {
                onScreenAppear(screen);
            } else {
                throw new IllegalStateException("Screen is null in internal event.");
            }
        }
    };

    @Getter(value = AccessLevel.PRIVATE)
    @NonNull
    private final NoticeEventHandler _screenCreateHandler = new NoticeEventHandler() {
        @Override
        public void onEvent() {
            final val presentableView = getScreen();

            if (presentableView != null) {
                onScreenCreate(presentableView);
            } else {
                throw new IllegalStateException("Screen is null in internal event.");
            }
        }
    };

    @Getter(value = AccessLevel.PRIVATE)
    @NonNull
    private final NoticeEventHandler _screenDestroyHandler = new NoticeEventHandler() {
        @Override
        public void onEvent() {
            final val presentableView = getScreen();

            if (presentableView != null) {
                onScreenDestroy(presentableView);
            } else {
                throw new IllegalStateException("Screen is null in internal event.");
            }
        }
    };

    @Getter(value = AccessLevel.PRIVATE)
    @NonNull
    private final NoticeEventHandler _screenDisappearHandler = new NoticeEventHandler() {
        @Override
        public void onEvent() {
            final val presentableView = getScreen();

            if (presentableView != null) {
                onScreenDisappear(presentableView);
            } else {
                throw new IllegalStateException("Screen is null in internal event.");
            }
        }
    };

    @Getter(AccessLevel.PROTECTED)
    @Nullable
    private TScreen _screen;
}