package com.github.zubnix.jaccall;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static com.google.common.truth.Truth.assertThat;

public class PointerTest {

    @Test
    public void testWrapByteBuffer() throws Exception {
        //given
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(5);
        byteBuffer.put(new byte[]{
                1, 1, 2, 3, 5
        });

        //when
        final Pointer<Void> voidPointer = Pointer.wrap(byteBuffer);

        //then
        final long address = voidPointer.tCast(Long.class);
        assertThat(address).isNotEqualTo(0L);
    }

    @Test
    public void testWrapTypedByteBuffer() throws Exception {
        //given
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * 3)
                                          .order(ByteOrder.nativeOrder());

        int int0 = 4;
        int int1 = 5;
        int int2 = -10;

        byteBuffer.asIntBuffer()
                  .put(new int[]{int0, int1, int2});

        //when
        final Pointer<Integer> intPointer = Pointer.wrap(Integer.class,
                                                         byteBuffer);

        //then
        final Integer integer0 = intPointer.dref();
        assertThat(integer0).isEqualTo(int0);

        final Integer integer1 = intPointer.dref(1);
        assertThat(integer1).isEqualTo(int1);

        final Integer integer2 = intPointer.dref(2);
        assertThat(integer2).isEqualTo(int2);
    }

    @Test
    public void testWrapAddress() throws Exception {
        //given
        final long pointer = JNITestUtil.byteArrayAsPointer(123,
                                                            -94,
                                                            43,
                                                            58,
                                                            0xFF);

        //when
        final Pointer<Void> voidPointer = Pointer.wrap(pointer);

        //then
        assertThat(voidPointer.tCast(Long.class)).isEqualTo(pointer);
    }

    @Test
    public void testWrapTypedAddress() throws Exception {
        //given
        byte b0 = 123;
        byte b1 = -94;
        byte b2 = 43;
        byte b3 = 58;
        byte b4 = (byte) 0xFF;

        final long pointer = JNITestUtil.byteArrayAsPointer(b0,
                                                            b1,
                                                            b2,
                                                            b3,
                                                            b4);
        final long pointerOfPointer = JNITestUtil.pointerOfPointer(pointer);

        //when
        try (final Pointer<Byte> bytePointer = Pointer.wrap(Byte.class,
                                                            pointer);

             final Pointer<Pointer<Byte>> bytePointerPointer = Pointer.wrap(Byte.class,
                                                                            pointer)
                                                                      .ppCast();

             final Pointer<Pointer> pointerPointer = Pointer.wrap(Pointer.class,
                                                                  pointerOfPointer)) {

            //then
            assertThat(bytePointer.dref()).isEqualTo(b0);
            assertThat(bytePointer.dref(1)).isEqualTo(b1);
            assertThat(bytePointer.dref(2)).isEqualTo(b2);
            assertThat(bytePointer.dref(3)).isEqualTo(b3);
            assertThat(bytePointer.dref(4)).isEqualTo(b4);

            //throws error complaining about incomplete type
            final Object val = pointerPointer.dref()
                                             .dref();
        }
        catch (Exception e) {

        }
    }

    @Test
    public void testMalloc() throws Exception {

    }

    @Test
    public void testCalloc() throws Exception {

    }

    @Test
    public void testRefStructType() throws Exception {

    }

    @Test
    public void testRefPointer() throws Exception {

    }

    @Test
    public void testRefByte() throws Exception {

    }

    @Test
    public void testRefShort() throws Exception {

    }

    @Test
    public void testRefChar() throws Exception {

    }

    @Test
    public void testRefInt() throws Exception {

    }

    @Test
    public void testRefFloat() throws Exception {

    }

    @Test
    public void testRefLong() throws Exception {

    }

    @Test
    public void testClose() throws Exception {

    }

    @Test
    public void testOffset() throws Exception {

    }

    @Test
    public void testTCast() throws Exception {

    }

    @Test
    public void testPtCast() throws Exception {

    }

    @Test
    public void testPpCast() throws Exception {

    }

    @Test
    public void testWriteByte() throws Exception {

    }

    @Test
    public void testWriteByteAtIndex() throws Exception {

    }

    @Test
    public void testWriteShort() throws Exception {

    }

    @Test
    public void testWriteShortAtIndex() throws Exception {

    }

    @Test
    public void testWriteChar() throws Exception {

    }

    @Test
    public void testWriteCharAtIndex() throws Exception {

    }

    @Test
    public void testWriteInt() throws Exception {

    }

    @Test
    public void testWriteIntAtIndex() throws Exception {

    }

    @Test
    public void testWriteFloat() throws Exception {

    }

    @Test
    public void testWriteFloatAtIndex() throws Exception {

    }

    @Test
    public void testWriteLong() throws Exception {

    }

    @Test
    public void testWriteLongAtIndex() throws Exception {

    }

    @Test
    public void testWritePointer() throws Exception {

    }

    @Test
    public void testWritePointerAtIndex() throws Exception {

    }

    @Test
    public void testWriteCLong() throws Exception {

    }

    @Test
    public void testWriteCLongAtIndex() throws Exception {

    }

    @Test
    public void testWriteStruct() throws Exception {

    }

    @Test
    public void testWriteStructAtIndex() throws Exception {

    }

    @Test
    public void testToClass() throws Exception {

    }
}