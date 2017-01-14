package com.christina.common.data.content;

import android.content.ContentProvider;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import lombok.val;

import com.christina.common.contract.Contracts;
import com.christina.common.data.database.SqlDatabase;

public abstract class SqlDatabaseContentProvider extends ContentProvider {
    @CallSuper
    @Override
    public boolean onCreate() {
        boolean created = false;

        final val context = getContext();
        if (context != null) {
            _sqlDatabase = onCreateDatabase(context);

            created = true;
        }
        return created;
    }

    @NonNull
    protected final SqlDatabase getSqlDatabase() {
        if (_sqlDatabase == null) {
            throw new IllegalStateException("Content provider is not created");
        }

        return _sqlDatabase;
    }

    protected final void notifyChange(@NonNull final Uri uri) {
        Contracts.requireNonNull(uri, "uri == null");

        notifyChange(uri, null);
    }

    protected final void notifyChange(
        @NonNull final Uri uri, @Nullable final ContentObserver contentObserver) {
        Contracts.requireNonNull(uri, "uri == null");

        final val context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, contentObserver);
        }
    }

    @NonNull
    protected abstract SqlDatabase onCreateDatabase(@NonNull Context context);

    @Nullable
    private SqlDatabase _sqlDatabase;
}
