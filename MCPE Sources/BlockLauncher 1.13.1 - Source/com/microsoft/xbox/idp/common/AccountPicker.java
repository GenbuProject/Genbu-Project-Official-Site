package com.microsoft.xbox.idp.common;

import android.content.Context;
import android.content.Intent;
import com.microsoft.xbox.idp.ui.AuthFlowActivity;
import com.microsoft.xbox.idp.ui.SignOutActivity;

public class AccountPicker
{
  public static Intent newSignInIntent(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = new Intent(paramContext, AuthFlowActivity.class);
    paramContext.putExtra("ARG_SECURITY_SCOPE", paramString1);
    paramContext.putExtra("ARG_SECURITY_POLICY", paramString2);
    return paramContext;
  }
  
  public static Intent newSignInIntent(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    paramContext = new Intent(paramContext, AuthFlowActivity.class);
    paramContext.putExtra("ARG_SECURITY_SCOPE", paramString1);
    paramContext.putExtra("ARG_SECURITY_POLICY", paramString2);
    paramContext.putExtra("ARG_ALT_BUTTON_TEXT", paramString3);
    return paramContext;
  }
  
  public static Intent newSignOutIntent(Context paramContext)
  {
    return new Intent(paramContext, SignOutActivity.class);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\idp\common\AccountPicker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */