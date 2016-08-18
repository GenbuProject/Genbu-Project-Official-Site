package org.simpleframework.xml.stream;

class NodeReader
{
  private final EventReader reader;
  private final InputStack stack = new InputStack();
  private final StringBuilder text = new StringBuilder();
  
  public NodeReader(EventReader paramEventReader)
  {
    this.reader = paramEventReader;
  }
  
  private void fillText(InputNode paramInputNode)
    throws Exception
  {
    paramInputNode = this.reader.peek();
    if (paramInputNode.isText())
    {
      paramInputNode = paramInputNode.getValue();
      this.text.append(paramInputNode);
    }
  }
  
  private boolean isName(EventNode paramEventNode, String paramString)
  {
    paramEventNode = paramEventNode.getName();
    if (paramEventNode == null) {
      return false;
    }
    return paramEventNode.equals(paramString);
  }
  
  private String readBuffer(InputNode paramInputNode)
    throws Exception
  {
    if (this.text.length() > 0)
    {
      paramInputNode = this.text.toString();
      this.text.setLength(0);
      return paramInputNode;
    }
    return null;
  }
  
  private InputNode readStart(InputNode paramInputNode, EventNode paramEventNode)
    throws Exception
  {
    paramInputNode = new InputElement(paramInputNode, this, paramEventNode);
    if (this.text.length() > 0) {
      this.text.setLength(0);
    }
    if (paramEventNode.isStart()) {
      return (InputNode)this.stack.push(paramInputNode);
    }
    return paramInputNode;
  }
  
  private String readText(InputNode paramInputNode)
    throws Exception
  {
    for (EventNode localEventNode = this.reader.peek(); (this.stack.top() == paramInputNode) && (localEventNode.isText()); localEventNode = this.reader.peek())
    {
      fillText(paramInputNode);
      this.reader.next();
    }
    return readBuffer(paramInputNode);
  }
  
  public boolean isEmpty(InputNode paramInputNode)
    throws Exception
  {
    return (this.stack.top() == paramInputNode) && (this.reader.peek().isEnd());
  }
  
  public boolean isRoot(InputNode paramInputNode)
  {
    return this.stack.bottom() == paramInputNode;
  }
  
  public InputNode readElement(InputNode paramInputNode)
    throws Exception
  {
    if (!this.stack.isRelevant(paramInputNode)) {}
    EventNode localEventNode;
    label23:
    do
    {
      return null;
      localEventNode = this.reader.next();
      if (localEventNode == null) {
        break;
      }
      if (!localEventNode.isEnd()) {
        break label60;
      }
    } while (this.stack.pop() == paramInputNode);
    label60:
    while (!localEventNode.isStart())
    {
      localEventNode = this.reader.next();
      break label23;
      break;
    }
    return readStart(paramInputNode, localEventNode);
  }
  
  public InputNode readElement(InputNode paramInputNode, String paramString)
    throws Exception
  {
    if (!this.stack.isRelevant(paramInputNode)) {}
    EventNode localEventNode;
    label23:
    label64:
    label95:
    do
    {
      return null;
      localEventNode = this.reader.peek();
      if (localEventNode != null)
      {
        if (!localEventNode.isText()) {
          break label64;
        }
        fillText(paramInputNode);
      }
      do
      {
        for (;;)
        {
          this.reader.next();
          localEventNode = this.reader.peek();
          break label23;
          break;
          if (!localEventNode.isEnd()) {
            break label95;
          }
          if (this.stack.top() == paramInputNode) {
            break;
          }
          this.stack.pop();
        }
      } while (!localEventNode.isStart());
    } while (!isName(localEventNode, paramString));
    return readElement(paramInputNode);
  }
  
  public InputNode readRoot()
    throws Exception
  {
    Object localObject = null;
    if (this.stack.isEmpty())
    {
      InputNode localInputNode = readElement(null);
      localObject = localInputNode;
      if (localInputNode == null) {
        throw new NodeException("Document has no root element");
      }
    }
    return (InputNode)localObject;
  }
  
  public String readValue(InputNode paramInputNode)
    throws Exception
  {
    if (!this.stack.isRelevant(paramInputNode)) {}
    do
    {
      return null;
      if ((this.text.length() > 0) || (!this.reader.peek().isEnd())) {
        break;
      }
    } while (this.stack.top() == paramInputNode);
    this.stack.pop();
    this.reader.next();
    return readText(paramInputNode);
  }
  
  public void skipElement(InputNode paramInputNode)
    throws Exception
  {
    while (readElement(paramInputNode) != null) {}
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\NodeReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */