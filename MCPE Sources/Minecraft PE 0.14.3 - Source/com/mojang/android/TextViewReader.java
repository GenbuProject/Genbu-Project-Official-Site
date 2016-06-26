package com.mojang.android;

import android.widget.TextView;

public class TextViewReader
  implements StringValue
{
  private TextView _view;
  
  public TextViewReader(TextView paramTextView)
  {
    this._view = paramTextView;
  }
  
  public String getStringValue()
  {
    return this._view.getText().toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\mojang\android\TextViewReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */