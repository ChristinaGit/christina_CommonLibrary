package com.christina.common.presentation;

import android.support.annotation.NonNull;

public interface Presenter<TScreen extends Screen> {
    void bindScreen(@NonNull TScreen screen);

    void unbindScreen();
}
