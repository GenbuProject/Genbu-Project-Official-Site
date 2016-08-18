package org.simpleframework.xml.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.Style;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class PathParser
  implements Expression
{
  protected boolean attribute;
  protected Cache<String> attributes = new ConcurrentCache();
  protected StringBuilder builder = new StringBuilder();
  protected String cache;
  protected int count;
  protected char[] data;
  protected Cache<String> elements = new ConcurrentCache();
  protected List<Integer> indexes = new ArrayList();
  protected String location;
  protected List<String> names = new ArrayList();
  protected int off;
  protected String path;
  protected List<String> prefixes = new ArrayList();
  protected int start;
  protected Style style;
  protected Type type;
  
  public PathParser(String paramString, Type paramType, Format paramFormat)
    throws Exception
  {
    this.style = paramFormat.getStyle();
    this.type = paramType;
    this.path = paramString;
    parse(paramString);
  }
  
  private void align()
    throws Exception
  {
    if (this.names.size() > this.indexes.size()) {
      this.indexes.add(Integer.valueOf(1));
    }
  }
  
  private void attribute()
    throws Exception
  {
    int i = this.off + 1;
    this.off = i;
    while (this.off < this.count)
    {
      char[] arrayOfChar = this.data;
      int j = this.off;
      this.off = (j + 1);
      char c = arrayOfChar[j];
      if (!isValid(c)) {
        throw new PathException("Illegal character '%s' in attribute for '%s' in %s", new Object[] { Character.valueOf(c), this.path, this.type });
      }
    }
    if (this.off <= i) {
      throw new PathException("Attribute reference in '%s' for %s is empty", new Object[] { this.path, this.type });
    }
    this.attribute = true;
    attribute(i, this.off - i);
  }
  
  private void attribute(int paramInt1, int paramInt2)
  {
    String str = new String(this.data, paramInt1, paramInt2);
    if (paramInt2 > 0) {
      attribute(str);
    }
  }
  
  private void attribute(String paramString)
  {
    paramString = this.style.getAttribute(paramString);
    this.prefixes.add(null);
    this.names.add(paramString);
  }
  
  private void build()
  {
    int j = this.names.size();
    int i = 0;
    if (i < j)
    {
      String str1 = (String)this.prefixes.get(i);
      String str2 = (String)this.names.get(i);
      int k = ((Integer)this.indexes.get(i)).intValue();
      if (i > 0) {
        this.builder.append('/');
      }
      if ((this.attribute) && (i == j - 1))
      {
        this.builder.append('@');
        this.builder.append(str2);
      }
      for (;;)
      {
        i += 1;
        break;
        if (str1 != null)
        {
          this.builder.append(str1);
          this.builder.append(':');
        }
        this.builder.append(str2);
        this.builder.append('[');
        this.builder.append(k);
        this.builder.append(']');
      }
    }
    this.location = this.builder.toString();
  }
  
  private void element()
    throws Exception
  {
    int j = this.off;
    int i = 0;
    for (;;)
    {
      char c;
      if (this.off < this.count)
      {
        char[] arrayOfChar = this.data;
        int k = this.off;
        this.off = (k + 1);
        c = arrayOfChar[k];
        if (isValid(c)) {
          break label129;
        }
        if (c != '@') {
          break label75;
        }
        this.off -= 1;
      }
      label75:
      do
      {
        for (;;)
        {
          element(j, i);
          return;
          if (c != '[') {
            break;
          }
          index();
        }
      } while (c == '/');
      throw new PathException("Illegal character '%s' in element for '%s' in %s", new Object[] { Character.valueOf(c), this.path, this.type });
      label129:
      i += 1;
    }
  }
  
  private void element(int paramInt1, int paramInt2)
  {
    String str = new String(this.data, paramInt1, paramInt2);
    if (paramInt2 > 0) {
      element(str);
    }
  }
  
  private void element(String paramString)
  {
    int i = paramString.indexOf(':');
    String str1 = null;
    String str2 = paramString;
    if (i > 0)
    {
      str1 = paramString.substring(0, i);
      str2 = paramString.substring(i + 1);
    }
    paramString = this.style.getElement(str2);
    this.prefixes.add(str1);
    this.names.add(paramString);
  }
  
  private void index()
    throws Exception
  {
    int k = 0;
    int j = 0;
    if (this.data[(this.off - 1)] == '[') {}
    for (;;)
    {
      k = j;
      char[] arrayOfChar;
      int i;
      if (this.off < this.count)
      {
        arrayOfChar = this.data;
        k = this.off;
        this.off = (k + 1);
        i = arrayOfChar[k];
        if (!isDigit(i)) {
          k = j;
        }
      }
      else
      {
        arrayOfChar = this.data;
        j = this.off;
        this.off = (j + 1);
        if (arrayOfChar[(j - 1)] == ']') {
          break;
        }
        throw new PathException("Invalid index for path '%s' in %s", new Object[] { this.path, this.type });
      }
      j = j * 10 + i - 48;
    }
    this.indexes.add(Integer.valueOf(k));
  }
  
  private boolean isDigit(char paramChar)
  {
    return Character.isDigit(paramChar);
  }
  
  private boolean isEmpty(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }
  
  private boolean isLetter(char paramChar)
  {
    return Character.isLetterOrDigit(paramChar);
  }
  
  private boolean isSpecial(char paramChar)
  {
    return (paramChar == '_') || (paramChar == '-') || (paramChar == ':');
  }
  
  private boolean isValid(char paramChar)
  {
    return (isLetter(paramChar)) || (isSpecial(paramChar));
  }
  
  private void parse(String paramString)
    throws Exception
  {
    if (paramString != null)
    {
      this.count = paramString.length();
      this.data = new char[this.count];
      paramString.getChars(0, this.count, this.data, 0);
    }
    path();
  }
  
  private void path()
    throws Exception
  {
    if (this.data[this.off] == '/') {
      throw new PathException("Path '%s' in %s references document root", new Object[] { this.path, this.type });
    }
    if (this.data[this.off] == '.') {
      skip();
    }
    while (this.off < this.count)
    {
      if (this.attribute) {
        throw new PathException("Path '%s' in %s references an invalid attribute", new Object[] { this.path, this.type });
      }
      segment();
    }
    truncate();
    build();
  }
  
  private void segment()
    throws Exception
  {
    int i = this.data[this.off];
    if (i == 47) {
      throw new PathException("Invalid path expression '%s' in %s", new Object[] { this.path, this.type });
    }
    if (i == 64) {
      attribute();
    }
    for (;;)
    {
      align();
      return;
      element();
    }
  }
  
  private void skip()
    throws Exception
  {
    if (this.data.length > 1)
    {
      if (this.data[(this.off + 1)] != '/') {
        throw new PathException("Path '%s' in %s has an illegal syntax", new Object[] { this.path, this.type });
      }
      this.off += 1;
    }
    int i = this.off + 1;
    this.off = i;
    this.start = i;
  }
  
  private void truncate()
    throws Exception
  {
    if (this.off - 1 >= this.data.length) {
      this.off -= 1;
    }
    while (this.data[(this.off - 1)] != '/') {
      return;
    }
    this.off -= 1;
  }
  
  public String getAttribute(String paramString)
  {
    if (!isEmpty(this.location))
    {
      String str2 = (String)this.attributes.fetch(paramString);
      String str1 = str2;
      if (str2 == null)
      {
        str2 = getAttributePath(this.location, paramString);
        str1 = str2;
        if (str2 != null)
        {
          this.attributes.cache(paramString, str2);
          str1 = str2;
        }
      }
      return str1;
    }
    return this.style.getAttribute(paramString);
  }
  
  protected String getAttributePath(String paramString1, String paramString2)
  {
    paramString2 = this.style.getAttribute(paramString2);
    if (isEmpty(paramString1)) {
      return paramString2;
    }
    return paramString1 + "/@" + paramString2;
  }
  
  public String getElement(String paramString)
  {
    if (!isEmpty(this.location))
    {
      String str2 = (String)this.elements.fetch(paramString);
      String str1 = str2;
      if (str2 == null)
      {
        str2 = getElementPath(this.location, paramString);
        str1 = str2;
        if (str2 != null)
        {
          this.elements.cache(paramString, str2);
          str1 = str2;
        }
      }
      return str1;
    }
    return this.style.getElement(paramString);
  }
  
  protected String getElementPath(String paramString1, String paramString2)
  {
    paramString2 = this.style.getElement(paramString2);
    if (isEmpty(paramString2)) {
      return paramString1;
    }
    if (isEmpty(paramString1)) {
      return paramString2;
    }
    return paramString1 + "/" + paramString2 + "[1]";
  }
  
  public String getFirst()
  {
    return (String)this.names.get(0);
  }
  
  public int getIndex()
  {
    return ((Integer)this.indexes.get(0)).intValue();
  }
  
  public String getLast()
  {
    int i = this.names.size();
    return (String)this.names.get(i - 1);
  }
  
  public String getPath()
  {
    return this.location;
  }
  
  public Expression getPath(int paramInt)
  {
    return getPath(paramInt, 0);
  }
  
  public Expression getPath(int paramInt1, int paramInt2)
  {
    int i = this.names.size() - 1;
    if (i - paramInt2 >= paramInt1) {
      return new PathSection(paramInt1, i - paramInt2);
    }
    return new PathSection(paramInt1, paramInt1);
  }
  
  public String getPrefix()
  {
    return (String)this.prefixes.get(0);
  }
  
  public boolean isAttribute()
  {
    return this.attribute;
  }
  
  public boolean isEmpty()
  {
    return isEmpty(this.location);
  }
  
  public boolean isPath()
  {
    return this.names.size() > 1;
  }
  
  public Iterator<String> iterator()
  {
    return this.names.iterator();
  }
  
  public String toString()
  {
    int i = this.off;
    int j = this.start;
    if (this.cache == null) {
      this.cache = new String(this.data, this.start, i - j);
    }
    return this.cache;
  }
  
  private class PathSection
    implements Expression
  {
    private int begin;
    private List<String> cache = new ArrayList();
    private int end;
    private String path;
    private String section;
    
    public PathSection(int paramInt1, int paramInt2)
    {
      this.begin = paramInt1;
      this.end = paramInt2;
    }
    
    private String getCanonicalPath()
    {
      int i = 0;
      int j = 0;
      while (j < this.begin)
      {
        i = PathParser.this.location.indexOf('/', i + 1);
        j += 1;
      }
      int m = i;
      int k = j;
      j = m;
      while (k <= this.end)
      {
        m = PathParser.this.location.indexOf('/', j + 1);
        j = m;
        if (m == -1) {
          j = PathParser.this.location.length();
        }
        k += 1;
      }
      return PathParser.this.location.substring(i + 1, j);
    }
    
    private String getFragment()
    {
      int i = PathParser.this.start;
      int m = 0;
      int j = 0;
      for (;;)
      {
        int k = i;
        if (j <= this.end)
        {
          if (i >= PathParser.this.count) {
            k = i + 1;
          }
        }
        else {
          return new String(PathParser.this.data, m, k - 1 - m);
        }
        char[] arrayOfChar = PathParser.this.data;
        k = i + 1;
        int n = j;
        if (arrayOfChar[i] == '/')
        {
          j += 1;
          n = j;
          if (j == this.begin)
          {
            m = k;
            i = k;
            continue;
          }
        }
        i = k;
        j = n;
      }
    }
    
    public String getAttribute(String paramString)
    {
      String str2 = getPath();
      String str1 = paramString;
      if (str2 != null) {
        str1 = PathParser.this.getAttributePath(str2, paramString);
      }
      return str1;
    }
    
    public String getElement(String paramString)
    {
      String str2 = getPath();
      String str1 = paramString;
      if (str2 != null) {
        str1 = PathParser.this.getElementPath(str2, paramString);
      }
      return str1;
    }
    
    public String getFirst()
    {
      return (String)PathParser.this.names.get(this.begin);
    }
    
    public int getIndex()
    {
      return ((Integer)PathParser.this.indexes.get(this.begin)).intValue();
    }
    
    public String getLast()
    {
      return (String)PathParser.this.names.get(this.end);
    }
    
    public String getPath()
    {
      if (this.section == null) {
        this.section = getCanonicalPath();
      }
      return this.section;
    }
    
    public Expression getPath(int paramInt)
    {
      return getPath(paramInt, 0);
    }
    
    public Expression getPath(int paramInt1, int paramInt2)
    {
      return new PathSection(PathParser.this, this.begin + paramInt1, this.end - paramInt2);
    }
    
    public String getPrefix()
    {
      return (String)PathParser.this.prefixes.get(this.begin);
    }
    
    public boolean isAttribute()
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (PathParser.this.attribute)
      {
        bool1 = bool2;
        if (this.end >= PathParser.this.names.size() - 1) {
          bool1 = true;
        }
      }
      return bool1;
    }
    
    public boolean isEmpty()
    {
      return this.begin == this.end;
    }
    
    public boolean isPath()
    {
      return this.end - this.begin >= 1;
    }
    
    public Iterator<String> iterator()
    {
      if (this.cache.isEmpty())
      {
        int i = this.begin;
        while (i <= this.end)
        {
          String str = (String)PathParser.this.names.get(i);
          if (str != null) {
            this.cache.add(str);
          }
          i += 1;
        }
      }
      return this.cache.iterator();
    }
    
    public String toString()
    {
      if (this.path == null) {
        this.path = getFragment();
      }
      return this.path;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\PathParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */