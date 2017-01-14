package com.christina.common.view.observerable;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.christina.common.event.generic.Event;
import com.christina.common.event.notice.NoticeEvent;
import com.christina.common.view.observerable.eventArgs.ActivityResultEventArgs;
import com.christina.common.view.observerable.eventArgs.BundleEventArgs;

public interface ObservableActivity {
    @NonNull
    AppCompatActivity asActivity();

    @NonNull
    Event<BundleEventArgs> getActivityCreateEvent();

    @NonNull
    NoticeEvent getActivityDestroyEvent();

    @NonNull
    NoticeEvent getActivityPauseEvent();

    @NonNull
    Event<BundleEventArgs> getActivityRestoreInstanceStateEvent();

    @NonNull
    Event<ActivityResultEventArgs> getActivityResultIntoActivityEvent();

    @NonNull
    NoticeEvent getActivityResumeEvent();

    @NonNull
    Event<BundleEventArgs> getActivitySaveInstanceStateEvent();

    @NonNull
    NoticeEvent getActivityStartEvent();

    @NonNull
    NoticeEvent getActivityStopEvent();
}
