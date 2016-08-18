package org.simpleframework.xml.stream;

class InputStack
  extends Stack<InputNode>
{
  public InputStack()
  {
    super(6);
  }
  
  public boolean isRelevant(InputNode paramInputNode)
  {
    return (contains(paramInputNode)) || (isEmpty());
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\InputStack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */