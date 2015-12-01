package com.github.zubnix.jaccall;


import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.nio.ByteBuffer;

public class JNI {

    /*
     * JNI ->
     */
    static native ByteBuffer wrap(long address,
                                  @Nonnegative long size);

    static native long unwrap(@Nonnull ByteBuffer byteBuffer);
    /*
     * <- JNI
     */

    /*
     * std ->
     */
    static native int malloc(@Nonnegative long size);

    static native int calloc(final int nmemb,
                             @Nonnegative final int size);

    static native void free(final long address);

    static native int sizeOfPointer();

    static native int sizeOfCLong();
    /*
     * <- std
     */

    /*
     * dyncall ->
     */
    public static native int dcStructSize(long dcStruct);

    public static native long dcDefineStruct(@Nonnull String signature);

    public static native ByteBuffer dcStructFieldOffsets(final long dcStruct,
                                                         @Nonnull final ByteBuffer offsets);
    /*
     * <- dyncall
     */
}