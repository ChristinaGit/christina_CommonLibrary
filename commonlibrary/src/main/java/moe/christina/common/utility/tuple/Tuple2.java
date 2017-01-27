package moe.christina.common.utility.tuple;

import android.support.annotation.Nullable;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString(doNotUseGetters = true)
@Accessors(prefix = "_")
public final class Tuple2<T1, T2> {
    /*package-private*/ Tuple2(@Nullable final T1 first, @Nullable final T2 second) {
        _first = first;
        _second = second;
    }

    @Getter
    @Nullable
    private final T1 _first;

    @Getter
    @Nullable
    private final T2 _second;
}
