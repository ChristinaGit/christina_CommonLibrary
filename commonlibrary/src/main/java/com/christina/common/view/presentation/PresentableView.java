package com.christina.common.view.presentation;

import android.support.annotation.NonNull;

import com.christina.common.event.NoticeEvent;

public interface PresentableView {
    @NonNull
    NoticeEvent getOnViewAppearEvent();

    @NonNull
    NoticeEvent getOnViewCreateEvent();

    @NonNull
    NoticeEvent getOnViewDestroyEvent();

    @NonNull
    NoticeEvent getOnViewDisappearEvent();
}
