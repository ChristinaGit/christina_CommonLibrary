package com.christina.common.adviser;

import android.support.annotation.NonNull;

import com.christina.common.event.notice.NoticeEvent;

public interface ResourceAdviser {
    @NonNull
    NoticeEvent getAcquireResourcesEvent();

    @NonNull
    NoticeEvent getReleaseResourcesEvent();
}
