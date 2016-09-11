package com.microsoft.onlineid.internal.ui;

import android.graphics.Typeface;

public enum Fonts
{
  SegoeUI("segoeui", null, 0),  SegoeUILight("segoeuil", "sans-serif-thin", 0),  SegoeUISemiBold("seguisb", null, 1);
  
  private String _fallbackFamilyName;
  private int _fallbackStyle;
  private String _filename;
  private boolean _loadFailed = false;
  private Typeface _typeface = null;
  
  private Fonts(String paramString1, String paramString2, int paramInt)
  {
    this._filename = paramString1;
    this._fallbackFamilyName = paramString2;
    this._fallbackStyle = paramInt;
  }
  
  /* Error */
  public Typeface getTypeface(android.content.Context paramContext)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: getfield 51	com/microsoft/onlineid/internal/ui/Fonts:_typeface	Landroid/graphics/Typeface;
    //   8: ifnonnull +53 -> 61
    //   11: aload_0
    //   12: getfield 53	com/microsoft/onlineid/internal/ui/Fonts:_loadFailed	Z
    //   15: ifne +46 -> 61
    //   18: aload_1
    //   19: invokevirtual 83	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   22: astore_1
    //   23: aload_0
    //   24: aload_1
    //   25: ldc 85
    //   27: iconst_1
    //   28: anewarray 87	java/lang/Object
    //   31: dup
    //   32: iconst_0
    //   33: aload_0
    //   34: getfield 55	com/microsoft/onlineid/internal/ui/Fonts:_filename	Ljava/lang/String;
    //   37: aastore
    //   38: invokestatic 93	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   41: invokestatic 99	android/graphics/Typeface:createFromAsset	(Landroid/content/res/AssetManager;Ljava/lang/String;)Landroid/graphics/Typeface;
    //   44: putfield 51	com/microsoft/onlineid/internal/ui/Fonts:_typeface	Landroid/graphics/Typeface;
    //   47: aload_0
    //   48: getfield 51	com/microsoft/onlineid/internal/ui/Fonts:_typeface	Landroid/graphics/Typeface;
    //   51: ifnonnull +5 -> 56
    //   54: iconst_1
    //   55: istore_2
    //   56: aload_0
    //   57: iload_2
    //   58: putfield 53	com/microsoft/onlineid/internal/ui/Fonts:_loadFailed	Z
    //   61: aload_0
    //   62: getfield 51	com/microsoft/onlineid/internal/ui/Fonts:_typeface	Landroid/graphics/Typeface;
    //   65: astore_1
    //   66: aload_0
    //   67: monitorexit
    //   68: aload_1
    //   69: areturn
    //   70: astore_1
    //   71: aload_0
    //   72: aload_0
    //   73: getfield 57	com/microsoft/onlineid/internal/ui/Fonts:_fallbackFamilyName	Ljava/lang/String;
    //   76: aload_0
    //   77: getfield 59	com/microsoft/onlineid/internal/ui/Fonts:_fallbackStyle	I
    //   80: invokestatic 103	android/graphics/Typeface:create	(Ljava/lang/String;I)Landroid/graphics/Typeface;
    //   83: putfield 51	com/microsoft/onlineid/internal/ui/Fonts:_typeface	Landroid/graphics/Typeface;
    //   86: goto -39 -> 47
    //   89: astore_1
    //   90: aload_0
    //   91: monitorexit
    //   92: aload_1
    //   93: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	94	0	this	Fonts
    //   0	94	1	paramContext	android.content.Context
    //   1	57	2	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   23	47	70	java/lang/RuntimeException
    //   4	23	89	finally
    //   23	47	89	finally
    //   47	54	89	finally
    //   56	61	89	finally
    //   61	68	89	finally
    //   71	86	89	finally
    //   90	92	89	finally
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\internal\ui\Fonts.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */