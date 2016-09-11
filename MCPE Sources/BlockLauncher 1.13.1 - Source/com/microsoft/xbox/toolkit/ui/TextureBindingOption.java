package com.microsoft.xbox.toolkit.ui;

import com.microsoft.xbox.toolkit.XLERValueHelper;

public class TextureBindingOption
{
  public static final int DO_NOT_SCALE = -1;
  public static final int DO_NOT_USE_PLACEHOLDER = -1;
  public static final TextureBindingOption DefaultBindingOption = new TextureBindingOption();
  public static final int DefaultResourceIdForEmpty;
  public static final int DefaultResourceIdForError;
  public static final int DefaultResourceIdForLoading = XLERValueHelper.getDrawableRValue("empty");
  public static final TextureBindingOption KeepAsIsBindingOption = new TextureBindingOption(-1, -1, -1, -1, false);
  public final int height;
  public final int resourceIdForError;
  public final int resourceIdForLoading;
  public final boolean useFileCache;
  public final int width;
  
  static
  {
    DefaultResourceIdForEmpty = XLERValueHelper.getDrawableRValue("empty");
    DefaultResourceIdForError = XLERValueHelper.getDrawableRValue("error");
  }
  
  public TextureBindingOption()
  {
    this(-1, -1, DefaultResourceIdForLoading, DefaultResourceIdForError, false);
  }
  
  public TextureBindingOption(int paramInt1, int paramInt2)
  {
    this(paramInt1, paramInt2, true);
  }
  
  public TextureBindingOption(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
  {
    this.width = paramInt1;
    this.height = paramInt2;
    this.resourceIdForLoading = paramInt3;
    this.resourceIdForError = paramInt4;
    this.useFileCache = paramBoolean;
  }
  
  public TextureBindingOption(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    this(paramInt1, paramInt2, DefaultResourceIdForLoading, DefaultResourceIdForError, paramBoolean);
  }
  
  public static TextureBindingOption createDoNotScale(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    return new TextureBindingOption(-1, -1, paramInt1, paramInt2, paramBoolean);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    if (this == paramObject) {}
    do
    {
      boolean bool1 = true;
      do
      {
        do
        {
          do
          {
            do
            {
              return bool1;
              bool1 = bool2;
            } while (!(paramObject instanceof TextureBindingOption));
            paramObject = (TextureBindingOption)paramObject;
            bool1 = bool2;
          } while (this.width != ((TextureBindingOption)paramObject).width);
          bool1 = bool2;
        } while (this.height != ((TextureBindingOption)paramObject).height);
        bool1 = bool2;
      } while (this.resourceIdForError != ((TextureBindingOption)paramObject).resourceIdForError);
    } while (this.resourceIdForLoading == ((TextureBindingOption)paramObject).resourceIdForLoading);
    return false;
  }
  
  public int hashCode()
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\ui\TextureBindingOption.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */