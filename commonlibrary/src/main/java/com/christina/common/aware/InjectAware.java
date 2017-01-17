package com.christina.common.aware;

import android.support.annotation.NonNull;

import com.christina.common.event.notice.NoticeEvent;

public interface InjectAware {
    @NonNull
    NoticeEvent getInjectMembersEvent();

    @NonNull
    NoticeEvent getReleaseInjectedMembersEvent();
}
