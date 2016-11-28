package com.christina.common.view.activity;

import android.support.annotation.NonNull;

public interface ObservableActivity {
    void registerActivityListener(@NonNull final ActivityListener listener);

    void unregisterActivityListener(@NonNull final ActivityListener listener);
}
