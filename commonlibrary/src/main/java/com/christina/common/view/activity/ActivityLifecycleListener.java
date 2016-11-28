package com.christina.common.view.activity;

public interface ActivityLifecycleListener extends ActivityListener {
    void onActivityCreate();

    void onActivityDestroy();

    void onActivityPause();

    void onActivityResume();

    void onActivityStart();

    void onActivityStop();
}
