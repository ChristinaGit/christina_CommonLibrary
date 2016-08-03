package com.christina.common.data;

import android.support.annotation.NonNull;

import com.christina.common.contract.Contracts;

public final class MimeTypeUtils {
    @NonNull
    public static String combineDirContentType(@NonNull String companyName,
                                               @NonNull String contentTypeSimpleName) {
        Contracts.requireNonNull(companyName, "companyName == null");
        Contracts.requireNonNull(contentTypeSimpleName, "contentTypeSimpleName == null");

        return "vnd.android.cursor.dir/vnd." + companyName + "." + contentTypeSimpleName;
    }

    @NonNull
    public static String combineItemContentType(@NonNull String companyName,
                                                @NonNull String contentTypeSimpleName) {
        Contracts.requireNonNull(companyName, "companyName == null");
        Contracts.requireNonNull(contentTypeSimpleName, "contentTypeSimpleName == null");

        return "vnd.android.cursor.item/vnd." + companyName + "." + contentTypeSimpleName;
    }

    private MimeTypeUtils() {
    }
}
