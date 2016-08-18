package org.simpleframework.xml.core;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

class SignatureBuilder
{
  private final Constructor factory;
  private final ParameterTable table = new ParameterTable();
  
  public SignatureBuilder(Constructor paramConstructor)
  {
    this.factory = paramConstructor;
  }
  
  private List<Signature> build(ParameterTable paramParameterTable)
    throws Exception
  {
    if (this.table.isEmpty()) {
      return create();
    }
    build(paramParameterTable, 0);
    return create(paramParameterTable);
  }
  
  private void build(ParameterTable paramParameterTable, int paramInt)
  {
    build(paramParameterTable, new ParameterList(), paramInt);
  }
  
  private void build(ParameterTable paramParameterTable, ParameterList paramParameterList, int paramInt)
  {
    ParameterList localParameterList1 = this.table.get(paramInt);
    int j = localParameterList1.size();
    if (this.table.width() - 1 > paramInt)
    {
      int i = 0;
      while (i < j)
      {
        ParameterList localParameterList2 = new ParameterList(paramParameterList);
        if (paramParameterList != null)
        {
          localParameterList2.add((Parameter)localParameterList1.get(i));
          build(paramParameterTable, localParameterList2, paramInt + 1);
        }
        i += 1;
      }
    }
    populate(paramParameterTable, paramParameterList, paramInt);
  }
  
  private List<Signature> create()
    throws Exception
  {
    ArrayList localArrayList = new ArrayList();
    Signature localSignature = new Signature(this.factory);
    if (isValid()) {
      localArrayList.add(localSignature);
    }
    return localArrayList;
  }
  
  private List<Signature> create(ParameterTable paramParameterTable)
    throws Exception
  {
    ArrayList localArrayList = new ArrayList();
    int k = paramParameterTable.height();
    int m = paramParameterTable.width();
    int i = 0;
    while (i < k)
    {
      Signature localSignature = new Signature(this.factory);
      int j = 0;
      while (j < m)
      {
        Parameter localParameter = paramParameterTable.get(j, i);
        String str = localParameter.getPath();
        if (localSignature.contains(localParameter.getKey())) {
          throw new ConstructorException("Parameter '%s' is a duplicate in %s", new Object[] { str, this.factory });
        }
        localSignature.add(localParameter);
        j += 1;
      }
      localArrayList.add(localSignature);
      i += 1;
    }
    return localArrayList;
  }
  
  private void populate(ParameterTable paramParameterTable, ParameterList paramParameterList, int paramInt)
  {
    ParameterList localParameterList = this.table.get(paramInt);
    int k = paramParameterList.size();
    int m = localParameterList.size();
    int i = 0;
    while (i < m)
    {
      int j = 0;
      while (j < k)
      {
        paramParameterTable.get(j).add((Parameter)paramParameterList.get(j));
        j += 1;
      }
      paramParameterTable.get(paramInt).add((Parameter)localParameterList.get(i));
      i += 1;
    }
  }
  
  public List<Signature> build()
    throws Exception
  {
    return build(new ParameterTable());
  }
  
  public void insert(Parameter paramParameter, int paramInt)
  {
    this.table.insert(paramParameter, paramInt);
  }
  
  public boolean isValid()
  {
    Class[] arrayOfClass = this.factory.getParameterTypes();
    int i = this.table.width();
    return arrayOfClass.length == i;
  }
  
  private static class ParameterList
    extends ArrayList<Parameter>
  {
    public ParameterList() {}
    
    public ParameterList(ParameterList paramParameterList)
    {
      super();
    }
  }
  
  private static class ParameterTable
    extends ArrayList<SignatureBuilder.ParameterList>
  {
    private int height()
    {
      int i = 0;
      if (width() > 0) {
        i = get(0).size();
      }
      return i;
    }
    
    private int width()
    {
      return size();
    }
    
    public Parameter get(int paramInt1, int paramInt2)
    {
      return (Parameter)get(paramInt1).get(paramInt2);
    }
    
    public SignatureBuilder.ParameterList get(int paramInt)
    {
      int i = size();
      while (i <= paramInt)
      {
        add(new SignatureBuilder.ParameterList());
        i += 1;
      }
      return (SignatureBuilder.ParameterList)super.get(paramInt);
    }
    
    public void insert(Parameter paramParameter, int paramInt)
    {
      SignatureBuilder.ParameterList localParameterList = get(paramInt);
      if (localParameterList != null) {
        localParameterList.add(paramParameter);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\SignatureBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */