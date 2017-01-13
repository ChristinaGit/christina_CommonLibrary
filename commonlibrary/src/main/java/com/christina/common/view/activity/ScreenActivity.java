package com.christina.common.view.activity;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.event.Events;
import com.christina.common.event.notice.ManagedNoticeEvent;
import com.christina.common.event.notice.NoticeEvent;
import com.christina.common.presentation.Screen;

import lombok.experimental.Accessors;

@Accessors(prefix = "_")
public abstract class ScreenActivity extends ExtendedActivity implements Screen {
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
    protected void onPause() {
        super.onPause();

        _screenDisappearEvent.rise();
    }

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();

        _screenAppearEvent.rise();
    }

    @CallSuper
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _screenCreateEvent.rise();
    }

    @CallSuper
    @Override
    protected void onDestroy() {
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
