package com.github.zubnix.libtest;

import com.github.zubnix.jaccall.JNI;

import javax.annotation.Generated;

@Generated("com.github.zubnix.jaccall.compiletime.funcptr.FunctionPointerGenerator")
final class UnsignedLongLongTest_Jaccall_C extends PointerUnsignedLongLongTest {

    static {
        JNI.linkFuncPtr(UnsignedLongLongTest_Jaccall_C.class,
                        "_$",
                        2,
                        "(JJ)J",
                        FFI_CIF);
    }

    UnsignedLongLongTest_Jaccall_C(final long address) {
        super(address);
    }

    @Override
    public long $(final long value) {
        return _$(this.address,
                  value);
    }

    private static native long _$(final long address,
                                  final long value);
}
