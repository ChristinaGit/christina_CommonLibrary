package com.christina.common.view.fragment;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.christina.common.event.BaseNoticeEvent;
import com.christina.common.event.NoticeEvent;
import com.christina.common.view.presentation.PresentableView;

public abstract class PresentableFragment extends ExtendedFragment implements PresentableView {
    @NonNull
    @Override
    public final NoticeEvent getOnViewAppearEvent() {
        return _onViewAppearEvent;
    }

    @NonNull
    @Override
    public final NoticeEvent getOnViewCreateEvent() {
        return _onViewCreateEvent;
    }

    @NonNull
    @Override
    public final NoticeEvent getOnViewDestroyEvent() {
        return _onViewDestroyEvent;
    }

    @NonNull
    @Override
    public final NoticeEvent getOnViewDisappearEvent() {
        return _onViewDisappearEvent;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _onViewCreateEvent.rise();
    }

    @Override
    public void onResume() {
        super.onResume();

        _onViewAppearEvent.rise();
    }

    @Override
    public void onPause() {
        super.onPause();

        _onViewDisappearEvent.rise();
    }

    @CallSuper
    @Nullable
    @Override
    public View onCreateView(
        final LayoutInflater inflater,
        @Nullable final ViewGroup container,
        @Nullable final Bundle savedInstanceState) {
        onBindPresenter();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        _onViewDestroyEvent.rise();

        onUnbindPresenter();
    }

    protected void onBindPresenter() {
    }

    protected void onUnbindPresenter() {
    }

    @NonNull
    private final BaseNoticeEvent _onViewAppearEvent = new BaseNoticeEvent();

    @NonNull
    private final BaseNoticeEvent _onViewCreateEvent = new BaseNoticeEvent();

    @NonNull
    private final BaseNoticeEvent _onViewDestroyEvent = new BaseNoticeEvent();

    @NonNull
    private final BaseNoticeEvent _onViewDisappearEvent = new BaseNoticeEvent();
}
