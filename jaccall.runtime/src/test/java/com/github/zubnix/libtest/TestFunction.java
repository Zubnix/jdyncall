package com.github.zubnix.libtest;

import com.github.zubnix.jaccall.ByVal;
import com.github.zubnix.jaccall.Func;
import com.github.zubnix.jaccall.FuncPtr;
import com.github.zubnix.jaccall.Ptr;
import com.github.zubnix.jaccall.Unsigned;

@FuncPtr
public interface TestFunction extends Func {

    byte $(@Ptr long arg0,
           @Unsigned int arg1,
           @ByVal(TestStruct.class) long arg2);
}
