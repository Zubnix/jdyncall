package com.github.zubnix.libtest;


import com.github.zubnix.jaccall.JNI;
import com.github.zubnix.jaccall.PointerFunc;

import javax.annotation.Generated;
import javax.annotation.Nonnull;

@Generated("com.github.zubnix.jaccall.compiletime.funcptr.FunctionPointerGenerator")
public abstract class PointerDoubleFunc extends PointerFunc<PointerDoubleFunc> implements Testing.DoubleFunc {

    static final long FFI_CIF = JNI.ffi_callInterface(JNI.FFI_TYPE_DOUBLE,
                                                      JNI.FFI_TYPE_DOUBLE);

    PointerDoubleFunc(final long address) {
        super(PointerDoubleFunc.class,
              address);
    }

    @Nonnull
    public static PointerDoubleFunc wrapFunc(final long address) {
        return new DoubleFunc_Jaccall_C(address);
    }

    @Nonnull
    public static PointerDoubleFunc nref(@Nonnull final Testing.DoubleFunc function) {
        if (function instanceof PointerDoubleFunc) {
            return (PointerDoubleFunc) function;
        }
        return new DoubleFunc_Jaccall_J(function);
    }
}