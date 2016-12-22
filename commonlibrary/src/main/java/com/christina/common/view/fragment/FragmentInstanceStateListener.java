package com.christina.common.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

public interface FragmentInstanceStateListener extends FragmentListener {
    void onFragmentCreateView(@Nullable final Bundle savedInstanceState);

    void onFragmentSaveInstanceState(@Nullable final Bundle outState);

    void onFragmentViewStateRestored(@Nullable final Bundle savedInstanceState);
}
