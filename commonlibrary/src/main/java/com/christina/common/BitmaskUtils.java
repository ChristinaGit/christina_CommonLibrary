package com.christina.common;

public final class BitmaskUtils {
    public static long apply(long value, long mask) {
        return value & mask;
    }

    public static long set(long value, long mask) {
        return value | mask;
    }

    public static boolean match(long value, long mask) {
        return apply(value, mask) != 0L;
    }

    public static int apply(int value, int mask) {
        return value & mask;
    }

    public static int set(int value, int mask) {
        return value | mask;
    }

    public static boolean match(int value, int mask) {
        return apply(value, mask) != 0;
    }

    private BitmaskUtils() {
    }
}
