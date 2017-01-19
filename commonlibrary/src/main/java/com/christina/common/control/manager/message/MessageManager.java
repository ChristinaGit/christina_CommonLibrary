package com.christina.common.control.manager.message;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

public interface MessageManager {
    void showInfoMessage(@NonNull CharSequence message);

    void showInfoMessage(@StringRes int messageId);

    void showNotificationMessage(@NonNull CharSequence message);

    void showNotificationMessage(@StringRes int messageId);
}
