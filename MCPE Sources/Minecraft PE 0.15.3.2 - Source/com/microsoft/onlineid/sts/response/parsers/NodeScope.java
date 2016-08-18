package com.microsoft.onlineid.sts.response.parsers;

import android.text.TextUtils;
import com.microsoft.onlineid.sts.exception.StsParseException;
import java.io.IOException;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

class NodeScope
{
  private final int _depth;
  private final XmlPullParser _parser;
  
  NodeScope(XmlPullParser paramXmlPullParser)
  {
    this._parser = paramXmlPullParser;
    this._depth = paramXmlPullParser.getDepth();
  }
  
  void finish()
    throws XmlPullParserException, IOException
  {
    while (hasMore()) {
      this._parser.next();
    }
  }
  
  int getDepth()
  {
    return this._depth;
  }
  
  boolean hasMore()
    throws XmlPullParserException
  {
    boolean bool = false;
    switch (this._parser.getEventType())
    {
    }
    do
    {
      bool = true;
      return bool;
    } while (this._depth != this._parser.getDepth());
    return false;
  }
  
  String nextRequiredText()
    throws XmlPullParserException, IOException, StsParseException
  {
    String str1 = this._parser.getName();
    String str2 = this._parser.nextText();
    if (TextUtils.isEmpty(str2)) {
      throw new StsParseException(String.format(Locale.US, "Expected text of %s is empty", new Object[] { str1 }), new Object[0]);
    }
    return str2;
  }
  
  void nextStartTag(String paramString)
    throws XmlPullParserException, IOException, StsParseException
  {
    if (!nextStartTagNoThrow(paramString)) {
      throw new StsParseException("Required node \"%s\" is missing.", new Object[] { paramString });
    }
  }
  
  boolean nextStartTagNoThrow()
    throws XmlPullParserException, IOException
  {
    while (hasMore()) {
      if (this._parser.next() == 2) {
        return true;
      }
    }
    return false;
  }
  
  boolean nextStartTagNoThrow(String paramString)
    throws XmlPullParserException, IOException
  {
    while (nextStartTagNoThrow())
    {
      if (BasePullParser.getPrefixedTagName(this._parser).equals(paramString)) {
        return true;
      }
      skipElement();
    }
    return false;
  }
  
  protected void skipElement()
    throws XmlPullParserException, IOException
  {
    int j = this._parser.getDepth();
    if (j == this._depth) {
      finish();
    }
    for (;;)
    {
      return;
      for (int i = this._parser.getEventType(); (j != this._parser.getDepth()) || (i != 3); i = this._parser.next()) {}
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\sts\response\parsers\NodeScope.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */