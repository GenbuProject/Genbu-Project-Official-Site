package com.google.android.gms.location.places.ui;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;

public abstract interface PlaceSelectionListener
{
  public abstract void onError(Status paramStatus);
  
  public abstract void onPlaceSelected(Place paramPlace);
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\location\places\ui\PlaceSelectionListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */