package com.christina.common.data;

import android.support.annotation.NonNull;

import com.christina.common.contract.Contracts;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public final class UriUtils {
    public static final String NUMBER_PLACEHOLDER = "#";

    public static final String PATH_SEPARATOR = "/";

    public static final String SCHEMA_SEPARATOR = "://";

    @NonNull
    public static String encode(@NonNull String str, @NonNull CharsetType charsetType) {
        Contracts.requireNonNull(str, "str == null");
        Contracts.requireNonNull(charsetType, "charsetType == null");

        try {
            return URLEncoder.encode(str, charsetType.getCharsetName());
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    @NonNull
    public static String decode(@NonNull String str, @NonNull CharsetType charsetType) {
        Contracts.requireNonNull(str, "str == null");
        Contracts.requireNonNull(charsetType, "charsetType == null");

        try {
            return URLDecoder.decode(str, charsetType.getCharsetName());
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    @NonNull
    public static String combine(@NonNull String... pathSegments) {
        Contracts.requireNonNull(pathSegments, "pathSegments == null");

        return StringUtils.join(pathSegments, PATH_SEPARATOR);
    }

    private UriUtils() {
    }
}
