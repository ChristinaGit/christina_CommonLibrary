package moe.christina.common.extension.eventArgs;

import android.os.Bundle;
import android.support.annotation.Nullable;

import lombok.Getter;
import lombok.experimental.Accessors;

import moe.christina.common.event.eventArgs.EventArgs;

@Accessors(prefix = "_")
public class BundleEventArgs extends EventArgs {
    public BundleEventArgs(@Nullable final Bundle data) {
        _data = data;
    }

    @Getter
    @Nullable
    private final Bundle _data;
}
