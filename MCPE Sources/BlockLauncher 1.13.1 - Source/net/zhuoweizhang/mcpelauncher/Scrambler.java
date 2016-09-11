package net.zhuoweizhang.mcpelauncher;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

class Scrambler
{
  static native void nativeScramble(ByteBuffer paramByteBuffer, MpepInfo paramMpepInfo);
  
  static Reader scramble(byte[] paramArrayOfByte, MpepInfo paramMpepInfo)
    throws IOException
  {
    ByteBuffer localByteBuffer = ByteBuffer.allocateDirect(paramArrayOfByte.length);
    localByteBuffer.put(paramArrayOfByte);
    localByteBuffer.rewind();
    nativeScramble(localByteBuffer, paramMpepInfo);
    paramArrayOfByte = new byte[paramArrayOfByte.length];
    localByteBuffer.get(paramArrayOfByte);
    return new StringReader(new String(paramArrayOfByte, Charset.forName("UTF-8")));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\Scrambler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */