package moe.christina.common.mvp.screen;

import android.support.annotation.NonNull;

import moe.christina.common.event.notice.NoticeEvent;

public interface Screen {
    @NonNull
    NoticeEvent getScreenAppearEvent();

    @NonNull
    NoticeEvent getScreenCreateEvent();

    @NonNull
    NoticeEvent getScreenDestroyEvent();

    @NonNull
    NoticeEvent getScreenDisappearEvent();
}
