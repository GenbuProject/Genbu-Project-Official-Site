package com.microsoft.bond;

public abstract interface BondMirror
{
  public abstract BondMirror createInstance(StructDef paramStructDef);
  
  public abstract Object getField(FieldDef paramFieldDef);
  
  public abstract SchemaDef getSchema();
  
  public abstract void setField(FieldDef paramFieldDef, Object paramObject);
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\bond\BondMirror.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */