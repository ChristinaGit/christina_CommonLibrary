package com.christina.common.view.fragment;

import android.content.Intent;
import android.support.annotation.Nullable;

public interface FragmentResultListener extends FragmentListener {
    void onActivityResultIntoFragment(int requestCode, int resultCode, @Nullable Intent data);
}
