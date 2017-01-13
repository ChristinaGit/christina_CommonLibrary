package com.christina.common.view.observerable;

import android.support.annotation.NonNull;

import com.christina.common.event.generic.Event;
import com.christina.common.event.notice.NoticeEvent;
import com.christina.common.view.observerable.eventArgs.ActivityResultEventArgs;
import com.christina.common.view.observerable.eventArgs.BundleEventArgs;

public interface ObservableActivity {
    @NonNull
    Event<BundleEventArgs> getActivityCreateEvent();

    @NonNull
    NoticeEvent getActivityDestroyEvent();

    @NonNull
    NoticeEvent getActivityPauseEvent();

    @NonNull
    Event<BundleEventArgs> getActivityRestoreInstanceStateEvent();

    @NonNull
    NoticeEvent getActivityResumeEvent();

    @NonNull
    Event<BundleEventArgs> getActivitySaveInstanceStateEvent();

    @NonNull
    NoticeEvent getActivityStartEvent();

    @NonNull
    NoticeEvent getActivityStopEvent();

    @NonNull
    Event<ActivityResultEventArgs> onActivityResultIntoActivityEvent();
}
