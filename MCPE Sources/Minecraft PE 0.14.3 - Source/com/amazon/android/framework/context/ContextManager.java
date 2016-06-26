package com.amazon.android.framework.context;

import android.app.Activity;
import android.app.Service;

public abstract interface ContextManager
{
  public abstract void finishActivities();
  
  public abstract Activity getRoot();
  
  public abstract Activity getVisible();
  
  public abstract boolean hasAppShutdownBeenRequested();
  
  public abstract boolean isVisible();
  
  public abstract void onCreate(Activity paramActivity);
  
  public abstract void onCreate(Service paramService);
  
  public abstract void onDestroy(Activity paramActivity);
  
  public abstract void onDestroy(Service paramService);
  
  public abstract void onPause(Activity paramActivity);
  
  public abstract void onResume(Activity paramActivity);
  
  public abstract void onStart(Activity paramActivity);
  
  public abstract void onStop(Activity paramActivity);
  
  public abstract void stopServices();
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\context\ContextManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */