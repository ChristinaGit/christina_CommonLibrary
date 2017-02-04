package moe.christina.common.extension.delegate.loading;

import android.support.annotation.NonNull;
import android.view.View;

import lombok.val;

import moe.christina.common.contract.Contracts;
import moe.christina.common.extension.view.ContentLoaderProgressBar;

public class ProgressVisibilityHandler extends SimpleVisibilityHandler {
    @Override
    public void changeVisibility(@NonNull final View view, final boolean visible) {
        Contracts.requireNonNull(view, "view == null");

        if (view instanceof ContentLoaderProgressBar) {
            final val progressBar = (ContentLoaderProgressBar) view;
            if (visible) {
                progressBar.show();
            } else {
                progressBar.hide();
            }
        } else {
            super.changeVisibility(view, visible);
        }
    }
}
