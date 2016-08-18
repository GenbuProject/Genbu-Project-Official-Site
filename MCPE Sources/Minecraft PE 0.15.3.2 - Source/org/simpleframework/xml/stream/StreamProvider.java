package org.simpleframework.xml.stream;

import java.io.InputStream;
import java.io.Reader;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

class StreamProvider
  implements Provider
{
  private final XMLInputFactory factory = XMLInputFactory.newInstance();
  
  private EventReader provide(XMLEventReader paramXMLEventReader)
    throws Exception
  {
    return new StreamReader(paramXMLEventReader);
  }
  
  public EventReader provide(InputStream paramInputStream)
    throws Exception
  {
    return provide(this.factory.createXMLEventReader(paramInputStream));
  }
  
  public EventReader provide(Reader paramReader)
    throws Exception
  {
    return provide(this.factory.createXMLEventReader(paramReader));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\StreamProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */