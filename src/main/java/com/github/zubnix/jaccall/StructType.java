package com.github.zubnix.jaccall;


import java.nio.ByteBuffer;

import static com.github.zubnix.jaccall.Size.sizeof;
import static java.nio.ByteBuffer.allocate;

public abstract class StructType {

    protected static int SIZE;

    private ByteBuffer buffer;

    protected ByteBuffer buffer() {
        if (this.buffer == null) {
            buffer(allocate(sizeof(getClass())));
        }
        return this.buffer;
    }

    void buffer(final ByteBuffer buffer) {
        buffer.rewind();
        buffer.clear();
        this.buffer = buffer;
    }
}
