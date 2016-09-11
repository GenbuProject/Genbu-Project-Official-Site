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
    if ((!JavaUtil.isNullOrEmpty(paramString1)) && (!JavaUtil.isNullOrEmpty(paramString2))) {
      for (int i = paramString1.toLowerCase().indexOf(paramString2.toLowerCase());; i = paramString1.toLowerCase().indexOf(paramString2.toLowerCase(), i + 1)) {
        if ((i == -1) || (i == 0) || (isNullOrWhitespace(paramString1.substring(i - 1, i)))) {
          return i;
        }
      }
    }
    return -1;
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
    if (paramHashtable == null) {
      return null;
    }
    TrieNode localTrieNode = new TrieNode();
    Enumeration localEnumeration = paramHashtable.keys();
    while (localEnumeration.hasMoreElements())
    {
      String str = (String)localEnumeration.nextElement();
      int i = 0;
      paramHashtable = localTrieNode;
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
    return localTrieNode;
  }
  
  public static List<String> getWordMatches(TrieNode paramTrieNode, int paramInt, String paramString)
  {
    int j = 0;
    ArrayList localArrayList = new ArrayList();
    if (JavaUtil.isNullOrEmpty(paramString)) {
      return localArrayList;
    }
    String str1 = "";
    String str2 = paramString.toUpperCase();
    int i = 0;
    Object localObject = paramTrieNode;
    paramTrieNode = str1;
    label41:
    char c;
    if ((i < str2.length()) && (i <= paramInt))
    {
      c = str2.charAt(i);
      paramTrieNode = paramTrieNode + c;
      if ((((TrieNode)localObject).MoreNodes != null) && (((TrieNode)localObject).MoreNodes.containsKey(Character.valueOf(c)))) {}
    }
    for (;;)
    {
      if (i > paramInt)
      {
        if (((TrieNode)localObject).Words == null) {
          break;
        }
        paramTrieNode = ((TrieNode)localObject).Words.iterator();
        while (paramTrieNode.hasNext())
        {
          localObject = (String)paramTrieNode.next();
          if (((String)localObject).toLowerCase().startsWith(paramString.toLowerCase())) {
            localArrayList.add(localObject);
          }
        }
        break;
        localObject = (TrieNode)((TrieNode)localObject).MoreNodes.get(Character.valueOf(c));
        i += 1;
        break label41;
      }
      if (j == 0) {
        break;
      }
      localArrayList.addAll(getRemainingWordMatches((TrieNode)localObject, paramInt, paramTrieNode));
      return localArrayList;
      j = 1;
    }
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\ui\Search\TrieSearch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */