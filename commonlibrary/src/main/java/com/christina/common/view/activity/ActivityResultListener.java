package com.christina.common.view.activity;

import android.content.Intent;
import android.support.annotation.Nullable;

public interface ActivityResultListener extends ActivityListener {
    void onActivityResultIntoActivity(int requestCode, int resultCode, @Nullable Intent data);
}
