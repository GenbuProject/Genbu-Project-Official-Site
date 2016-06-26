package android.support.v4.speech.tts;

import android.content.Context;
import android.os.Build.VERSION;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;

class TextToSpeechICS
{
  private static final String TAG = "android.support.v4.speech.tts";
  
  static TextToSpeech construct(Context paramContext, TextToSpeech.OnInitListener paramOnInitListener, String paramString)
  {
    if (Build.VERSION.SDK_INT < 14)
    {
      if (paramString == null) {
        return new TextToSpeech(paramContext, paramOnInitListener);
      }
      Log.w("android.support.v4.speech.tts", "Can't specify tts engine on this device");
      return new TextToSpeech(paramContext, paramOnInitListener);
    }
    return new TextToSpeech(paramContext, paramOnInitListener, paramString);
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\android\support\v4\speech\tts\TextToSpeechICS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */