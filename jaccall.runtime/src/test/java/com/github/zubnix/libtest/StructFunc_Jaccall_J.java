package com.github.zubnix.libtest;

import com.github.zubnix.jaccall.JNI;

import javax.annotation.Generated;

@Generated("com.github.zubnix.jaccall.compiletime.funcptr.FunctionPointerGenerator")
final class StructFunc_Jaccall_J extends PointerStructFunc {

    private static final long JNI_METHOD_ID = JNI.GetMethodID(Testing.StructFunc.class,
                                                              "$",
                                                              "(JBSJJJF)J");
    private final Testing.StructFunc function;

    public StructFunc_Jaccall_J(final Testing.StructFunc function) {
        super(JNI.ffi_closure(FFI_CIF,
                              function,
                              JNI_METHOD_ID));
        this.function = function;
    }


    @Override
    public long $(long tst,
                  byte field0,
                  short field1,
                  long field2,
                  long field3,
                  long embedded_field0,
                  float embedded_field1) {
        return this.function.$(tst,
                               field0,
                               field1,
                               field2,
                               field3,
                               embedded_field0,
                               embedded_field1);
    }
}