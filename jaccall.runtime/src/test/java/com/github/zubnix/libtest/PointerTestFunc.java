package com.github.zubnix.libtest;


import com.github.zubnix.jaccall.JNI;
import com.github.zubnix.jaccall.PointerFunc;

import javax.annotation.Generated;
import javax.annotation.Nonnull;

@Generated("com.github.zubnix.jaccall.compiletime.funcptr.FunctionPointerGenerator")
public abstract class PointerTestFunc extends PointerFunc<PointerTestFunc> implements TestFunc {

    static final long FFI_CIF = JNI.ffi_callInterface(JNI.FFI_TYPE_SINT8,
                                                      JNI.FFI_TYPE_POINTER,
                                                      JNI.FFI_TYPE_UINT32,
                                                      TestStruct.FFI_TYPE);

    PointerTestFunc(final long address) {
        super(PointerTestFunc.class,
              address);
    }

    @Nonnull
    public static PointerTestFunc wrapFunc(final long address) {
        return new TestFunc_Jaccall_C(address);
    }

    @Nonnull
    public static PointerTestFunc nref(@Nonnull final TestFunc function) {
        if (function instanceof TestFunc_Jaccall_J) {
            return (PointerTestFunc) function;
        }
        return new TestFunc_Jaccall_J(function);
    }
}