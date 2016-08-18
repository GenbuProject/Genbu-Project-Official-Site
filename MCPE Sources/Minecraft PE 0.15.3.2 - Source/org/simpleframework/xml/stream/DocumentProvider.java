package org.simpleframework.xml.stream;

import java.io.InputStream;
import java.io.Reader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.InputSource;

class DocumentProvider
  implements Provider
{
  private final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
  
  public DocumentProvider()
  {
    this.factory.setNamespaceAware(true);
  }
  
  private EventReader provide(InputSource paramInputSource)
    throws Exception
  {
    return new DocumentReader(this.factory.newDocumentBuilder().parse(paramInputSource));
  }
  
  public EventReader provide(InputStream paramInputStream)
    throws Exception
  {
    return provide(new InputSource(paramInputStream));
  }
  
  public EventReader provide(Reader paramReader)
    throws Exception
  {
    return provide(new InputSource(paramReader));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\DocumentProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */