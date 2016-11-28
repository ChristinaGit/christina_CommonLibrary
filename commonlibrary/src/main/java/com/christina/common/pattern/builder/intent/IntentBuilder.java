package com.christina.common.pattern.builder.intent;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.christina.common.pattern.builder.ContextualBuilder;

public abstract class IntentBuilder implements ContextualBuilder<IntentBuilderResult, Context> {
    @NonNull
    @Override
    public final IntentBuilderResult build(@NonNull final Context context) {
        return new IntentBuilderResult() {
            @NonNull
            @Override
            public Intent asIntent() {
                return buildIntent(context);
            }

            @NonNull
            @Override
            public PendingIntent asPendingIntent(final int requestCode, final int flags) {
                return buildPendingIntent(context, buildIntent(context), requestCode, flags);
            }
        };
    }

    @NonNull
    protected abstract Intent buildIntent(@NonNull final Context context);

    @NonNull
    protected abstract PendingIntent buildPendingIntent(
        @NonNull final Context context, @NonNull final Intent intent, int requestCode, int flags);
}
