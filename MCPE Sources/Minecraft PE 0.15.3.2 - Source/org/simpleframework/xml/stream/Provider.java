package org.simpleframework.xml.stream;

import java.io.InputStream;
import java.io.Reader;

abstract interface Provider
{
  public abstract EventReader provide(InputStream paramInputStream)
    throws Exception;
  
  public abstract EventReader provide(Reader paramReader)
    throws Exception;
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\Provider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */