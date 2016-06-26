package com.microsoft.bond;

public abstract interface BondMirror
{
  public abstract BondMirror createInstance(StructDef paramStructDef);
  
  public abstract Object getField(FieldDef paramFieldDef);
  
  public abstract SchemaDef getSchema();
  
  public abstract void setField(FieldDef paramFieldDef, Object paramObject);
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\bond\BondMirror.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */