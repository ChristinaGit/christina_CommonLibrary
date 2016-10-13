package com.christina.common;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.christina.common.contract.Contracts;

public final class FragmentUtils {
    @NonNull
    public static Bundle getArguments(@NonNull final Fragment fragment) {
        Contracts.requireNonNull(fragment, "fragment == null");

        Bundle arguments = fragment.getArguments();

        if (arguments == null) {
            arguments = new Bundle();
            fragment.setArguments(arguments);
        }

        return arguments;
    }

    private FragmentUtils() {
    }
}
