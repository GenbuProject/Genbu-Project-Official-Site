package net.zhuoweizhang.mcpelauncher.patch;

import java.util.Arrays;

public class PatchSegment
{
  public byte[] data;
  public int offset;
  
  public PatchSegment(int paramInt, byte[] paramArrayOfByte)
  {
    this.offset = paramInt;
    this.data = paramArrayOfByte;
  }
  
  public String toString()
  {
    return super.toString() + " offset=" + Integer.toString(this.offset, 16) + " data=" + Arrays.toString(this.data);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\patch\PatchSegment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */