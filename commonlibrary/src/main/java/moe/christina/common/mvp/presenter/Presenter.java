package moe.christina.common.mvp.presenter;

import android.support.annotation.NonNull;

import moe.christina.common.mvp.screen.Screen;

public interface Presenter<TScreen extends Screen> {
    void bindScreen(@NonNull TScreen screen);

    void unbindScreen();
}
