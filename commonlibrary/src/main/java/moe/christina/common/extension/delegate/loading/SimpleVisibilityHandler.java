package moe.christina.common.extension.delegate.loading;

import android.support.annotation.NonNull;
import android.view.View;

import moe.christina.common.contract.Contracts;

public class SimpleVisibilityHandler implements VisibilityHandler {
    @Override
    public void changeVisibility(@NonNull final View view, final boolean visible) {
        Contracts.requireNonNull(view, "view == null");

        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
