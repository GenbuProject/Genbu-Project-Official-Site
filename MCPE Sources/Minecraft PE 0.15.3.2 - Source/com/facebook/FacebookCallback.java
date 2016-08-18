package com.facebook;

public abstract interface FacebookCallback<RESULT>
{
  public abstract void onCancel();
  
  public abstract void onError(FacebookException paramFacebookException);
  
  public abstract void onSuccess(RESULT paramRESULT);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\FacebookCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */