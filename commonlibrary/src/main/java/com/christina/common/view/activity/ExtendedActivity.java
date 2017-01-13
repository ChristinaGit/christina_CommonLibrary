package com.christina.common.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.christina.common.contract.Contracts;
import com.christina.common.event.Events;
import com.christina.common.event.generic.Event;
import com.christina.common.event.generic.ManagedEvent;
import com.christina.common.event.notice.ManagedNoticeEvent;
import com.christina.common.event.notice.NoticeEvent;
import com.christina.common.view.ViewBinder;
import com.christina.common.view.observerable.ObservableActivity;
import com.christina.common.view.observerable.eventArgs.ActivityResultEventArgs;
import com.christina.common.view.observerable.eventArgs.BundleEventArgs;
import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.RxLifecycle;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.android.RxLifecycleAndroid;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;
import rx.Observable;
import rx.subjects.BehaviorSubject;

@Accessors(prefix = "_")
public abstract class ExtendedActivity extends AppCompatActivity
    implements ViewBinder, ObservableActivity, LifecycleProvider<ActivityEvent> {
    @NonNull
    @Override
    public final Event<BundleEventArgs> getActivityCreateEvent() {
        return _activityCreateEvent;
    }

    @NonNull
    @Override
    public final NoticeEvent getActivityDestroyEvent() {
        return _activityDestroyEvent;
    }

    @NonNull
    @Override
    public final NoticeEvent getActivityPauseEvent() {
        return _activityPauseEvent;
    }

    @NonNull
    @Override
    public final Event<BundleEventArgs> getActivityRestoreInstanceStateEvent() {
        return _activityRestoreInstanceStateEvent;
    }

    @NonNull
    @Override
    public final NoticeEvent getActivityResumeEvent() {
        return _activityResumeEvent;
    }

    @NonNull
    @Override
    public final Event<BundleEventArgs> getActivitySaveInstanceStateEvent() {
        return _activitySaveInstanceStateEvent;
    }

    @NonNull
    @Override
    public final NoticeEvent getActivityStartEvent() {
        return _activityStartEvent;
    }

    @NonNull
    @Override
    public final NoticeEvent getActivityStopEvent() {
        return _activityStopEvent;
    }

    @NonNull
    @Override
    public final Event<ActivityResultEventArgs> onActivityResultIntoActivityEvent() {
        return _activityResultIntoActivityEvent;
    }

    @Override
    @NonNull
    @CheckResult
    public final Observable<ActivityEvent> lifecycle() {
        return getLifecycleSubject().asObservable();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull final ActivityEvent event) {
        Contracts.requireNonNull(event, "event == null");

        return RxLifecycle.bindUntilEvent(getLifecycleSubject(), event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(getLifecycleSubject());
    }

    @CallSuper
    @Override
    public void bindViews() {
        unbindViews();

        _unbinder = ButterKnife.bind(this);
    }

    @CallSuper
    @Override
    public void bindViews(@NonNull final View source) {
        Contracts.requireNonNull(source, "source == null");

        unbindViews();

        _unbinder = ButterKnife.bind(this, source);
    }

    @CallSuper
    @Override
    public void unbindViews() {
        final val unbinder = getUnbinder();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @CallSuper
    @Override
    protected void onActivityResult(
        final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        final val eventArgs = new ActivityResultEventArgs(requestCode, resultCode, data);
        _activityResultIntoActivityEvent.rise(eventArgs);
    }

    @CallSuper
    @Override
    protected void onPause() {
        super.onPause();

        _activityPauseEvent.rise();

        getLifecycleSubject().onNext(ActivityEvent.PAUSE);
    }

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();

        _activityResumeEvent.rise();

        getLifecycleSubject().onNext(ActivityEvent.RESUME);
    }

    @CallSuper
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onInjectMembers();

        _activityCreateEvent.rise(new BundleEventArgs(savedInstanceState));

        getLifecycleSubject().onNext(ActivityEvent.CREATE);
    }

    @CallSuper
    @Override
    protected void onStart() {
        super.onStart();

        _activityStartEvent.rise();

        getLifecycleSubject().onNext(ActivityEvent.START);
    }

    @CallSuper
    @Override
    protected void onStop() {
        super.onStop();

        getLifecycleSubject().onNext(ActivityEvent.STOP);

        _activityStopEvent.rise();
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();

        _activityDestroyEvent.rise();

        getLifecycleSubject().onNext(ActivityEvent.DESTROY);

        onReleaseInjectedMembers();
    }

    @CallSuper
    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        _activitySaveInstanceStateEvent.rise(new BundleEventArgs(outState));
    }

    @CallSuper
    protected void onInjectMembers() {
    }

    @CallSuper
    protected void onReleaseInjectedMembers() {
    }

    @CallSuper
    @Override
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        _activityRestoreInstanceStateEvent.rise(new BundleEventArgs(savedInstanceState));
    }

    @NonNull
    private final ManagedEvent<BundleEventArgs> _activityCreateEvent = Events.createEvent();

    @NonNull
    private final ManagedNoticeEvent _activityDestroyEvent = Events.createNoticeEvent();

    @NonNull
    private final ManagedNoticeEvent _activityPauseEvent = Events.createNoticeEvent();

    @NonNull
    private final ManagedEvent<BundleEventArgs> _activityRestoreInstanceStateEvent =
        Events.createEvent();

    @NonNull
    private final ManagedEvent<ActivityResultEventArgs> _activityResultIntoActivityEvent =
        Events.createEvent();

    @NonNull
    private final ManagedNoticeEvent _activityResumeEvent = Events.createNoticeEvent();

    @NonNull
    private final ManagedEvent<BundleEventArgs> _activitySaveInstanceStateEvent =
        Events.createEvent();

    @NonNull
    private final ManagedNoticeEvent _activityStartEvent = Events.createNoticeEvent();

    @NonNull
    private final ManagedNoticeEvent _activityStopEvent = Events.createNoticeEvent();

    @Getter(AccessLevel.PRIVATE)
    @NonNull
    private final BehaviorSubject<ActivityEvent> _lifecycleSubject = BehaviorSubject.create();

    @Getter(AccessLevel.PROTECTED)
    @Nullable
    private Unbinder _unbinder;
}
