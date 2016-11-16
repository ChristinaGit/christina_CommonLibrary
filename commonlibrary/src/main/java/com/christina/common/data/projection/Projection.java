package com.christina.common.data.projection;

import android.support.annotation.NonNull;

public interface Projection {
    int getColumnsCount();

    @NonNull
    String[] getColumns();
}
