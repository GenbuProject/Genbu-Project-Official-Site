package org.simpleframework.xml.stream;

import java.io.InputStream;
import java.io.Reader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

class PullProvider
  implements Provider
{
  private final XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
  
  public PullProvider()
    throws Exception
  {
    this.factory.setNamespaceAware(true);
  }
  
  public EventReader provide(InputStream paramInputStream)
    throws Exception
  {
    XmlPullParser localXmlPullParser = this.factory.newPullParser();
    if (paramInputStream != null) {
      localXmlPullParser.setInput(paramInputStream, null);
    }
    return new PullReader(localXmlPullParser);
  }
  
  public EventReader provide(Reader paramReader)
    throws Exception
  {
    XmlPullParser localXmlPullParser = this.factory.newPullParser();
    if (paramReader != null) {
      localXmlPullParser.setInput(paramReader);
    }
    return new PullReader(localXmlPullParser);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\PullProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */