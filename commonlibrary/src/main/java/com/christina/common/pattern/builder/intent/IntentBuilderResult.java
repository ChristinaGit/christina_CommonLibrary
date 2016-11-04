package com.christina.common.pattern.builder.intent;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.NonNull;

public interface IntentBuilderResult {
    @NonNull
    Intent asIntent();

    @NonNull
    PendingIntent asPendingIntent(int requestCode, int flags);
}
