package com.christina.common.extension.fragment;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.christina.common.event.generic.Event;
import com.christina.common.event.notice.NoticeEvent;
import com.christina.common.extension.eventArgs.ActivityResultEventArgs;
import com.christina.common.extension.eventArgs.BundleEventArgs;

public interface ObservableFragment {
    @NonNull
    Fragment asFragment();

    @NonNull
    Event<ActivityResultEventArgs> getActivityResultEvent();

    @NonNull
    NoticeEvent getAttachEvent();

    @NonNull
    Event<BundleEventArgs> getCreateEvent();

    @NonNull
    Event<BundleEventArgs> getCreateViewEvent();

    @NonNull
    NoticeEvent getDestroyEvent();

    @NonNull
    NoticeEvent getDestroyViewEvent();

    @NonNull
    NoticeEvent getDetachEvent();

    @NonNull
    NoticeEvent getPauseEvent();

    @NonNull
    NoticeEvent getResumeEvent();

    @NonNull
    Event<BundleEventArgs> getSaveInstanceStateEvent();

    @NonNull
    NoticeEvent getStartEvent();

    @NonNull
    NoticeEvent getStopEvent();

    @NonNull
    Event<BundleEventArgs> getViewCreatedEvent();

    @NonNull
    Event<BundleEventArgs> getViewStateRestoredEvent();
}
