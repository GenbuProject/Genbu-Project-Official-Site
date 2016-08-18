package com.microsoft.xbox.toolkit.ui.Search;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class TrieNode
{
  public boolean IsWord;
  public Hashtable<Character, TrieNode> MoreNodes = new Hashtable(26);
  public List<String> Words = new ArrayList();
  
  public void accept(ITrieNodeVisitor paramITrieNodeVisitor)
  {
    if (paramITrieNodeVisitor != null) {
      paramITrieNodeVisitor.visit(this);
    }
    if (this.MoreNodes != null)
    {
      Enumeration localEnumeration = this.MoreNodes.keys();
      while (localEnumeration.hasMoreElements())
      {
        char c = ((Character)localEnumeration.nextElement()).charValue();
        ((TrieNode)this.MoreNodes.get(Character.valueOf(c))).accept(paramITrieNodeVisitor);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\ui\Search\TrieNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */