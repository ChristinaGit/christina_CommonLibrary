package com.christina.common.data.cursor;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.contract.Contracts;

public final class CursorValueExtractors {
    @NonNull
    public static final CursorValueExtractor<String> STRING =
        new AbstractCursorValueExtractor<String>() {
            @Nullable
            @Override
            public String extract(@NonNull final Cursor cursor, final int columnIndex) {
                Contracts.requireNonNull(cursor, "cursor == null");

                return cursor.getString(columnIndex);
            }
        };

    @NonNull
    public static final CursorValueExtractor<byte[]> BLOB =
        new AbstractCursorValueExtractor<byte[]>() {
            @Nullable
            @Override
            public byte[] extract(@NonNull final Cursor cursor, final int columnIndex) {
                Contracts.requireNonNull(cursor, "cursor == null");

                return cursor.getBlob(columnIndex);
            }
        };

    @NonNull
    public static final CursorValueExtractor<Double> DOUBLE =
        new AbstractCursorValueExtractor<Double>() {
            @Nullable
            @Override
            public Double extract(@NonNull final Cursor cursor, final int columnIndex) {
                Contracts.requireNonNull(cursor, "cursor == null");

                return CursorUtils.getNullableDouble(cursor, columnIndex);
            }
        };

    @NonNull
    public static final CursorValueExtractor<Float> FLOAT =
        new AbstractCursorValueExtractor<Float>() {
            @Nullable
            @Override
            public Float extract(@NonNull final Cursor cursor, final int columnIndex) {
                Contracts.requireNonNull(cursor, "cursor == null");

                return CursorUtils.getNullableFloat(cursor, columnIndex);
            }
        };

    @NonNull
    public static final CursorValueExtractor<Long> LONG = new AbstractCursorValueExtractor<Long>() {
        @Nullable
        @Override
        public Long extract(@NonNull final Cursor cursor, final int columnIndex) {
            Contracts.requireNonNull(cursor, "cursor == null");

            return CursorUtils.getNullableLong(cursor, columnIndex);
        }
    };

    @NonNull
    public static final CursorValueExtractor<Integer> INT =
        new AbstractCursorValueExtractor<Integer>() {
            @Nullable
            @Override
            public Integer extract(@NonNull final Cursor cursor, final int columnIndex) {
                Contracts.requireNonNull(cursor, "cursor == null");

                return CursorUtils.getNullableInt(cursor, columnIndex);
            }
        };

    @NonNull
    public static final CursorValueExtractor<Short> SHORT =
        new AbstractCursorValueExtractor<Short>() {
            @Nullable
            @Override
            public Short extract(@NonNull final Cursor cursor, final int columnIndex) {
                Contracts.requireNonNull(cursor, "cursor == null");

                return CursorUtils.getNullableShort(cursor, columnIndex);
            }
        };

    private CursorValueExtractors() {
        Contracts.unreachable();
    }
}
