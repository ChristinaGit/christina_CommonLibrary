package com.christina.common.data.dao.selection;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.christina.common.contract.Contracts;
import com.christina.common.pattern.builder.Builder;

import java.util.List;

import lombok.Getter;
import lombok.experimental.Accessors;

public final class SqlDataSelections {
    @NonNull
    public static WhereClauseSetter builder() {
        return new _Builder();
    }

    @NonNull
    public static SqlDataSelection create(
        @Nullable final String whereClause,
        @Nullable final String[] whereArguments) {
        return create(whereClause, whereArguments, null);
    }

    @NonNull
    public static SqlDataSelection create(@Nullable final String sortOrder) {
        return create(null, null, sortOrder);
    }

    @NonNull
    public static SqlDataSelection create(
        @Nullable final String whereClause,
        @Nullable final String[] whereArguments,
        @Nullable final String sortOrder) {
        return new _SqlDataSelection(whereClause, whereArguments, sortOrder);
    }

    private SqlDataSelections() {
        Contracts.unreachable();
    }

    public interface SortOrderSetter extends Builder<SqlDataSelection> {
        @NonNull
        Builder<SqlDataSelection> sortBy(@NonNull String... columns);

        @NonNull
        Builder<SqlDataSelection> sortBy(@NonNull Iterable<String> columns);
    }

    public interface WhereArgumentsSetter {
        @NonNull
        SortOrderSetter with(@NonNull String... whereArguments);

        @NonNull
        SortOrderSetter with(@NonNull List<String> whereArguments);
    }

    public interface WhereClauseSetter extends SortOrderSetter {
        @NonNull
        WhereArgumentsSetter where(@NonNull String where);
    }

    private static final class _Builder implements SortOrderSetter,
                                                   WhereArgumentsSetter,
                                                   WhereClauseSetter,
                                                   Builder<SqlDataSelection> {
        @NonNull
        @Override
        public final SqlDataSelection build() {
            return new _SqlDataSelection(_whereClause, _whereArguments, _sortOrder);
        }

        @NonNull
        @Override
        public final Builder<SqlDataSelection> sortBy(@NonNull final String... columns) {
            Contracts.requireNonNull(columns, "columns == null");

            _sortOrder = TextUtils.join(",", columns);

            return this;
        }

        @NonNull
        @Override
        public final Builder<SqlDataSelection> sortBy(@NonNull final Iterable<String> columns) {
            Contracts.requireNonNull(columns, "columns == null");

            _sortOrder = TextUtils.join(",", columns);

            return this;
        }

        @NonNull
        @Override
        public final WhereArgumentsSetter where(@NonNull final String where) {
            Contracts.requireNonNull(where, "where == null");

            _whereClause = where;

            return this;
        }

        @NonNull
        @Override
        public final SortOrderSetter with(@NonNull final String... whereArguments) {
            Contracts.requireNonNull(whereArguments, "whereArguments == null");

            _whereArguments = whereArguments;

            return this;
        }

        @NonNull
        @Override
        public final SortOrderSetter with(@NonNull final List<String> whereArguments) {
            Contracts.requireNonNull(whereArguments, "whereArguments == null");

            _whereArguments = whereArguments.toArray(new String[whereArguments.size()]);

            return this;
        }

        @Nullable
        private String _sortOrder;

        @Nullable
        private String[] _whereArguments;

        @Nullable
        private String _whereClause;
    }

    @Accessors(prefix = "_")
    private static final class _SqlDataSelection implements SqlDataSelection {
        @Getter(onMethod = @__(@Override))
        @Nullable
        private final String _sortOrder;

        @Getter(onMethod = @__(@Override))
        @Nullable
        private final String[] _whereArguments;

        @Getter(onMethod = @__(@Override))
        @Nullable
        private final String _whereClause;

        private _SqlDataSelection(
            @Nullable final String whereClause,
            @Nullable final String[] whereArguments,
            @Nullable final String sortOrder) {
            _whereClause = whereClause;
            _whereArguments = whereArguments;
            _sortOrder = sortOrder;
        }
    }
}
