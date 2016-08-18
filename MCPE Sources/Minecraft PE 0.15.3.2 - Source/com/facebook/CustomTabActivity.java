package com.facebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class CustomTabActivity
  extends Activity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = new Intent(this, FacebookActivity.class);
    paramBundle.putExtra("url", getIntent().getDataString());
    paramBundle.addFlags(603979776);
    startActivity(paramBundle);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\CustomTabActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */