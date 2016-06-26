package com.microsoft.onlineid.sts.response.parsers;

import android.text.TextUtils;
import com.microsoft.onlineid.sts.exception.StsParseException;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public abstract class BasePullParser
{
  private boolean _parseCalled = false;
  protected final XmlPullParser _parser;
  protected final String _parserTag;
  protected final String _parserTagNamespace;
  private final NodeScope _scope;
  
  public BasePullParser(XmlPullParser paramXmlPullParser, String paramString1, String paramString2)
  {
    this._parser = paramXmlPullParser;
    this._parserTag = paramString2;
    this._parserTagNamespace = paramString1;
    this._scope = getLocation();
  }
  
  private void finish()
    throws XmlPullParserException, IOException
  {
    this._scope.finish();
  }
  
  private String getPrefixedAttributeName(int paramInt)
    throws XmlPullParserException
  {
    int i = this._parser.getEventType();
    if ((i != 2) && (i != 3)) {
      throw new XmlPullParserException("Attribute should only be retrieved on a start or end tag.");
    }
    if ((paramInt < 0) || (paramInt >= this._parser.getAttributeCount())) {
      throw new XmlPullParserException("Invalid attribute location.");
    }
    String str1 = this._parser.getAttributePrefix(paramInt);
    String str2 = this._parser.getAttributeName(paramInt);
    if (TextUtils.isEmpty(str1)) {
      return str2;
    }
    return str1 + ":" + str2;
  }
  
  private String getPrefixedNamespaceName(int paramInt)
    throws XmlPullParserException
  {
    int i = this._parser.getEventType();
    if ((i != 2) && (i != 3)) {
      throw new XmlPullParserException("Namespace name should only be retrieved on a start or end tag.");
    }
    i = this._parser.getDepth();
    if ((paramInt < this._parser.getNamespaceCount(i - 1)) || (paramInt >= this._parser.getNamespaceCount(i))) {
      throw new XmlPullParserException("Invalid namespace location.");
    }
    String str = this._parser.getNamespacePrefix(paramInt);
    if (TextUtils.isEmpty(str)) {
      return "xmlns";
    }
    return "xmlns:" + str;
  }
  
  protected static String getPrefixedTagName(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException
  {
    int i = paramXmlPullParser.getEventType();
    if ((i != 2) && (i != 3)) {
      throw new XmlPullParserException("Tag name should only be retrieved on a start or end tag.");
    }
    String str = paramXmlPullParser.getPrefix();
    paramXmlPullParser = paramXmlPullParser.getName();
    if (TextUtils.isEmpty(str)) {
      return paramXmlPullParser;
    }
    return str + ":" + paramXmlPullParser;
  }
  
  protected NodeScope getLocation()
  {
    return new NodeScope(this._parser);
  }
  
  protected String getPrefixedTagName()
    throws XmlPullParserException
  {
    return getPrefixedTagName(this._parser);
  }
  
  protected boolean hasMore()
    throws XmlPullParserException
  {
    return this._scope.hasMore();
  }
  
  protected String nextRequiredText()
    throws XmlPullParserException, IOException, StsParseException
  {
    return this._scope.nextRequiredText();
  }
  
  protected void nextStartTag(String paramString)
    throws XmlPullParserException, IOException, StsParseException
  {
    this._scope.nextStartTag(paramString);
  }
  
  protected boolean nextStartTagNoThrow()
    throws XmlPullParserException, IOException
  {
    return this._scope.nextStartTagNoThrow();
  }
  
  protected boolean nextStartTagNoThrow(String paramString)
    throws XmlPullParserException, IOException
  {
    return this._scope.nextStartTagNoThrow(paramString);
  }
  
  protected abstract void onParse()
    throws XmlPullParserException, IOException, StsParseException;
  
  public final void parse()
    throws IOException, StsParseException
  {
    try
    {
      if (this._parseCalled) {
        throw new IllegalStateException("Parse has already been called.");
      }
    }
    catch (XmlPullParserException localXmlPullParserException)
    {
      throw new StsParseException("XML was either invalid or failed to parse.", localXmlPullParserException, new Object[0]);
    }
    this._parseCalled = true;
    if (this._scope.getDepth() == 0)
    {
      this._parser.require(0, null, null);
      this._parser.next();
    }
    this._parser.require(2, this._parserTagNamespace, this._parserTag);
    onParse();
    finish();
  }
  
  protected String readRawOuterXml()
    throws XmlPullParserException, IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    NodeScope localNodeScope = getLocation();
    if (localNodeScope.hasMore())
    {
      switch (this._parser.getEventType())
      {
      }
      for (;;)
      {
        this._parser.next();
        break;
        localStringBuilder.append('<').append(getPrefixedTagName());
        int i = this._parser.getNamespaceCount(this._parser.getDepth() - 1);
        int j = this._parser.getNamespaceCount(this._parser.getDepth());
        while (i < j)
        {
          localStringBuilder.append(' ').append(getPrefixedNamespaceName(i)).append("=\"").append(this._parser.getNamespaceUri(i)).append('"');
          i += 1;
        }
        i = 0;
        while (i < this._parser.getAttributeCount())
        {
          localStringBuilder.append(' ').append(getPrefixedAttributeName(i)).append("=\"").append(this._parser.getAttributeValue(i)).append('"');
          i += 1;
        }
        localStringBuilder.append('>');
        continue;
        localStringBuilder.append(this._parser.getText());
        continue;
        localStringBuilder.append("</").append(getPrefixedTagName(this._parser)).append('>');
      }
    }
    localStringBuilder.append("</").append(getPrefixedTagName(this._parser)).append('>');
    return localStringBuilder.toString();
  }
  
  protected void skipElement()
    throws XmlPullParserException, IOException
  {
    this._scope.skipElement();
  }
  
  protected void verifyParseCalled()
  {
    if (!this._parseCalled) {
      throw new IllegalStateException("Cannot call this method without calling parse.");
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\sts\response\parsers\BasePullParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */