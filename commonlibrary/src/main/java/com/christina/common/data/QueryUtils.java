package com.christina.common.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.contract.Contracts;

public final class QueryUtils {
    @NonNull
    public static String whereEquals(@NonNull String columnName, @NonNull String value) {
        return appendWhereEquals(null, columnName, value);
    }

    @NonNull
    public static String appendWhereEquals(@Nullable String where, @NonNull String columnName,
                                           @NonNull String value) {
        Contracts.requireNonNull(columnName, "columnName == null");
        Contracts.requireNonNull(value, "value == null");

        final String whereEquals = columnName + "='" + value + "'";
        if (where == null) {
            return whereEquals;
        } else {
            return where + " AND " + whereEquals;
        }
    }

    private QueryUtils() {
    }
}
