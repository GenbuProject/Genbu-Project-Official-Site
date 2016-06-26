package com.microsoft.bond;

import java.io.IOException;

public class Transcoder
{
  public static void transcode(ProtocolWriter paramProtocolWriter, ProtocolReader paramProtocolReader)
    throws IOException
  {
    if ((paramProtocolReader.hasCapability(ProtocolCapability.CAN_SEEK)) && (paramProtocolReader.isProtocolSame(paramProtocolWriter)))
    {
      int i = paramProtocolReader.getPosition();
      paramProtocolReader.skip(BondDataType.BT_STRUCT);
      int j = paramProtocolReader.getPosition();
      paramProtocolReader.setPosition(i);
      paramProtocolWriter.writeBlob(paramProtocolReader.readBlob(j - i));
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\bond\Transcoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */