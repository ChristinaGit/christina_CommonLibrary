package com.christina.common.data.content;

import android.content.ContentProvider;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.contract.Contracts;
import com.christina.common.data.database.Database;

public abstract class ContentProviderBase extends ContentProvider {
    @CallSuper
    @Override
    public boolean onCreate() {
        boolean created = false;

        final Context context = getContext();
        if (context != null) {
            _database = onCreateDatabase(context);

            created = true;
        }
        return created;
    }

    @NonNull
    protected final Database getDatabase() {
        Contracts.requireNonNull(_database, "Content provider is not created");

        return _database;
    }

    protected final void notifyChange(@NonNull final Uri uri) {
        Contracts.requireNonNull(uri, "uri == null");

        notifyChange(uri, null);
    }

    protected final void notifyChange(@NonNull final Uri uri,
                                      @Nullable final ContentObserver contentObserver) {
        Contracts.requireNonNull(uri, "uri == null");

        final Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, contentObserver);
        }
    }

    @NonNull
    protected abstract Database onCreateDatabase(@NonNull Context context);

    @Nullable
    private Database _database;
}
