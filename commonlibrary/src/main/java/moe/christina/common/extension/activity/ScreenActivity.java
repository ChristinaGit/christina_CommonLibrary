package moe.christina.common.extension.activity;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import lombok.experimental.Accessors;

import moe.christina.common.event.Events;
import moe.christina.common.event.notice.ManagedNoticeEvent;
import moe.christina.common.event.notice.NoticeEvent;
import moe.christina.common.mvp.screen.Screen;

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
