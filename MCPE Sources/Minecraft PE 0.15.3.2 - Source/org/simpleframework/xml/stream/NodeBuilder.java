package org.simpleframework.xml.stream;

import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;

public final class NodeBuilder
{
  private static Provider PROVIDER = ;
  
  public static InputNode read(InputStream paramInputStream)
    throws Exception
  {
    return read(PROVIDER.provide(paramInputStream));
  }
  
  public static InputNode read(Reader paramReader)
    throws Exception
  {
    return read(PROVIDER.provide(paramReader));
  }
  
  private static InputNode read(EventReader paramEventReader)
    throws Exception
  {
    return new NodeReader(paramEventReader).readRoot();
  }
  
  public static OutputNode write(Writer paramWriter)
    throws Exception
  {
    return write(paramWriter, new Format());
  }
  
  public static OutputNode write(Writer paramWriter, Format paramFormat)
    throws Exception
  {
    return new NodeWriter(paramWriter, paramFormat).writeRoot();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\NodeBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */