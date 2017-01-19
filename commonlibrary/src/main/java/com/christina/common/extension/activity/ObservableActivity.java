package com.christina.common.extension.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.christina.common.event.generic.Event;
import com.christina.common.event.notice.NoticeEvent;
import com.christina.common.extension.eventArgs.ActivityResultEventArgs;
import com.christina.common.extension.eventArgs.BundleEventArgs;

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
