package com.mojang.minecraftpe;

import android.content.Intent;
import android.net.Uri;

public class Minecraft_Market
  extends MainActivity
{
  public void buyGame()
  {
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.mojang.minecraftpe")));
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\mojang\minecraftpe\Minecraft_Market.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */