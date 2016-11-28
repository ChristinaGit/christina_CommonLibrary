package com.christina.common.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

public interface ActivityInstanceStateListener extends ActivityListener {
    void onActivityCreate(@Nullable final Bundle savedInstanceState);

    void onActivityRestoreInstanceState(@Nullable final Bundle savedInstanceState);

    void onActivitySaveInstanceState(@Nullable final Bundle outState);
}
