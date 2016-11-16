package com.christina.common.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
public abstract class BoundFragment extends Fragment {
    @Nullable
    public View onCreateUnboundView(final LayoutInflater inflater,
        @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
        @Nullable final Bundle savedInstanceState) {

        final View view = onCreateUnboundView(inflater, container, savedInstanceState);

        if (view != null) {
            _unbinder = ButterKnife.bind(this, view);
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        final Unbinder unbinder = getUnbinder();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Getter(AccessLevel.PROTECTED)
    @Nullable
    private Unbinder _unbinder;
}
