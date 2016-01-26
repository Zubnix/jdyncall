package com.github.zubnix.libtest;


import com.github.zubnix.jaccall.JNI;
import com.github.zubnix.jaccall.PointerFunc;

import javax.annotation.Generated;
import javax.annotation.Nonnull;

@Generated("com.github.zubnix.jaccall.compiletime.funcptr.FunctionPointerGenerator")
public abstract class PointerUnsignedCharFunc extends PointerFunc<PointerUnsignedCharFunc> implements Testing.UnsignedCharFunc {

    static final long FFI_CIF = JNI.ffi_callInterface(JNI.FFI_TYPE_UINT8,
                                                      JNI.FFI_TYPE_UINT8);

    PointerUnsignedCharFunc(final long address) {
        super(PointerUnsignedCharFunc.class,
              address);
    }

    @Nonnull
    public static PointerUnsignedCharFunc wrapFunc(final long address) {
        return new UnsignedCharFunc_Jaccall_C(address);
    }

    @Nonnull
    public static PointerUnsignedCharFunc nref(@Nonnull final Testing.UnsignedCharFunc function) {
        if (function instanceof PointerUnsignedCharFunc) {
            return (PointerUnsignedCharFunc) function;
        }
        return new UnsignedCharFunc_Jaccall_J(function);
    }
}