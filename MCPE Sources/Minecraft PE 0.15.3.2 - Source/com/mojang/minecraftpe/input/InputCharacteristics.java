package com.mojang.minecraftpe.input;

import android.os.Build.VERSION;
import android.view.InputDevice;

public class InputCharacteristics
{
  public static boolean allControllersHaveDoubleTriggers()
  {
    boolean bool1 = false;
    if (Build.VERSION.SDK_INT >= 19)
    {
      int[] arrayOfInt = InputDevice.getDeviceIds();
      int i = 0;
      for (;;)
      {
        boolean bool2 = bool1;
        InputDevice localInputDevice;
        boolean bool3;
        int j;
        label114:
        label184:
        int k;
        if (i < arrayOfInt.length)
        {
          localInputDevice = InputDevice.getDevice(arrayOfInt[i]);
          bool3 = bool1;
          if (localInputDevice.isVirtual()) {
            break label281;
          }
          bool3 = bool1;
          if (localInputDevice.getControllerNumber() <= 0) {
            break label281;
          }
          bool3 = bool1;
          if ((localInputDevice.getSources() & 0x401) == 0) {
            break label281;
          }
          boolean[] arrayOfBoolean = localInputDevice.hasKeys(new int[] { 102, 103, 104, 105 });
          if (arrayOfBoolean.length != 4) {
            break label254;
          }
          bool1 = true;
          j = 0;
          bool2 = bool1;
          if (j < arrayOfBoolean.length)
          {
            if (arrayOfBoolean[j] != 0) {
              break label259;
            }
            bool2 = false;
          }
          bool1 = bool2;
          if (!bool2)
          {
            bool1 = bool2;
            if (arrayOfBoolean[0] != 0)
            {
              bool1 = bool2;
              if (arrayOfBoolean[1] != 0)
              {
                if ((localInputDevice.getMotionRange(17) == null) && (localInputDevice.getMotionRange(23) == null)) {
                  break label266;
                }
                j = 1;
                if ((localInputDevice.getMotionRange(18) == null) && (localInputDevice.getMotionRange(22) == null)) {
                  break label271;
                }
                k = 1;
                label206:
                if ((j == 0) || (k == 0)) {
                  break label276;
                }
              }
            }
          }
        }
        label254:
        label259:
        label266:
        label271:
        label276:
        for (bool1 = true;; bool1 = false)
        {
          bool2 = bool1;
          if (bool1)
          {
            bool2 = bool1;
            if (localInputDevice.getName().contains("EI-GP20")) {
              bool2 = false;
            }
          }
          bool3 = bool2;
          if (bool2) {
            break label281;
          }
          return bool2;
          bool1 = false;
          break;
          j += 1;
          break label114;
          j = 0;
          break label184;
          k = 0;
          break label206;
        }
        label281:
        i += 1;
        bool1 = bool3;
      }
    }
    return true;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\mojang\minecraftpe\input\InputCharacteristics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */