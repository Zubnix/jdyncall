package org.freedesktop.jaccall;


import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public final class JNI {

    private static final Logger LOGGER = Logger.getLogger("jaccall");

    //TODO add android
    private static final String[] ARCHS = {"linux-aarch64",
                                           "linux-armv7hf",
                                           "linux-armv7sf",
                                           "linux-armv6hf",
                                           "linux-x86_64",
                                           "linux-i686",
                                           //last resort
                                           "native"};
    private static final String   LIB   = "libjaccall.so";

    static {
        //there is no real good or correct way to determine the userland+os+architecture in Java :(
        if (ConfigVariables.JACCALL_ARCH == null) {
            LOGGER.info(String.format("Jaccall might not work correctly, arch not specified by JACCALL_ARCH environment variable, please specify it. Supported values are: %s",
                                      Arrays.toString(ARCHS)));

            Map<String, LinkageError> failures = new HashMap<>();

            boolean libLoaded  = false;
            String  loadedArch = "";

            for (String arch : ARCHS) {

                try {
                    loadLibrary(arch);
                }
                catch (LinkageError e) {
                    LOGGER.info(String.format("Loading lib for arch %s failed. Trying next arch.",
                                              arch));
                    failures.put(arch,
                                 e);
                    continue;
                }

                loadedArch = arch;
                libLoaded = true;
                break;
            }

            if (!libLoaded) {
                for (Map.Entry<String, LinkageError> errorEntry : failures.entrySet()) {
                    System.err.println("Could not load lib for arch " + errorEntry.getKey());
                    errorEntry.getValue()
                              .printStackTrace();
                }

                throw new Error("Failed to load any of the libs for ARCHS: " + Arrays.toString(ARCHS));
            }
            else {
                LOGGER.info(String.format("Successfully loaded lib for arch %s.",
                                          loadedArch));
            }

        }
        else {
            loadLibrary(ConfigVariables.JACCALL_ARCH);
        }
    }

    private static void loadLibrary(String arch) throws LinkageError {
        try {
            final InputStream libStream = JNI.class.getClassLoader()
                                                   .getResourceAsStream(arch + "/" + LIB);
            if (libStream == null) {
                //lib not found
                throw new LinkageError(String.format("Lib for arch %s not found.",
                                                     arch));
            }

            final File tempFile = File.createTempFile(LIB,
                                                      null);
            tempFile.deleteOnExit();
            unpack(libStream,
                   tempFile);
            System.load(tempFile.getAbsolutePath());
        }
        catch (IOException e) {
            throw new Error(e);
        }
    }

    private static void unpack(final InputStream libStream,
                               final File tempFile) throws IOException {
        final FileOutputStream fos    = new FileOutputStream(tempFile);
        final byte[]           buffer = new byte[4096];
        int                    read;
        while ((read = libStream.read(buffer)) != -1) {
            fos.write(buffer,
                      0,
                      read);
        }
        fos.close();
        libStream.close();
    }

    private JNI() {
    }

    /*
     * JNI ->
     */
    public static native ByteBuffer wrap(long address,
                                         @Nonnegative long size);

    public static native long unwrap(@Nonnull Buffer byteBuffer);

    public static native long NewGlobalRef(@Nonnull final Object object);

    public static native void DeleteGlobalRef(final long object);

    public static native long GetMethodID(final Class<?> clazz,
                                          final String methodName,
                                          final String jniSignature);

    public static native Object toObject(final long jobject);
    /*
     * <- JNI
     */

    /*
     * std ->
     */
    public static native long realloc(long address,
                                      @Nonnegative int size);

    public static native long malloc(@Nonnegative int size);

    public static native long calloc(@Nonnegative int nmemb,
                                     @Nonnegative int size);

    public static native void free(long address);

    public static native int sizeOfPointer();

    public static native int sizeOfCLong();

    public static final int CHAR_ALIGNMENT = JNI.charAlignment();

    private static native int charAlignment();

    public static final int SHORT_ALIGNMENT = JNI.shortAlignment();

    private static native int shortAlignment();

    public static final int INT_ALIGNMENT = JNI.intAlignment();

    private static native int intAlignment();

    public static final int LONG_ALIGNMENT = JNI.longAlignment();

    private static native int longAlignment();

    public static final int LONG_LONG_ALIGNMENT = JNI.longLongAlignment();

    private static native int longLongAlignment();

    public static final int POINTER_ALIGNMENT = JNI.pointerAlignment();

    private static native int pointerAlignment();

    public static final int FLOAT_ALIGNMENT = JNI.floatAlignment();

    private static native int floatAlignment();

    public static final int DOUBLE_ALIGNMENT = JNI.doubleAlignment();

    private static native int doubleAlignment();

    /*
     * <- std
     */

    /*
     * FFI ->
     */
    public static final long FFI_TYPE_VOID = ffi_type_void();

    private static native long ffi_type_void();

    public static final long FFI_TYPE_SINT8 = ffi_type_sint8();

    private static native long ffi_type_sint8();

    public static final long FFI_TYPE_UINT8 = ffi_type_uint8();

    private static native long ffi_type_uint8();

    public static final long FFI_TYPE_SINT16 = ffi_type_sint16();

    private static native long ffi_type_sint16();

    public static final long FFI_TYPE_UINT16 = ffi_type_uint16();

    private static native long ffi_type_uint16();

    public static final long FFI_TYPE_SINT32 = ffi_type_sint32();

    private static native long ffi_type_sint32();

    public static final long FFI_TYPE_UINT32 = ffi_type_uint32();

    private static native long ffi_type_uint32();

    public static final long FFI_TYPE_SLONG = ffi_type_slong();

    private static native long ffi_type_slong();

    public static final long FFI_TYPE_ULONG = ffi_type_ulong();

    private static native long ffi_type_ulong();

    public static final long FFI_TYPE_SINT64 = ffi_type_sint64();

    private static native long ffi_type_sint64();

    public static final long FFI_TYPE_UINT64 = ffi_type_uint64();

    private static native long ffi_type_uint64();

    public static final long FFI_TYPE_FLOAT = ffi_type_float();

    private static native long ffi_type_float();

    public static final long FFI_TYPE_DOUBLE = ffi_type_double();

    private static native long ffi_type_double();

    public static final long FFI_TYPE_POINTER = ffi_type_pointer();

    private static native long ffi_type_pointer();

    public static native long ffi_type_struct(long... ffiTypes);

    public static native long ffi_type_union(long... ffiTypes);

    @Nonnegative
    public static native int ffi_type_struct_size(long ffiStructType);

    public static native long ffi_callInterface(long return_type,
                                                long... arg_types);

    public static native long ffi_closure(final long ffiCif,
                                          @Nonnull final Object function,
                                          final long jniMethodId);

    public static native void ffi_closure_free(final long address);
    /*
     * <- ffi
     */

    /*
     * linker ->
     */

    static native void link(@Nonnull String library, /* library path */
                            @Nonnull Class<?> header,/*class with native methods*/
                            @Nonnull String[] symbols,/*method names*/
                            @Nonnull byte[] argumentSizes,/*number of arguments for each method*/
                            @Nonnull String[] jniSignatures,/*jni method signatures*/
                            @Nonnull long[] ffiCallInterfaces/*array of ffi type pointers*/);

    public static native void linkFuncPtr(@Nonnull Class<?> wrapper,/*class with native method*/
                                          @Nonnull String symbol,/*method name*/
                                          @Nonnegative int argumentSize,/*number of arguments for the method*/
                                          @Nonnull String jniSignature,/*jni method signature*/
                                          long ffiCallInterface/*ffi call interface*/);

    /*
     * <- linker
     */

    /*
     * raw io ->
     */
    public static native byte readByte(final long address,
                                       final int index);

    public static native void writeByte(final long address,
                                        final int index,
                                        final byte b);

    public static native void writeBytes(final long address,
                                         final byte[] val);

    public static native long readCLong(final long address,
                                        final int index);

    public static native void writeCLong(final long address,
                                         final int index,
                                         final long l);

    public static native double readDouble(final long address,
                                           final int index);

    public static native void writeDouble(final long address,
                                          final int index,
                                          final double v);

    public static native void writeDoubles(final long address,
                                           final double[] val);

    public static native float readFloat(final long address,
                                         final int index);

    public static native void writeFloat(final long address,
                                         final int index,
                                         final float v);

    public static native void writeFloats(final long address,
                                          final float[] val);

    public static native int readInt(final long address,
                                     final int index);

    public static native void writeInt(final long address,
                                       final int index,
                                       final int i);

    public static native void writeInts(final long address,
                                        final int[] val);

    public static native long readLong(final long address,
                                       final int index);

    public static native void writeLong(final long address,
                                        final int index,
                                        final long val);

    public static native void writeLongs(final long address,
                                         final long[] val);

    public static native long readPointer(final long address,
                                          final int index);

    public static native void writePointer(final long address,
                                           final int index,
                                           final long address1);

    public static native short readShort(final long address,
                                         final int index);

    public static native void writeShort(final long address,
                                         final int index,
                                         final short i);

    public static native void writeShorts(final long address,
                                          final short[] val);

    public static native String readString(final long address,
                                           final int index);

    public static native void writeString(final long address,
                                          final int index,
                                          final String val);

    public static native void writeStruct(final long targetAddress,
                                          final long sourceAddress,
                                          final int size);


    /*
     * <- raw io
     */
}
