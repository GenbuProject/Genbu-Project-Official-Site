package org.simpleframework.xml.filter;

import java.util.Stack;

public class StackFilter
  implements Filter
{
  private Stack<Filter> stack = new Stack();
  
  public void push(Filter paramFilter)
  {
    this.stack.push(paramFilter);
  }
  
  public String replace(String paramString)
  {
    int i = this.stack.size();
    String str;
    do
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      str = ((Filter)this.stack.get(i)).replace(paramString);
    } while (str == null);
    return str;
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\filter\StackFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */