package org.mozilla.javascript;

public class IdFunctionObjectES6
  extends IdFunctionObject
{
  private static final int Id_length = 1;
  private static final int Id_name = 3;
  private boolean myLength = true;
  private boolean myName = true;
  
  public IdFunctionObjectES6(IdFunctionCall paramIdFunctionCall, Object paramObject, int paramInt1, String paramString, int paramInt2, Scriptable paramScriptable)
  {
    super(paramIdFunctionCall, paramObject, paramInt1, paramString, paramInt2, paramScriptable);
  }
  
  protected int findInstanceIdInfo(String paramString)
  {
    if (paramString.equals("length")) {
      return instanceIdInfo(3, 1);
    }
    if (paramString.equals("name")) {
      return instanceIdInfo(3, 3);
    }
    return super.findInstanceIdInfo(paramString);
  }
  
  protected Object getInstanceIdValue(int paramInt)
  {
    if ((paramInt == 1) && (!this.myLength)) {
      return NOT_FOUND;
    }
    if ((paramInt == 3) && (!this.myName)) {
      return NOT_FOUND;
    }
    return super.getInstanceIdValue(paramInt);
  }
  
  protected void setInstanceIdValue(int paramInt, Object paramObject)
  {
    if ((paramInt == 1) && (paramObject == NOT_FOUND))
    {
      this.myLength = false;
      return;
    }
    if ((paramInt == 3) && (paramObject == NOT_FOUND))
    {
      this.myName = false;
      return;
    }
    super.setInstanceIdValue(paramInt, paramObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\IdFunctionObjectES6.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */