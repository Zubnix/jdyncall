package com.github.zubnix.libtest;

import com.github.zubnix.jaccall.JNI;

import javax.annotation.Generated;

@Generated("com.github.zubnix.jaccall.compiletime.funcptr.FunctionPointerGenerator")
final class UnsignedShortTest_Jaccall_J extends PointerUnsignedShortTest {

    private static final long JNI_METHOD_ID = JNI.GetMethodID(Testing.UnsignedShortTest.class,
                                                              "$",
                                                              "(S)S");
    private final Testing.UnsignedShortTest function;

    public UnsignedShortTest_Jaccall_J(final Testing.UnsignedShortTest function) {
        super(JNI.ffi_closure(PointerShortTest.FFI_CIF,
                              function,
                              JNI_METHOD_ID));
        this.function = function;
    }


    @Override
    public short $(final short value) {
        return this.function.$(value);
    }
}