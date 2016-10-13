package com.christina.common.view;

import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.christina.common.contract.Contracts;

public final class AnimationViewUtils {
    public static void animateSetVisibility(@NonNull final View view, final int visibility) {
        Contracts.requireNonNull(view, "view == null");

        animateSetVisibility(view, visibility, android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public static void animateSetVisibility(@NonNull final View view, final int visibility,
        @AnimRes final int in, @AnimRes final int out) {
        Contracts.requireNonNull(view, "view == null");

        if (visibility != view.getVisibility()) {
            final boolean visible = visibility == View.VISIBLE;
            final Animation animation =
                AnimationUtils.loadAnimation(view.getContext(), visible ? in : out);
            view.startAnimation(animation);
            view.setVisibility(visibility);
        }
    }

    private AnimationViewUtils() {
    }
}
