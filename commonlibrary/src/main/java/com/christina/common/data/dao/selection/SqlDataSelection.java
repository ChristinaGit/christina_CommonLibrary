package com.christina.common.data.dao.selection;

import android.support.annotation.Nullable;

public interface SqlDataSelection {
    @Nullable
    String[] getWhereArguments();

    @Nullable
    String getWhereClause();
}
