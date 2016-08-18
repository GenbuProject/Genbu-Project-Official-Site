package com.microsoft.xbox.toolkit;

import android.app.Activity;
import android.view.View;
import com.microsoft.xboxtcui.R.color;
import com.microsoft.xboxtcui.R.dimen;
import com.microsoft.xboxtcui.R.drawable;
import com.microsoft.xboxtcui.R.id;
import com.microsoft.xboxtcui.R.layout;
import com.microsoft.xboxtcui.R.string;
import com.microsoft.xboxtcui.R.style;
import com.microsoft.xboxtcui.R.styleable;
import com.microsoft.xboxtcui.XboxTcuiSdk;
import java.lang.reflect.Field;

public class XLERValueHelper
{
  public static int findDimensionIdByName(String paramString)
  {
    Object localObject = null;
    try
    {
      paramString = R.dimen.class.getField(paramString);
      int i = -1;
      if (paramString != null) {}
      try
      {
        i = paramString.getInt(null);
        return i;
      }
      catch (IllegalAccessException paramString)
      {
        return -1;
      }
    }
    catch (NoSuchFieldException paramString)
    {
      for (;;)
      {
        paramString = (String)localObject;
      }
    }
  }
  
  public static View findViewByString(String paramString)
  {
    Object localObject = null;
    try
    {
      paramString = R.id.class.getField(paramString);
      int j = -1;
      int i = j;
      if (paramString != null) {}
      try
      {
        i = paramString.getInt(null);
        return XboxTcuiSdk.getActivity().findViewById(i);
      }
      catch (IllegalAccessException paramString)
      {
        for (;;)
        {
          i = j;
        }
      }
    }
    catch (NoSuchFieldException paramString)
    {
      for (;;)
      {
        paramString = (String)localObject;
      }
    }
  }
  
  protected static Class getColorRClass()
  {
    return R.color.class;
  }
  
  public static int getColorRValue(String paramString)
  {
    try
    {
      int i = getColorRClass().getDeclaredField(paramString).getInt(null);
      return i;
    }
    catch (Exception localException)
    {
      XLEAssert.assertTrue("Can't find " + paramString, false);
    }
    return -1;
  }
  
  protected static Class getDimenRClass()
  {
    return R.dimen.class;
  }
  
  public static int getDimenRValue(String paramString)
  {
    try
    {
      int i = getDimenRClass().getDeclaredField(paramString).getInt(null);
      return i;
    }
    catch (Exception localException)
    {
      XLEAssert.assertTrue("Can't find " + paramString, false);
    }
    return -1;
  }
  
  protected static Class getDrawableRClass()
  {
    return R.drawable.class;
  }
  
  public static int getDrawableRValue(String paramString)
  {
    try
    {
      int i = getDrawableRClass().getDeclaredField(paramString).getInt(null);
      return i;
    }
    catch (Exception localException)
    {
      XLEAssert.assertTrue("Can't find " + paramString, false);
    }
    return -1;
  }
  
  protected static Class getIdRClass()
  {
    return R.id.class;
  }
  
  public static int getIdRValue(String paramString)
  {
    try
    {
      int i = getIdRClass().getDeclaredField(paramString).getInt(null);
      return i;
    }
    catch (Exception localException)
    {
      XLEAssert.assertTrue("Can't find " + paramString, false);
    }
    return -1;
  }
  
  protected static Class getLayoutRClass()
  {
    return R.layout.class;
  }
  
  public static int getLayoutRValue(String paramString)
  {
    try
    {
      int i = getLayoutRClass().getDeclaredField(paramString).getInt(null);
      return i;
    }
    catch (Exception localException)
    {
      XLEAssert.assertTrue("Can't find " + paramString, false);
    }
    return -1;
  }
  
  protected static Class getStringRClass()
  {
    return R.string.class;
  }
  
  public static int getStringRValue(String paramString)
  {
    try
    {
      int i = getStringRClass().getDeclaredField(paramString).getInt(null);
      return i;
    }
    catch (Exception localException)
    {
      XLEAssert.assertTrue("Can't find " + paramString, false);
    }
    return -1;
  }
  
  protected static Class getStyleRClass()
  {
    return R.style.class;
  }
  
  public static int getStyleRValue(String paramString)
  {
    try
    {
      int i = getStyleRClass().getDeclaredField(paramString).getInt(null);
      return i;
    }
    catch (Exception localException)
    {
      XLEAssert.assertTrue("Can't find " + paramString, false);
    }
    return -1;
  }
  
  protected static Class getStyleableRClass()
  {
    return R.styleable.class;
  }
  
  public static int getStyleableRValue(String paramString)
  {
    try
    {
      int i = getStyleableRClass().getDeclaredField(paramString).getInt(null);
      return i;
    }
    catch (Exception localException)
    {
      XLEAssert.assertTrue("Can't find " + paramString, false);
    }
    return -1;
  }
  
  public static int[] getStyleableRValueArray(String paramString)
  {
    try
    {
      int[] arrayOfInt = (int[])getStyleableRClass().getDeclaredField(paramString).get(null);
      return arrayOfInt;
    }
    catch (Exception localException)
    {
      XLEAssert.assertTrue("Can't find " + paramString, false);
    }
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\XLERValueHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */