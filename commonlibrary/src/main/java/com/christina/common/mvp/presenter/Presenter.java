package com.christina.common.mvp.presenter;

import android.support.annotation.NonNull;

import com.christina.common.mvp.screen.Screen;

public interface Presenter<TScreen extends Screen> {
    void bindScreen(@NonNull TScreen screen);

    void unbindScreen();
}
