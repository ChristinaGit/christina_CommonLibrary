package com.christina.common.view.observerable;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.christina.common.event.generic.Event;
import com.christina.common.event.notice.NoticeEvent;
import com.christina.common.view.observerable.eventArgs.ActivityResultEventArgs;
import com.christina.common.view.observerable.eventArgs.BundleEventArgs;

public interface ObservableFragment {
    Fragment asFragment();

    @NonNull
    Event<ActivityResultEventArgs> getActivityResultIntoFragmentEvent();

    @NonNull
    NoticeEvent getFragmentAttachEvent();

    @NonNull
    Event<BundleEventArgs> getFragmentCreateEvent();

    @NonNull
    Event<BundleEventArgs> getFragmentCreateViewEvent();

    @NonNull
    NoticeEvent getFragmentDestroyEvent();

    @NonNull
    NoticeEvent getFragmentDestroyViewEvent();

    @NonNull
    NoticeEvent getFragmentDetachEvent();

    @NonNull
    NoticeEvent getFragmentPauseEvent();

    @NonNull
    NoticeEvent getFragmentResumeEvent();

    @NonNull
    Event<BundleEventArgs> getFragmentSaveInstanceStateEvent();

    @NonNull
    NoticeEvent getFragmentStartEvent();

    @NonNull
    NoticeEvent getFragmentStopEvent();

    @NonNull
    Event<BundleEventArgs> getFragmentViewCreatedEvent();

    @NonNull
    Event<BundleEventArgs> getFragmentViewStateRestoredEvent();
}
