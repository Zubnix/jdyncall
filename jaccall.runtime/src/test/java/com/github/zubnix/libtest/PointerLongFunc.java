package com.github.zubnix.libtest;


import com.github.zubnix.jaccall.JNI;
import com.github.zubnix.jaccall.Pointer;
import com.github.zubnix.jaccall.PointerFunc;

import javax.annotation.Generated;
import javax.annotation.Nonnull;
import java.nio.ByteBuffer;

@Generated("com.github.zubnix.jaccall.compiletime.functor.FunctionPointerGenerator")
public abstract class PointerLongFunc extends PointerFunc<PointerLongFunc> implements LongFunc {

    static final long FFI_CIF = JNI.ffi_callInterface(JNI.FFI_TYPE_SLONG,
                                                      JNI.FFI_TYPE_SLONG);

    PointerLongFunc(final long address,
                    final ByteBuffer buffer) {
        super(PointerLongFunc.class,
              address,
              buffer);
    }

    @Nonnull
    public static PointerLongFunc nref(@Nonnull final LongFunc function) {
        if (function instanceof PointerLongFunc) {
            return (PointerLongFunc) function;
        }
        return new LongFunc_Jaccall_J(function);
    }
}
