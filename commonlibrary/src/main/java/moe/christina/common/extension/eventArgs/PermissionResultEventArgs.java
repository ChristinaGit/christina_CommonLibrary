package moe.christina.common.extension.eventArgs;

import android.support.annotation.NonNull;

import lombok.Getter;
import lombok.experimental.Accessors;

import moe.christina.common.contract.Contracts;
import moe.christina.common.event.eventArgs.EventArgs;

@Accessors(prefix = "_")
public class PermissionResultEventArgs extends EventArgs {
    public PermissionResultEventArgs(
        @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        Contracts.requireNonNull(permissions, "permissions == null");
        Contracts.requireNonNull(grantResults, "grantResults == null");

        _permissions = permissions;
        _grantResults = grantResults;
    }

    @Getter
    @NonNull
    private final int[] _grantResults;

    @Getter
    @NonNull
    private final String[] _permissions;
}
