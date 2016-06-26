package com.ipaulpro.afilechooser.utils;

import android.content.res.XmlResourceParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class MimeTypeParser
{
  public static final String ATTR_EXTENSION = "extension";
  public static final String ATTR_MIMETYPE = "mimetype";
  public static final String TAG_MIMETYPES = "MimeTypes";
  public static final String TAG_TYPE = "type";
  private MimeTypes mMimeTypes;
  private XmlPullParser mXpp;
  
  private void addMimeTypeStart()
  {
    String str1 = this.mXpp.getAttributeValue(null, "extension");
    String str2 = this.mXpp.getAttributeValue(null, "mimetype");
    this.mMimeTypes.put(str1, str2);
  }
  
  public MimeTypes fromXml(InputStream paramInputStream)
    throws XmlPullParserException, IOException
  {
    this.mXpp = XmlPullParserFactory.newInstance().newPullParser();
    this.mXpp.setInput(new InputStreamReader(paramInputStream));
    return parse();
  }
  
  public MimeTypes fromXmlResource(XmlResourceParser paramXmlResourceParser)
    throws XmlPullParserException, IOException
  {
    this.mXpp = paramXmlResourceParser;
    return parse();
  }
  
  public MimeTypes parse()
    throws XmlPullParserException, IOException
  {
    this.mMimeTypes = new MimeTypes();
    int i = this.mXpp.getEventType();
    if (i != 1)
    {
      String str = this.mXpp.getName();
      if (i == 2) {
        if (!str.equals("MimeTypes")) {}
      }
      for (;;)
      {
        i = this.mXpp.next();
        break;
        if (str.equals("type"))
        {
          addMimeTypeStart();
          continue;
          if ((i != 3) || (!str.equals("MimeTypes"))) {}
        }
      }
    }
    return this.mMimeTypes;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\ipaulpro\afilechooser\utils\MimeTypeParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */