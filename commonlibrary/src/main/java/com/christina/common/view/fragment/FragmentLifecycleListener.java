package com.christina.common.view.fragment;

public interface FragmentLifecycleListener extends FragmentListener {
    void onFragmentCreateView();

    void onFragmentDestroy();

    void onFragmentPause();

    void onFragmentResume();

    void onFragmentStart();

    void onFragmentStop();
}
