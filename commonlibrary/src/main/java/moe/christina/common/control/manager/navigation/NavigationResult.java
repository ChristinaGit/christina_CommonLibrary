package moe.christina.common.control.manager.navigation;

import lombok.experimental.Accessors;

@Accessors(prefix = "_")
public enum NavigationResult {
    SUCCESS,
    CANCELED,
    FAILED,
    UNKNOWN
}
