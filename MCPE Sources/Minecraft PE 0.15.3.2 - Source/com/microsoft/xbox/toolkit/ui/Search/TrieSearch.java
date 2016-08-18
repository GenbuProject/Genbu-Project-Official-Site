package com.microsoft.xbox.toolkit.ui.Search;

import com.microsoft.xbox.toolkit.JavaUtil;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class TrieSearch
{
  private static String ComponentName = TrieSearch.class.getName();
  private static int DefaultTrieDepth = 4;
  public TrieNode RootTrieNode = new TrieNode();
  public int TrieDepth;
  public Hashtable<String, List<Object>> WordsDictionary = new Hashtable();
  
  public TrieSearch()
  {
    this.TrieDepth = DefaultTrieDepth;
  }
  
  public TrieSearch(int paramInt)
  {
    this.TrieDepth = paramInt;
  }
  
  public static int findWordIndex(String paramString1, String paramString2)
  {
    int i = -1;
    int j = i;
    if (!JavaUtil.isNullOrEmpty(paramString1))
    {
      j = i;
      if (JavaUtil.isNullOrEmpty(paramString2)) {}
    }
    for (i = paramString1.toLowerCase().indexOf(paramString2.toLowerCase());; i = paramString1.toLowerCase().indexOf(paramString2.toLowerCase(), i + 1))
    {
      j = i;
      if (i != -1)
      {
        j = i;
        if (i != 0)
        {
          if (!isNullOrWhitespace(paramString1.substring(i - 1, i))) {
            continue;
          }
          j = i;
        }
      }
      return j;
    }
  }
  
  public static List<String> getRemainingWordMatches(TrieNode paramTrieNode, int paramInt, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramTrieNode != null) && (!JavaUtil.isNullOrEmpty(paramString)))
    {
      if ((paramTrieNode.IsWord) && (paramString.length() <= paramInt)) {
        localArrayList.add(paramString);
      }
      Object localObject;
      if (paramTrieNode.MoreNodes != null)
      {
        localObject = paramTrieNode.MoreNodes.keys();
        while (((Enumeration)localObject).hasMoreElements())
        {
          char c = ((Character)((Enumeration)localObject).nextElement()).charValue();
          String str = paramString + c;
          localArrayList.addAll(getRemainingWordMatches((TrieNode)paramTrieNode.MoreNodes.get(Character.valueOf(c)), paramInt, str));
        }
      }
      if (paramTrieNode.Words != null)
      {
        paramTrieNode = paramTrieNode.Words.iterator();
        while (paramTrieNode.hasNext())
        {
          localObject = (String)paramTrieNode.next();
          if (((String)localObject).toLowerCase().startsWith(paramString.toLowerCase())) {
            localArrayList.add(localObject);
          }
        }
      }
    }
    return localArrayList;
  }
  
  public static TrieNode getTrieNodes(Hashtable<String, List<Object>> paramHashtable, int paramInt)
  {
    if (paramHashtable == null)
    {
      paramHashtable = null;
      return paramHashtable;
    }
    TrieNode localTrieNode = new TrieNode();
    Enumeration localEnumeration = paramHashtable.keys();
    for (;;)
    {
      paramHashtable = localTrieNode;
      if (!localEnumeration.hasMoreElements()) {
        break;
      }
      String str = (String)localEnumeration.nextElement();
      paramHashtable = localTrieNode;
      int i = 0;
      while ((i < str.length()) && (i <= paramInt))
      {
        char c = str.charAt(i);
        if (paramHashtable.MoreNodes == null) {
          paramHashtable.MoreNodes = new Hashtable(26);
        }
        if (!paramHashtable.MoreNodes.containsKey(Character.valueOf(c))) {
          paramHashtable.MoreNodes.put(Character.valueOf(c), new TrieNode());
        }
        paramHashtable = (TrieNode)paramHashtable.MoreNodes.get(Character.valueOf(c));
        i += 1;
      }
      if (i > paramInt)
      {
        if (paramHashtable.Words == null) {
          paramHashtable.Words = new ArrayList();
        }
        paramHashtable.Words.add(str);
      }
      if (i == str.length()) {
        paramHashtable.IsWord = true;
      }
    }
  }
  
  public static List<String> getWordMatches(TrieNode paramTrieNode, int paramInt, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if (JavaUtil.isNullOrEmpty(paramString)) {}
    Object localObject2;
    Object localObject1;
    int j;
    label200:
    label226:
    do
    {
      return localArrayList;
      localObject2 = "";
      localObject1 = paramTrieNode;
      int k = 1;
      String str = paramString.toUpperCase();
      int i = 0;
      paramTrieNode = (TrieNode)localObject2;
      for (;;)
      {
        j = k;
        localObject2 = paramTrieNode;
        char c;
        if (i < str.length())
        {
          j = k;
          localObject2 = paramTrieNode;
          if (i <= paramInt)
          {
            c = str.charAt(i);
            paramTrieNode = paramTrieNode + c;
            if ((((TrieNode)localObject1).MoreNodes != null) && (((TrieNode)localObject1).MoreNodes.containsKey(Character.valueOf(c)))) {
              break label200;
            }
            j = 0;
            localObject2 = paramTrieNode;
          }
        }
        if (i <= paramInt) {
          break label226;
        }
        if (((TrieNode)localObject1).Words == null) {
          break;
        }
        paramTrieNode = ((TrieNode)localObject1).Words.iterator();
        while (paramTrieNode.hasNext())
        {
          localObject1 = (String)paramTrieNode.next();
          if (((String)localObject1).toLowerCase().startsWith(paramString.toLowerCase())) {
            localArrayList.add(localObject1);
          }
        }
        break;
        localObject1 = (TrieNode)((TrieNode)localObject1).MoreNodes.get(Character.valueOf(c));
        i += 1;
      }
    } while (j == 0);
    localArrayList.addAll(getRemainingWordMatches((TrieNode)localObject1, paramInt, (String)localObject2));
    return localArrayList;
  }
  
  public static Hashtable<String, List<Object>> getWordsDictionary(List<TrieInput> paramList)
  {
    Hashtable localHashtable = new Hashtable();
    if (paramList == null) {}
    Iterator localIterator;
    do
    {
      return localHashtable;
      localIterator = paramList.iterator();
    } while (!localIterator.hasNext());
    TrieInput localTrieInput = (TrieInput)localIterator.next();
    label60:
    int i;
    label62:
    String str;
    if (JavaUtil.isNullOrEmpty(localTrieInput.Text))
    {
      paramList = new String[0];
      i = 0;
      if (i < paramList.length)
      {
        int j = findWordIndex(localTrieInput.Text, paramList[i]);
        if (j != -1)
        {
          str = localTrieInput.Text.substring(j).toUpperCase();
          if (!localHashtable.containsKey(str)) {
            break label171;
          }
          if (!((List)localHashtable.get(str)).contains(localTrieInput.Context)) {
            ((List)localHashtable.get(str)).add(localTrieInput.Context);
          }
        }
      }
    }
    for (;;)
    {
      i += 1;
      break label62;
      break;
      paramList = localTrieInput.Text.split(" ");
      break label60;
      label171:
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(localTrieInput.Context);
      localHashtable.put(str, localArrayList);
    }
  }
  
  private static boolean isNullOrWhitespace(String paramString)
  {
    return (JavaUtil.isNullOrEmpty(paramString)) || (paramString.trim().isEmpty());
  }
  
  public void initialize(List<TrieInput> paramList)
  {
    this.WordsDictionary = getWordsDictionary(paramList);
    this.RootTrieNode = getTrieNodes(this.WordsDictionary, this.TrieDepth);
  }
  
  public List<String> search(String paramString)
  {
    return getWordMatches(this.RootTrieNode, this.TrieDepth, paramString);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\ui\Search\TrieSearch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */