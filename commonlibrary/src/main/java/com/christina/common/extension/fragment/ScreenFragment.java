package com.christina.common.extension.fragment;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.event.Events;
import com.christina.common.event.notice.ManagedNoticeEvent;
import com.christina.common.event.notice.NoticeEvent;
import com.christina.common.mvp.screen.Screen;

public abstract class ScreenFragment extends ExtendedFragment implements Screen {
    @NonNull
    @Override
    public final NoticeEvent getScreenAppearEvent() {
        return _screenAppearEvent;
    }

    @NonNull
    @Override
    public final NoticeEvent getScreenCreateEvent() {
        return _screenCreateEvent;
    }

    @NonNull
    @Override
    public final NoticeEvent getScreenDestroyEvent() {
        return _screenDestroyEvent;
    }

    @NonNull
    @Override
    public final NoticeEvent getScreenDisappearEvent() {
        return _screenDisappearEvent;
    }

    @CallSuper
    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _screenCreateEvent.rise();
    }

    @CallSuper
    @Override
    public void onResume() {
        super.onResume();

        _screenAppearEvent.rise();
    }

    @CallSuper
    @Override
    public void onPause() {
        super.onPause();

        _screenDisappearEvent.rise();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();

        _screenDestroyEvent.rise();
    }

    @NonNull
    private final ManagedNoticeEvent _screenAppearEvent = Events.createNoticeEvent();

    @NonNull
    private final ManagedNoticeEvent _screenCreateEvent = Events.createNoticeEvent();

    @NonNull
    private final ManagedNoticeEvent _screenDestroyEvent = Events.createNoticeEvent();

    @NonNull
    private final ManagedNoticeEvent _screenDisappearEvent = Events.createNoticeEvent();
}
