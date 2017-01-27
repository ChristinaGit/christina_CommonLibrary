package moe.christina.common.control.adviser;

import android.support.annotation.NonNull;

import moe.christina.common.event.notice.NoticeEvent;

public interface ResourceAdviser {
    @NonNull
    NoticeEvent getAcquireResourcesEvent();

    @NonNull
    NoticeEvent getReleaseResourcesEvent();
}
