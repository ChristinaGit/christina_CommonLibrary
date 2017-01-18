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
    Event<ActivityResultEventArgs> getActivityResultEvent();

    @NonNull
    Event<BundleEventArgs> getCreateEvent();

    @NonNull
    NoticeEvent getDestroyEvent();

    @NonNull
    NoticeEvent getPauseEvent();

    @NonNull
    Event<BundleEventArgs> getRestoreInstanceStateEvent();

    @NonNull
    NoticeEvent getResumeEvent();

    @NonNull
    Event<BundleEventArgs> getSaveInstanceStateEvent();

    @NonNull
    NoticeEvent getStartEvent();

    @NonNull
    NoticeEvent getStopEvent();
}
