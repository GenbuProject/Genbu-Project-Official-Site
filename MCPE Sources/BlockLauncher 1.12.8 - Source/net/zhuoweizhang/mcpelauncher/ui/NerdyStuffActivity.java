package net.zhuoweizhang.mcpelauncher.ui;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.mojang.minecraftpe.MainActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Collections;
import net.zhuoweizhang.mcpelauncher.PatchManager;
import net.zhuoweizhang.mcpelauncher.R.id;
import net.zhuoweizhang.mcpelauncher.R.layout;
import net.zhuoweizhang.mcpelauncher.ScriptManager;
import net.zhuoweizhang.mcpelauncher.Utils;

public class NerdyStuffActivity
  extends Activity
  implements View.OnClickListener
{
  private static final String[] magicWords = { "nimubla", "muirab", "otrecnoc", "atled", "nolispe", "etagirf", "repeeketag" };
  private Button chefSpecialButton;
  private Button dumpLibMinecraftPeButton;
  private Button dumpModPEMethodsButton;
  private Button restartAppButton;
  private Button setSkinButton;
  
  public static void forceRestart(Activity paramActivity)
  {
    forceRestart(paramActivity, 1000, true);
  }
  
  public static void forceRestart(Activity paramActivity, int paramInt, boolean paramBoolean)
  {
    AlarmManager localAlarmManager = (AlarmManager)paramActivity.getSystemService("alarm");
    long l1 = SystemClock.elapsedRealtime();
    long l2 = paramInt;
    Intent localIntent = paramActivity.getPackageManager().getLaunchIntentForPackage(paramActivity.getPackageName());
    localIntent.addFlags(335544320);
    localAlarmManager.set(3, l1 + l2, PendingIntent.getActivity(paramActivity, 0, localIntent, 0));
    if (!paramBoolean) {
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          Thread.sleep(200L);
          System.exit(0);
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  public void dumpLib()
  {
    try
    {
      FileOutputStream localFileOutputStream = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + "/libminecraftpe.so.dump");
      FileChannel localFileChannel = localFileOutputStream.getChannel();
      MainActivity.minecraftLibBuffer.position(0);
      localFileChannel.write(MainActivity.minecraftLibBuffer);
      localFileChannel.close();
      localFileOutputStream.close();
      Toast.makeText(this, Environment.getExternalStorageDirectory().getAbsolutePath() + "libminecraftpe.so.dump", 1).show();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void dumpModPEMethods()
  {
    String str = ScriptManager.getAllApiMethodsDescriptions();
    ((ClipboardManager)getSystemService("clipboard")).setText(str);
    try
    {
      FileWriter localFileWriter = new FileWriter(new File(Environment.getExternalStorageDirectory(), "/modpescript_dump.txt").getAbsolutePath());
      localFileWriter.write(str);
      localFileWriter.close();
      new AlertDialog.Builder(this).setTitle("modpescript_dump.txt").setMessage(str).setPositiveButton(17039370, null).show();
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  public void onClick(View paramView)
  {
    if (paramView == this.dumpLibMinecraftPeButton) {
      dumpLib();
    }
    do
    {
      return;
      if (paramView == this.restartAppButton)
      {
        forceRestart(this);
        return;
      }
      if (paramView == this.setSkinButton)
      {
        setSkin();
        return;
      }
    } while ((paramView == this.chefSpecialButton) && (paramView != this.dumpModPEMethodsButton));
    dumpModPEMethods();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    Utils.setLanguageOverride();
    super.onCreate(paramBundle);
    setContentView(R.layout.nerdy_stuff);
    this.dumpLibMinecraftPeButton = ((Button)findViewById(R.id.dump_libminecraftpe_button));
    this.dumpLibMinecraftPeButton.setOnClickListener(this);
    this.restartAppButton = ((Button)findViewById(R.id.restart_app_button));
    this.restartAppButton.setOnClickListener(this);
    this.setSkinButton = ((Button)findViewById(R.id.set_skin_button));
    this.setSkinButton.setOnClickListener(this);
    this.chefSpecialButton = ((Button)findViewById(R.id.chef_special));
    this.chefSpecialButton.setOnClickListener(this);
    this.dumpModPEMethodsButton = ((Button)findViewById(R.id.dump_modpe_methods));
    this.dumpModPEMethodsButton.setOnClickListener(this);
    printMagicWord();
  }
  
  public void printMagicWord()
  {
    int i = 0;
    try
    {
      int j = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
      i = j;
    }
    catch (Exception localException)
    {
      Object localObject;
      for (;;) {}
    }
    localObject = magicWords[(i % magicWords.length)].split("");
    Collections.reverse(Arrays.asList((Object[])localObject));
    localObject = PatchManager.join((String[])localObject, "");
    Log.i("BlockLauncher", "The magic word is " + (String)localObject);
    Log.i("BlockLauncher", "https://groups.google.com/forum/#!forum/blocklauncher-beta");
  }
  
  public void scriptImport()
  {
    Intent localIntent = new Intent("net.zhuoweizhang.mcpelauncher.action.IMPORT_SCRIPT");
    localIntent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "/winprogress/500ise_everymethod.js")), "text/plain");
    try
    {
      startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void setSkin()
  {
    Intent localIntent = new Intent("net.zhuoweizhang.mcpelauncher.action.SET_SKIN");
    localIntent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "/skin.png")), "image/png");
    try
    {
      startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\ui\NerdyStuffActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */