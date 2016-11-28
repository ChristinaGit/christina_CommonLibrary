package com.christina.common.view.fragment;

import android.support.annotation.NonNull;

public interface ObservableFragment {
    void registerFragmentListener(@NonNull final FragmentListener listener);

    void unregisterFragmentListener(@NonNull final FragmentListener listener);
}
