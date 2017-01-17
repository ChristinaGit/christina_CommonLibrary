package com.christina.common.aware;

import android.support.annotation.NonNull;

import com.christina.common.event.notice.NoticeEvent;

public interface ResourceAware {
    @NonNull
    NoticeEvent getAcquireResourcesEvent();

    @NonNull
    NoticeEvent getReleaseResourcesEvent();
}
