package com.mojang.minecraftpe.input;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.input.InputManager;
import android.hardware.input.InputManager.InputDeviceListener;

@TargetApi(16)
public class JellyBeanDeviceManager
  extends InputDeviceManager
  implements InputManager.InputDeviceListener
{
  private final InputManager inputManager;
  
  JellyBeanDeviceManager(Context paramContext)
  {
    this.inputManager = ((InputManager)paramContext.getSystemService("input"));
  }
  
  public void onInputDeviceAdded(int paramInt)
  {
    onInputDeviceAddedNative(paramInt);
    setDoubleTriggersSupportedNative(InputCharacteristics.allControllersHaveDoubleTriggers());
  }
  
  native void onInputDeviceAddedNative(int paramInt);
  
  public void onInputDeviceChanged(int paramInt)
  {
    onInputDeviceChangedNative(paramInt);
    setDoubleTriggersSupportedNative(InputCharacteristics.allControllersHaveDoubleTriggers());
  }
  
  native void onInputDeviceChangedNative(int paramInt);
  
  public void onInputDeviceRemoved(int paramInt)
  {
    onInputDeviceRemovedNative(paramInt);
    setDoubleTriggersSupportedNative(InputCharacteristics.allControllersHaveDoubleTriggers());
  }
  
  native void onInputDeviceRemovedNative(int paramInt);
  
  public void register()
  {
    this.inputManager.getInputDeviceIds();
    this.inputManager.registerInputDeviceListener(this, null);
    setDoubleTriggersSupportedNative(InputCharacteristics.allControllersHaveDoubleTriggers());
  }
  
  native void setDoubleTriggersSupportedNative(boolean paramBoolean);
  
  public void unregister()
  {
    this.inputManager.unregisterInputDeviceListener(this);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\mojang\minecraftpe\input\JellyBeanDeviceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */