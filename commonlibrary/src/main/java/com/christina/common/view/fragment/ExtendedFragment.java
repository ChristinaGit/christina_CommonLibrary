package com.christina.common.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import rx.Observable;
import rx.subjects.BehaviorSubject;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.RxLifecycle;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.trello.rxlifecycle.android.RxLifecycleAndroid;

import com.christina.common.aware.InjectAware;
import com.christina.common.aware.ResourceAware;
import com.christina.common.contract.Contracts;
import com.christina.common.event.Events;
import com.christina.common.event.generic.Event;
import com.christina.common.event.generic.ManagedEvent;
import com.christina.common.event.notice.ManagedNoticeEvent;
import com.christina.common.event.notice.NoticeEvent;
import com.christina.common.view.ViewBinder;
import com.christina.common.view.observerable.ObservableFragment;
import com.christina.common.view.observerable.eventArgs.ActivityResultEventArgs;
import com.christina.common.view.observerable.eventArgs.BundleEventArgs;

@Accessors(prefix = "_")
public abstract class ExtendedFragment extends Fragment
    implements ViewBinder, ObservableFragment, LifecycleProvider<FragmentEvent>, InjectAware,
               ResourceAware {
    @NonNull
    @Override
    public final Fragment asFragment() {
        return this;
    }

    @NonNull
    public final Event<ActivityResultEventArgs> getFragmentActivityResultEvent() {
        return _fragmentActivityResultEvent;
    }

    @NonNull
    @Override
    public final NoticeEvent getFragmentAttachEvent() {
        return _fragmentAttachEvent;
    }

    @NonNull
    @Override
    public final Event<BundleEventArgs> getFragmentCreateEvent() {
        return _fragmentCreateEvent;
    }

    @NonNull
    @Override
    public final Event<BundleEventArgs> getFragmentCreateViewEvent() {
        return _fragmentCreateViewEvent;
    }

    @NonNull
    @Override
    public final NoticeEvent getFragmentDestroyEvent() {
        return _fragmentDestroyEvent;
    }

    @NonNull
    @Override
    public final NoticeEvent getFragmentDestroyViewEvent() {
        return _fragmentDestroyViewEvent;
    }

    @NonNull
    @Override
    public final NoticeEvent getFragmentDetachEvent() {
        return _fragmentDetachEvent;
    }

    @NonNull
    @Override
    public final NoticeEvent getFragmentPauseEvent() {
        return _fragmentPauseEvent;
    }

    @NonNull
    @Override
    public final NoticeEvent getFragmentResumeEvent() {
        return _fragmentResumeEvent;
    }

    @NonNull
    @Override
    public final Event<BundleEventArgs> getFragmentSaveInstanceStateEvent() {
        return _fragmentSaveInstanceStateEvent;
    }

    @NonNull
    @Override
    public final NoticeEvent getFragmentStartEvent() {
        return _fragmentStartEvent;
    }

    @NonNull
    @Override
    public final NoticeEvent getFragmentStopEvent() {
        return _fragmentStopEvent;
    }

    @NonNull
    @Override
    public final Event<BundleEventArgs> getFragmentViewCreatedEvent() {
        return _fragmentViewCratedEvent;
    }

    @NonNull
    @Override
    public final Event<BundleEventArgs> getFragmentViewStateRestoredEvent() {
        return _fragmentViewStateRestoredEvent;
    }

    @NonNull
    @Override
    public final NoticeEvent getAcquireResourcesEvent() {
        return _acquireResourcesEvent;
    }

    @NonNull
    @Override
    public final NoticeEvent getReleaseResourcesEvent() {
        return _releaseResourcesEvent;
    }

    @NonNull
    @Override
    public final NoticeEvent getInjectMembersEvent() {
        return _injectMembersEvent;
    }

    @NonNull
    @Override
    public final NoticeEvent getReleaseInjectedMembersEvent() {
        return _releaseInjectedMembersEvent;
    }

    @Override
    @NonNull
    @CheckResult
    public final Observable<FragmentEvent> lifecycle() {
        return getLifecycleSubject().asObservable();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull final FragmentEvent event) {
        Contracts.requireNonNull(event, "event == null");

        return RxLifecycle.bindUntilEvent(getLifecycleSubject(), event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindFragment(getLifecycleSubject());
    }

    @CallSuper
    @Override
    public void bindViews() {
        unbindViews();

        final val view = getView();
        if (view != null) {
            _unbinder = ButterKnife.bind(this, view);
        }
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
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        final val eventArgs = new ActivityResultEventArgs(requestCode, resultCode, data);
        _fragmentActivityResultEvent.rise(eventArgs);
    }

    @CallSuper
    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);

        _fragmentAttachEvent.rise();

        getLifecycleSubject().onNext(FragmentEvent.ATTACH);
    }

    @CallSuper
    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _fragmentCreateEvent.rise(new BundleEventArgs(savedInstanceState));

        getLifecycleSubject().onNext(FragmentEvent.CREATE);
    }

    @CallSuper
    @Nullable
    @Override
    public View onCreateView(
        final LayoutInflater inflater,
        @Nullable final ViewGroup container,
        @Nullable final Bundle savedInstanceState) {
        final val view = super.onCreateView(inflater, container, savedInstanceState);

        onInjectMembers();

        onAcquireResources();

        _fragmentCreateViewEvent.rise(new BundleEventArgs(savedInstanceState));

        return view;
    }

    @CallSuper
    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        _fragmentViewCratedEvent.rise(new BundleEventArgs(savedInstanceState));

        getLifecycleSubject().onNext(FragmentEvent.CREATE_VIEW);
    }

    @CallSuper
    @Override
    public void onViewStateRestored(@Nullable final Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        _fragmentViewStateRestoredEvent.rise(new BundleEventArgs(savedInstanceState));
    }

    @CallSuper
    @Override
    public void onStart() {
        super.onStart();

        _fragmentStartEvent.rise();

        getLifecycleSubject().onNext(FragmentEvent.START);
    }

    @Override
    public void onResume() {
        super.onResume();

        _fragmentResumeEvent.rise();

        getLifecycleSubject().onNext(FragmentEvent.RESUME);
    }

    @CallSuper
    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        _fragmentSaveInstanceStateEvent.rise(new BundleEventArgs(outState));
    }

    @CallSuper
    @Override
    public void onPause() {
        super.onPause();

        _fragmentPauseEvent.rise();

        getLifecycleSubject().onNext(FragmentEvent.PAUSE);
    }

    @CallSuper
    @Override
    public void onStop() {
        super.onStop();

        _fragmentStopEvent.rise();

        getLifecycleSubject().onNext(FragmentEvent.STOP);
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        super.onDestroyView();

        _fragmentDestroyViewEvent.rise();

        getLifecycleSubject().onNext(FragmentEvent.DESTROY_VIEW);
    }

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();

        _fragmentDestroyEvent.rise();

        getLifecycleSubject().onNext(FragmentEvent.DESTROY);

        onReleaseResources();

        onReleaseInjectedMembers();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        _fragmentDetachEvent.rise();

        getLifecycleSubject().onNext(FragmentEvent.DETACH);
    }

    @CallSuper
    protected void onAcquireResources() {
        _acquireResourcesEvent.rise();
    }

    @CallSuper
    protected void onInjectMembers() {
        _injectMembersEvent.rise();
    }

    @CallSuper
    protected void onReleaseInjectedMembers() {
        _releaseInjectedMembersEvent.rise();
    }

    @CallSuper
    protected void onReleaseResources() {
        _releaseResourcesEvent.rise();
    }

    @NonNull
    private final ManagedNoticeEvent _acquireResourcesEvent = Events.createNoticeEvent();

    @NonNull
    private final ManagedEvent<ActivityResultEventArgs> _fragmentActivityResultEvent =
        Events.createEvent();

    @NonNull
    private final ManagedNoticeEvent _fragmentAttachEvent = Events.createNoticeEvent();

    @NonNull
    private final ManagedEvent<BundleEventArgs> _fragmentCreateEvent = Events.createEvent();

    @NonNull
    private final ManagedEvent<BundleEventArgs> _fragmentCreateViewEvent = Events.createEvent();

    @NonNull
    private final ManagedNoticeEvent _fragmentDestroyEvent = Events.createNoticeEvent();

    @NonNull
    private final ManagedNoticeEvent _fragmentDestroyViewEvent = Events.createNoticeEvent();

    @NonNull
    private final ManagedNoticeEvent _fragmentDetachEvent = Events.createNoticeEvent();

    @NonNull
    private final ManagedNoticeEvent _fragmentPauseEvent = Events.createNoticeEvent();

    @NonNull
    private final ManagedNoticeEvent _fragmentResumeEvent = Events.createNoticeEvent();

    @NonNull
    private final ManagedEvent<BundleEventArgs> _fragmentSaveInstanceStateEvent =
        Events.createEvent();

    @NonNull
    private final ManagedNoticeEvent _fragmentStartEvent = Events.createNoticeEvent();

    @NonNull
    private final ManagedNoticeEvent _fragmentStopEvent = Events.createNoticeEvent();

    @NonNull
    private final ManagedEvent<BundleEventArgs> _fragmentViewCratedEvent = Events.createEvent();

    private final ManagedEvent<BundleEventArgs> _fragmentViewStateRestoredEvent =
        Events.createEvent();

    @NonNull
    private final ManagedNoticeEvent _injectMembersEvent = Events.createNoticeEvent();

    @Getter(AccessLevel.PRIVATE)
    private final BehaviorSubject<FragmentEvent> _lifecycleSubject = BehaviorSubject.create();

    @NonNull
    private final ManagedNoticeEvent _releaseInjectedMembersEvent = Events.createNoticeEvent();

    @NonNull
    private final ManagedNoticeEvent _releaseResourcesEvent = Events.createNoticeEvent();

    @Getter(AccessLevel.PROTECTED)
    @Nullable
    private Unbinder _unbinder;
}
