package com.github.zubnix.libtest;

import com.github.zubnix.jaccall.JNI;

import javax.annotation.Generated;

@Generated("com.github.zubnix.jaccall.compiletime.funcptr.FunctionPointerGenerator")
final class IntFunc_Jaccall_C extends PointerIntFunc {

    static {
        JNI.linkFuncPtr(IntFunc_Jaccall_C.class,
                        "_$",
                        2,
                        "(JI)I",
                        FFI_CIF);
    }

    IntFunc_Jaccall_C(final long address) {
        super(address);
    }

    @Override
    public int $(final int value) {
        return _$(this.address,
                  value);
    }

    private static native int _$(final long address,
                                 final int value);
}