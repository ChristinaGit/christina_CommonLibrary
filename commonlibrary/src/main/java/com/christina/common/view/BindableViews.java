package com.christina.common.view;

import android.support.annotation.NonNull;
import android.view.View;

public interface BindableViews {
    void bindViews();

    void bindViews(@NonNull final View source);

    void unbindViews();
}
