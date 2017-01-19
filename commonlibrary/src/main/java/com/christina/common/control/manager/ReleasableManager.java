package com.christina.common.control.manager;

import android.support.annotation.NonNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;

import com.christina.common.control.adviser.ResourceAdviser;
import com.christina.common.contract.Contracts;
import com.christina.common.event.notice.NoticeEventHandler;

@Accessors(prefix = "_")
public abstract class ReleasableManager {
    protected ReleasableManager(@NonNull final ResourceAdviser resourceAdviser) {
        Contracts.requireNonNull(resourceAdviser, "resourceAdviser == null");

        _resourceAdviser = resourceAdviser;

        resourceAdviser.getAcquireResourcesEvent().addHandler(_acquireResourcesHandler);
        resourceAdviser.getReleaseResourcesEvent().addHandler(_releaseResourcesHandler);
    }

    protected abstract void onAcquireResources();

    protected abstract void onReleaseResources();

    @NonNull
    private final NoticeEventHandler _acquireResourcesHandler = new NoticeEventHandler() {
        @Override
        public void onEvent() {
            onAcquireResources();
        }
    };

    @NonNull
    private final NoticeEventHandler _releaseResourcesHandler = new NoticeEventHandler() {
        @Override
        public void onEvent() {
            onReleaseResources();

            final val resourceAdviser = getResourceAdviser();

            resourceAdviser.getAcquireResourcesEvent().removeHandler(_acquireResourcesHandler);
            resourceAdviser.getReleaseResourcesEvent().removeHandler(_releaseResourcesHandler);
        }
    };

    @Getter(AccessLevel.PROTECTED)
    @NonNull
    private final ResourceAdviser _resourceAdviser;
}
