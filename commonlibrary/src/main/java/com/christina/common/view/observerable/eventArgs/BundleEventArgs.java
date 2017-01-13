package com.christina.common.view.observerable.eventArgs;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.christina.common.event.eventArgs.EventArgs;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
public class BundleEventArgs extends EventArgs {
    public BundleEventArgs(@Nullable final Bundle data) {
        _data = data;
    }

    @Getter
    @Nullable
    private final Bundle _data;
}
