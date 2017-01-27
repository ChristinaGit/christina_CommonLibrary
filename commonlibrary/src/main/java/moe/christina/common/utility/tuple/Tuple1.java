package moe.christina.common.utility.tuple;

import android.support.annotation.Nullable;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString(doNotUseGetters = true)
@Accessors(prefix = "_")
public final class Tuple1<T1> {
    /*package-private*/ Tuple1(@Nullable final T1 first) {
        _first = first;
    }


    @Getter
    @Nullable
    private final T1 _first;
}
