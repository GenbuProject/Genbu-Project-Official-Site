package org.simpleframework.xml.stream;

import org.xmlpull.v1.XmlPullParser;

class PullReader
  implements EventReader
{
  private XmlPullParser parser;
  private EventNode peek;
  
  public PullReader(XmlPullParser paramXmlPullParser)
  {
    this.parser = paramXmlPullParser;
  }
  
  private Entry attribute(int paramInt)
    throws Exception
  {
    return new Entry(this.parser, paramInt);
  }
  
  private Start build(Start paramStart)
    throws Exception
  {
    int j = this.parser.getAttributeCount();
    int i = 0;
    while (i < j)
    {
      Entry localEntry = attribute(i);
      if (!localEntry.isReserved()) {
        paramStart.add(localEntry);
      }
      i += 1;
    }
    return paramStart;
  }
  
  private End end()
    throws Exception
  {
    return new End(null);
  }
  
  private EventNode read()
    throws Exception
  {
    int i = this.parser.next();
    if (i != 1)
    {
      if (i == 2) {
        return start();
      }
      if (i == 4) {
        return text();
      }
      if (i == 3) {
        return end();
      }
      return read();
    }
    return null;
  }
  
  private Start start()
    throws Exception
  {
    Start localStart2 = new Start(this.parser);
    Start localStart1 = localStart2;
    if (localStart2.isEmpty()) {
      localStart1 = build(localStart2);
    }
    return localStart1;
  }
  
  private Text text()
    throws Exception
  {
    return new Text(this.parser);
  }
  
  public EventNode next()
    throws Exception
  {
    EventNode localEventNode = this.peek;
    if (localEventNode == null) {
      return read();
    }
    this.peek = null;
    return localEventNode;
  }
  
  public EventNode peek()
    throws Exception
  {
    if (this.peek == null) {
      this.peek = next();
    }
    return this.peek;
  }
  
  private static class End
    extends EventToken
  {
    public boolean isEnd()
    {
      return true;
    }
  }
  
  private static class Entry
    extends EventAttribute
  {
    private final String name;
    private final String prefix;
    private final String reference;
    private final XmlPullParser source;
    private final String value;
    
    public Entry(XmlPullParser paramXmlPullParser, int paramInt)
    {
      this.reference = paramXmlPullParser.getAttributeNamespace(paramInt);
      this.prefix = paramXmlPullParser.getAttributePrefix(paramInt);
      this.value = paramXmlPullParser.getAttributeValue(paramInt);
      this.name = paramXmlPullParser.getAttributeName(paramInt);
      this.source = paramXmlPullParser;
    }
    
    public String getName()
    {
      return this.name;
    }
    
    public String getPrefix()
    {
      return this.prefix;
    }
    
    public String getReference()
    {
      return this.reference;
    }
    
    public Object getSource()
    {
      return this.source;
    }
    
    public String getValue()
    {
      return this.value;
    }
    
    public boolean isReserved()
    {
      return false;
    }
  }
  
  private static class Start
    extends EventElement
  {
    private final int line;
    private final String name;
    private final String prefix;
    private final String reference;
    private final XmlPullParser source;
    
    public Start(XmlPullParser paramXmlPullParser)
    {
      this.reference = paramXmlPullParser.getNamespace();
      this.line = paramXmlPullParser.getLineNumber();
      this.prefix = paramXmlPullParser.getPrefix();
      this.name = paramXmlPullParser.getName();
      this.source = paramXmlPullParser;
    }
    
    public int getLine()
    {
      return this.line;
    }
    
    public String getName()
    {
      return this.name;
    }
    
    public String getPrefix()
    {
      return this.prefix;
    }
    
    public String getReference()
    {
      return this.reference;
    }
    
    public Object getSource()
    {
      return this.source;
    }
  }
  
  private static class Text
    extends EventToken
  {
    private final XmlPullParser source;
    private final String text;
    
    public Text(XmlPullParser paramXmlPullParser)
    {
      this.text = paramXmlPullParser.getText();
      this.source = paramXmlPullParser;
    }
    
    public Object getSource()
    {
      return this.source;
    }
    
    public String getValue()
    {
      return this.text;
    }
    
    public boolean isText()
    {
      return true;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\PullReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */