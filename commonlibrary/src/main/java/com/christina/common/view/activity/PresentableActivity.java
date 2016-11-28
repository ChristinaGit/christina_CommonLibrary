package com.christina.common.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.event.BaseNoticeEvent;
import com.christina.common.event.NoticeEvent;
import com.christina.common.view.presentation.PresentableView;

import lombok.experimental.Accessors;

@Accessors(prefix = "_")
public abstract class PresentableActivity extends ExtendedActivity implements PresentableView {
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

    protected void onBindPresenter() {
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onBindPresenter();

        _onViewCreateEvent.rise();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        _onViewDestroyEvent.rise();

        onUnbindPresenter();
    }

    @Override
    protected void onPause() {
        super.onPause();

        _onViewDisappearEvent.rise();
    }

    @Override
    protected void onResume() {
        super.onResume();

        _onViewAppearEvent.rise();
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
