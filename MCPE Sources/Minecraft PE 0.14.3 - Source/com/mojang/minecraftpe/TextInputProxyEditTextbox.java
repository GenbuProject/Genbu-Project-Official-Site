package com.mojang.minecraftpe;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;

public class TextInputProxyEditTextbox
  extends EditText
{
  private MCPEKeyWatcher _mcpeKeyWatcher = null;
  public final int allowedLength;
  public final boolean limitInput;
  
  public TextInputProxyEditTextbox(Context paramContext, int paramInt, boolean paramBoolean)
  {
    super(paramContext);
    this.allowedLength = paramInt;
    this.limitInput = paramBoolean;
    if (paramBoolean)
    {
      paramContext = new InputFilter[2];
      paramContext[0] = new InputFilter.LengthFilter(this.allowedLength);
      paramContext[1 = new InputFilter()
      {
        public CharSequence filter(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, Spanned paramAnonymousSpanned, int paramAnonymousInt3, int paramAnonymousInt4)
        {
          if (paramAnonymousCharSequence.equals("")) {}
          return paramAnonymousCharSequence;
        }
      };
    }
    for (;;)
    {
      setFilters(paramContext);
      return;
      paramContext = new InputFilter[1];
      paramContext[0] = new InputFilter.LengthFilter(this.allowedLength);
    }
  }
  
  public TextInputProxyEditTextbox(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.allowedLength = 160;
    this.limitInput = false;
  }
  
  public TextInputProxyEditTextbox(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.allowedLength = 160;
    this.limitInput = false;
  }
  
  public InputConnection onCreateInputConnection(EditorInfo paramEditorInfo)
  {
    return new MCPEInputConnection(super.onCreateInputConnection(paramEditorInfo), true, this);
  }
  
  public boolean onKeyPreIme(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getAction() == 1))
    {
      if (this._mcpeKeyWatcher != null) {
        this._mcpeKeyWatcher.onBackKeyPressed();
      }
      return false;
    }
    return super.onKeyPreIme(paramInt, paramKeyEvent);
  }
  
  public void setOnMCPEKeyWatcher(MCPEKeyWatcher paramMCPEKeyWatcher)
  {
    this._mcpeKeyWatcher = paramMCPEKeyWatcher;
  }
  
  private class MCPEInputConnection
    extends InputConnectionWrapper
  {
    TextInputProxyEditTextbox textbox;
    
    public MCPEInputConnection(InputConnection paramInputConnection, boolean paramBoolean, TextInputProxyEditTextbox paramTextInputProxyEditTextbox)
    {
      super(paramBoolean);
      this.textbox = paramTextInputProxyEditTextbox;
    }
    
    public boolean sendKeyEvent(KeyEvent paramKeyEvent)
    {
      if ((this.textbox.getText().length() == 0) && (paramKeyEvent.getAction() == 0) && (paramKeyEvent.getKeyCode() == 67))
      {
        if (TextInputProxyEditTextbox.this._mcpeKeyWatcher != null) {
          TextInputProxyEditTextbox.this._mcpeKeyWatcher.onDeleteKeyPressed();
        }
        return false;
      }
      return super.sendKeyEvent(paramKeyEvent);
    }
  }
  
  public static abstract interface MCPEKeyWatcher
  {
    public abstract void onBackKeyPressed();
    
    public abstract void onDeleteKeyPressed();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\mojang\minecraftpe\TextInputProxyEditTextbox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */