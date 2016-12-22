package com.christina.common.view.presentation;

import android.support.annotation.Nullable;

public interface Presenter<TPresentableView extends PresentableView> {
    @Nullable
    TPresentableView getPresentableView();

    void setPresentableView(@Nullable TPresentableView presentableView);
}
