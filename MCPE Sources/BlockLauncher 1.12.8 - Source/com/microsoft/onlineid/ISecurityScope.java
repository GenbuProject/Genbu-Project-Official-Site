package com.microsoft.onlineid;

import java.io.Serializable;

public abstract interface ISecurityScope
  extends Serializable
{
  public abstract String getPolicy();
  
  public abstract String getTarget();
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\ISecurityScope.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */