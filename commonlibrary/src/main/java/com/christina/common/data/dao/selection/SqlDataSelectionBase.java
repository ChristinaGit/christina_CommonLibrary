package com.christina.common.data.dao.selection;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
@EqualsAndHashCode(doNotUseGetters = true)
public class SqlDataSelectionBase implements SqlDataSelection {
    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    protected SqlDataSelectionBase(
        @Nullable final String[] whereArguments,
        @Nullable final String whereClause,
        @Nullable final String sortOrder) {
        _whereArguments = whereArguments;
        _whereClause = whereClause;
        _sortOrder = sortOrder;
    }

    @Nullable
    @Getter(onMethod = @__(@Override))
    private final String _sortOrder;

    @Nullable
    @Getter(onMethod = @__(@Override))
    private final String[] _whereArguments;

    @Nullable
    @Getter(onMethod = @__(@Override))
    private final String _whereClause;

    public static class Builder
        implements com.christina.common.pattern.builder.Builder<SqlDataSelectionBase> {

        @NonNull
        public final Builder sortBy(final String... columns) {
            _sortOrder = TextUtils.join(",", columns);

            return this;
        }

        @NonNull
        public final Builder where(@Nullable final String whereClause) {
            _whereClause = whereClause;

            return this;
        }

        @NonNull
        public final Builder with(@Nullable final String... whereArguments) {
            _whereArguments = whereArguments;

            return this;
        }

        @NonNull
        @Override
        public SqlDataSelectionBase build() {
            return new SqlDataSelectionBase(_whereArguments, _whereClause, _sortOrder);
        }

        @Nullable
        private String _sortOrder;

        @Nullable
        private String[] _whereArguments;

        @Nullable
        private String _whereClause;
    }
}