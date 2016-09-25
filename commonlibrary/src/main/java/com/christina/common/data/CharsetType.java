package com.christina.common.data;

import android.support.annotation.NonNull;

public enum CharsetType {
    ISO_8859_1("ISO-8859-1"),

    US_ASCII("US-ASCII"),

    UTF_16("UTF-16"),

    UTF_16BE("UTF-16BE"),

    UTF_16LE("UTF-16LE"),

    UTF_8("UTF-8");

    @NonNull
    public final String getCharsetName() {
        return _charsetName;
    }

    private final String _charsetName;

    CharsetType(@NonNull final String charsetName) {
        _charsetName = charsetName;
    }
}
