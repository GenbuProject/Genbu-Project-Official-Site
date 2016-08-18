package org.simpleframework.xml.stream;

import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

class StreamReader
  implements EventReader
{
  private EventNode peek;
  private XMLEventReader reader;
  
  public StreamReader(XMLEventReader paramXMLEventReader)
  {
    this.reader = paramXMLEventReader;
  }
  
  private Entry attribute(Attribute paramAttribute)
  {
    return new Entry(paramAttribute);
  }
  
  private Start build(Start paramStart)
  {
    Iterator localIterator = paramStart.getAttributes();
    while (localIterator.hasNext())
    {
      Entry localEntry = attribute((Attribute)localIterator.next());
      if (!localEntry.isReserved()) {
        paramStart.add(localEntry);
      }
    }
    return paramStart;
  }
  
  private End end()
  {
    return new End(null);
  }
  
  private EventNode read()
    throws Exception
  {
    XMLEvent localXMLEvent = this.reader.nextEvent();
    if (!localXMLEvent.isEndDocument())
    {
      if (localXMLEvent.isStartElement()) {
        return start(localXMLEvent);
      }
      if (localXMLEvent.isCharacters()) {
        return text(localXMLEvent);
      }
      if (localXMLEvent.isEndElement()) {
        return end();
      }
      return read();
    }
    return null;
  }
  
  private Start start(XMLEvent paramXMLEvent)
  {
    Start localStart = new Start(paramXMLEvent);
    paramXMLEvent = localStart;
    if (localStart.isEmpty()) {
      paramXMLEvent = build(localStart);
    }
    return paramXMLEvent;
  }
  
  private Text text(XMLEvent paramXMLEvent)
  {
    return new Text(paramXMLEvent);
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
    private final Attribute entry;
    
    public Entry(Attribute paramAttribute)
    {
      this.entry = paramAttribute;
    }
    
    public String getName()
    {
      return this.entry.getName().getLocalPart();
    }
    
    public String getPrefix()
    {
      return this.entry.getName().getPrefix();
    }
    
    public String getReference()
    {
      return this.entry.getName().getNamespaceURI();
    }
    
    public Object getSource()
    {
      return this.entry;
    }
    
    public String getValue()
    {
      return this.entry.getValue();
    }
    
    public boolean isReserved()
    {
      return false;
    }
  }
  
  private static class Start
    extends EventElement
  {
    private final StartElement element;
    private final Location location;
    
    public Start(XMLEvent paramXMLEvent)
    {
      this.element = paramXMLEvent.asStartElement();
      this.location = paramXMLEvent.getLocation();
    }
    
    public Iterator<Attribute> getAttributes()
    {
      return this.element.getAttributes();
    }
    
    public int getLine()
    {
      return this.location.getLineNumber();
    }
    
    public String getName()
    {
      return this.element.getName().getLocalPart();
    }
    
    public String getPrefix()
    {
      return this.element.getName().getPrefix();
    }
    
    public String getReference()
    {
      return this.element.getName().getNamespaceURI();
    }
    
    public Object getSource()
    {
      return this.element;
    }
  }
  
  private static class Text
    extends EventToken
  {
    private final Characters text;
    
    public Text(XMLEvent paramXMLEvent)
    {
      this.text = paramXMLEvent.asCharacters();
    }
    
    public Object getSource()
    {
      return this.text;
    }
    
    public String getValue()
    {
      return this.text.getData();
    }
    
    public boolean isText()
    {
      return true;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\StreamReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */