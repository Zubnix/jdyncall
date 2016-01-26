package com.github.zubnix.libtest;

import com.github.zubnix.jaccall.JNI;

import javax.annotation.Generated;

@Generated("com.github.zubnix.jaccall.compiletime.funcptr.FunctionPointerGenerator")
final class UnsignedCharFunc_Jaccall_J extends PointerUnsignedCharFunc {

    private static final long JNI_METHOD_ID = JNI.GetMethodID(Testing.UnsignedCharFunc.class,
                                                              "$",
                                                              "(B)B");
    private final Testing.UnsignedCharFunc function;

    public UnsignedCharFunc_Jaccall_J(final Testing.UnsignedCharFunc function) {
        super(JNI.ffi_closure(FFI_CIF,
                              function,
                              JNI_METHOD_ID));
        this.function = function;
    }


    @Override
    public byte $(final byte value) {
        return this.function.$(value);
    }
}