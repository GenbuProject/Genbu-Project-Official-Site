package net.zhuoweizhang.mcpelauncher;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;

public final class AdConfiguration
{
  public static final String AD_UNIT_ID = "ca-app-pub-2652482030334356/6560239824";
  public static final String DEVICE_ID_TESTER = "DF28838C26BDFAE7EB063BFEB7A241D3";
  public static final String DEVICE_ID_TESTER2 = "C0ABF0B025E43414E6EF63D720DCEFDE";
  
  public static AdRequest buildRequest()
  {
    return new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).addTestDevice("DF28838C26BDFAE7EB063BFEB7A241D3").addTestDevice("C0ABF0B025E43414E6EF63D720DCEFDE").build();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\AdConfiguration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */