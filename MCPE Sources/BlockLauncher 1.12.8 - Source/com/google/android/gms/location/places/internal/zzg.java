package com.google.android.gms.location.places.internal;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.NearbyAlertRequest;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.PlaceRequest;
import com.google.android.gms.location.places.UserDataType;
import com.google.android.gms.location.places.personalized.PlaceAlias;
import com.google.android.gms.location.places.personalized.zza;
import com.google.android.gms.location.places.personalized.zza.zza;
import com.google.android.gms.location.places.personalized.zzb;
import com.google.android.gms.location.places.zzc;
import com.google.android.gms.location.places.zzm;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.zzd;
import java.util.List;

public abstract interface zzg
  extends IInterface
{
  public abstract void zza(AddPlaceRequest paramAddPlaceRequest, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zza(NearbyAlertRequest paramNearbyAlertRequest, PlacesParams paramPlacesParams, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void zza(PlaceReport paramPlaceReport, PlacesParams paramPlacesParams)
    throws RemoteException;
  
  public abstract void zza(PlaceRequest paramPlaceRequest, PlacesParams paramPlacesParams, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void zza(UserDataType paramUserDataType, LatLngBounds paramLatLngBounds, List<String> paramList, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zza(PlacesParams paramPlacesParams, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void zza(PlaceAlias paramPlaceAlias, PlacesParams paramPlacesParams, zza paramzza)
    throws RemoteException;
  
  public abstract void zza(PlaceAlias paramPlaceAlias, String paramString1, String paramString2, PlacesParams paramPlacesParams, zza paramzza)
    throws RemoteException;
  
  public abstract void zza(LatLng paramLatLng, PlaceFilter paramPlaceFilter, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zza(LatLng paramLatLng, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zza(LatLngBounds paramLatLngBounds, int paramInt, String paramString, PlaceFilter paramPlaceFilter, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zza(String paramString, int paramInt1, int paramInt2, int paramInt3, PlacesParams paramPlacesParams, zzh paramzzh)
    throws RemoteException;
  
  public abstract void zza(String paramString, int paramInt, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zza(String paramString, PlacesParams paramPlacesParams, zzh paramzzh)
    throws RemoteException;
  
  public abstract void zza(String paramString, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zza(String paramString, LatLngBounds paramLatLngBounds, AutocompleteFilter paramAutocompleteFilter, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zza(List<String> paramList, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zzb(PlaceFilter paramPlaceFilter, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zzb(PlacesParams paramPlacesParams, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void zzb(String paramString, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zzb(List<String> paramList, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzg
  {
    public static zzg zzcn(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
      if ((localIInterface != null) && ((localIInterface instanceof zzg))) {
        return (zzg)localIInterface;
      }
      return new zza(paramIBinder);
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      Object localObject1 = null;
      Object localObject4 = null;
      Object localObject2 = null;
      Object localObject9 = null;
      Object localObject10 = null;
      Object localObject11 = null;
      Object localObject5 = null;
      Object localObject6 = null;
      Object localObject7 = null;
      Object localObject12 = null;
      Object localObject13 = null;
      Object localObject14 = null;
      Object localObject8 = null;
      Object localObject3 = null;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.location.places.internal.IGooglePlacesService");
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = LatLngBounds.CREATOR.zzfy(paramParcel1);
          paramInt1 = paramParcel1.readInt();
          localObject4 = paramParcel1.readString();
          if (paramParcel1.readInt() == 0) {
            break label343;
          }
        }
        for (localObject2 = PlaceFilter.CREATOR.zzfe(paramParcel1);; localObject2 = null)
        {
          if (paramParcel1.readInt() != 0) {
            localObject3 = PlacesParams.CREATOR.zzfo(paramParcel1);
          }
          zza((LatLngBounds)localObject1, paramInt1, (String)localObject4, (PlaceFilter)localObject2, (PlacesParams)localObject3, zzi.zza.zzcp(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
        }
      case 3: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        localObject2 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {
          localObject1 = PlacesParams.CREATOR.zzfo(paramParcel1);
        }
        zza((String)localObject2, (PlacesParams)localObject1, zzi.zza.zzcp(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 4: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = LatLng.CREATOR.zzfz(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label485;
          }
        }
        for (localObject2 = PlaceFilter.CREATOR.zzfe(paramParcel1);; localObject2 = null)
        {
          localObject3 = localObject4;
          if (paramParcel1.readInt() != 0) {
            localObject3 = PlacesParams.CREATOR.zzfo(paramParcel1);
          }
          zza((LatLng)localObject1, (PlaceFilter)localObject2, (PlacesParams)localObject3, zzi.zza.zzcp(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
        }
      case 5: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0) {}
        for (localObject1 = PlaceFilter.CREATOR.zzfe(paramParcel1);; localObject1 = null)
        {
          if (paramParcel1.readInt() != 0) {
            localObject2 = PlacesParams.CREATOR.zzfo(paramParcel1);
          }
          zzb((PlaceFilter)localObject1, (PlacesParams)localObject2, zzi.zza.zzcp(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        }
      case 6: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        localObject2 = paramParcel1.readString();
        localObject1 = localObject9;
        if (paramParcel1.readInt() != 0) {
          localObject1 = PlacesParams.CREATOR.zzfo(paramParcel1);
        }
        zzb((String)localObject2, (PlacesParams)localObject1, zzi.zza.zzcp(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 7: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        localObject2 = paramParcel1.createStringArrayList();
        localObject1 = localObject10;
        if (paramParcel1.readInt() != 0) {
          localObject1 = PlacesParams.CREATOR.zzfo(paramParcel1);
        }
        zza((List)localObject2, (PlacesParams)localObject1, zzi.zza.zzcp(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 17: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        localObject2 = paramParcel1.createStringArrayList();
        localObject1 = localObject11;
        if (paramParcel1.readInt() != 0) {
          localObject1 = PlacesParams.CREATOR.zzfo(paramParcel1);
        }
        zzb((List)localObject2, (PlacesParams)localObject1, zzi.zza.zzcp(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 8: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = UserDataType.CREATOR.zzfj(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label806;
          }
          localObject2 = LatLngBounds.CREATOR.zzfy(paramParcel1);
          localObject4 = paramParcel1.createStringArrayList();
          if (paramParcel1.readInt() == 0) {
            break label812;
          }
        }
        for (localObject3 = PlacesParams.CREATOR.zzfo(paramParcel1);; localObject3 = null)
        {
          zza((UserDataType)localObject1, (LatLngBounds)localObject2, (List)localObject4, (PlacesParams)localObject3, zzi.zza.zzcp(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
          localObject2 = null;
          break label753;
        }
      case 9: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = (PlaceRequest)PlaceRequest.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label902;
          }
          localObject2 = PlacesParams.CREATOR.zzfo(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label908;
          }
        }
        for (paramParcel1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          zza((PlaceRequest)localObject1, (PlacesParams)localObject2, paramParcel1);
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
          localObject2 = null;
          break label861;
        }
      case 10: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = PlacesParams.CREATOR.zzfo(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label974;
          }
        }
        for (paramParcel1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          zza((PlacesParams)localObject1, paramParcel1);
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
        }
      case 11: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = NearbyAlertRequest.CREATOR.zzfd(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label1058;
          }
          localObject2 = PlacesParams.CREATOR.zzfo(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label1064;
          }
        }
        for (paramParcel1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          zza((NearbyAlertRequest)localObject1, (PlacesParams)localObject2, paramParcel1);
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
          localObject2 = null;
          break label1017;
        }
      case 12: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = PlacesParams.CREATOR.zzfo(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label1130;
          }
        }
        for (paramParcel1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          zzb((PlacesParams)localObject1, paramParcel1);
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
        }
      case 13: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        localObject4 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = LatLngBounds.CREATOR.zzfy(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label1226;
          }
          localObject2 = AutocompleteFilter.CREATOR.zzfb(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label1232;
          }
        }
        for (localObject3 = PlacesParams.CREATOR.zzfo(paramParcel1);; localObject3 = null)
        {
          zza((String)localObject4, (LatLngBounds)localObject1, (AutocompleteFilter)localObject2, (PlacesParams)localObject3, zzi.zza.zzcp(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
          localObject2 = null;
          break label1179;
        }
      case 14: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0) {}
        for (localObject1 = (AddPlaceRequest)AddPlaceRequest.CREATOR.createFromParcel(paramParcel1);; localObject1 = null)
        {
          localObject2 = localObject5;
          if (paramParcel1.readInt() != 0) {
            localObject2 = PlacesParams.CREATOR.zzfo(paramParcel1);
          }
          zza((AddPlaceRequest)localObject1, (PlacesParams)localObject2, zzi.zza.zzcp(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        }
      case 15: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0) {}
        for (localObject1 = (PlaceReport)PlaceReport.CREATOR.createFromParcel(paramParcel1);; localObject1 = null)
        {
          localObject2 = localObject6;
          if (paramParcel1.readInt() != 0) {
            localObject2 = PlacesParams.CREATOR.zzfo(paramParcel1);
          }
          zza((PlaceReport)localObject1, (PlacesParams)localObject2);
          paramParcel2.writeNoException();
          return true;
        }
      case 16: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0)
        {
          localObject1 = PlaceAlias.CREATOR.zzfq(paramParcel1);
          localObject3 = paramParcel1.readString();
          localObject4 = paramParcel1.readString();
          if (paramParcel1.readInt() == 0) {
            break label1460;
          }
        }
        for (localObject2 = PlacesParams.CREATOR.zzfo(paramParcel1);; localObject2 = null)
        {
          zza((PlaceAlias)localObject1, (String)localObject3, (String)localObject4, (PlacesParams)localObject2, zza.zza.zzcr(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
          localObject1 = null;
          break;
        }
      case 21: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0) {}
        for (localObject1 = PlaceAlias.CREATOR.zzfq(paramParcel1);; localObject1 = null)
        {
          localObject2 = localObject7;
          if (paramParcel1.readInt() != 0) {
            localObject2 = PlacesParams.CREATOR.zzfo(paramParcel1);
          }
          zza((PlaceAlias)localObject1, (PlacesParams)localObject2, zza.zza.zzcr(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          return true;
        }
      case 18: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        localObject2 = paramParcel1.readString();
        paramInt1 = paramParcel1.readInt();
        localObject1 = localObject12;
        if (paramParcel1.readInt() != 0) {
          localObject1 = PlacesParams.CREATOR.zzfo(paramParcel1);
        }
        zza((String)localObject2, paramInt1, (PlacesParams)localObject1, zzi.zza.zzcp(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 19: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        localObject2 = paramParcel1.readString();
        localObject1 = localObject13;
        if (paramParcel1.readInt() != 0) {
          localObject1 = PlacesParams.CREATOR.zzfo(paramParcel1);
        }
        zza((String)localObject2, (PlacesParams)localObject1, zzh.zza.zzco(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 20: 
        label343:
        label485:
        label753:
        label806:
        label812:
        label861:
        label902:
        label908:
        label974:
        label1017:
        label1058:
        label1064:
        label1130:
        label1179:
        label1226:
        label1232:
        label1460:
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        localObject2 = paramParcel1.readString();
        paramInt1 = paramParcel1.readInt();
        paramInt2 = paramParcel1.readInt();
        int i = paramParcel1.readInt();
        localObject1 = localObject14;
        if (paramParcel1.readInt() != 0) {
          localObject1 = PlacesParams.CREATOR.zzfo(paramParcel1);
        }
        zza((String)localObject2, paramInt1, paramInt2, i, (PlacesParams)localObject1, zzh.zza.zzco(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
      if (paramParcel1.readInt() != 0) {}
      for (localObject1 = LatLng.CREATOR.zzfz(paramParcel1);; localObject1 = null)
      {
        localObject2 = localObject8;
        if (paramParcel1.readInt() != 0) {
          localObject2 = PlacesParams.CREATOR.zzfo(paramParcel1);
        }
        zza((LatLng)localObject1, (PlacesParams)localObject2, zzi.zza.zzcp(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      }
    }
    
    private static class zza
      implements zzg
    {
      private IBinder zzoz;
      
      zza(IBinder paramIBinder)
      {
        this.zzoz = paramIBinder;
      }
      
      public IBinder asBinder()
      {
        return this.zzoz;
      }
      
      public void zza(AddPlaceRequest paramAddPlaceRequest, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramAddPlaceRequest != null)
            {
              localParcel1.writeInt(1);
              paramAddPlaceRequest.writeToParcel(localParcel1, 0);
              if (paramPlacesParams != null)
              {
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
                if (paramzzi == null) {
                  break label132;
                }
                paramAddPlaceRequest = paramzzi.asBinder();
                localParcel1.writeStrongBinder(paramAddPlaceRequest);
                this.zzoz.transact(14, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label132:
          paramAddPlaceRequest = null;
        }
      }
      
      public void zza(NearbyAlertRequest paramNearbyAlertRequest, PlacesParams paramPlacesParams, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramNearbyAlertRequest != null)
            {
              localParcel1.writeInt(1);
              paramNearbyAlertRequest.writeToParcel(localParcel1, 0);
              if (paramPlacesParams != null)
              {
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
                if (paramPendingIntent == null) {
                  break label132;
                }
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                this.zzoz.transact(11, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label132:
          localParcel1.writeInt(0);
        }
      }
      
      public void zza(PlaceReport paramPlaceReport, PlacesParams paramPlacesParams)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramPlaceReport != null)
            {
              localParcel1.writeInt(1);
              paramPlaceReport.writeToParcel(localParcel1, 0);
              if (paramPlacesParams != null)
              {
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
                this.zzoz.transact(15, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(PlaceRequest paramPlaceRequest, PlacesParams paramPlacesParams, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramPlaceRequest != null)
            {
              localParcel1.writeInt(1);
              paramPlaceRequest.writeToParcel(localParcel1, 0);
              if (paramPlacesParams != null)
              {
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
                if (paramPendingIntent == null) {
                  break label132;
                }
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                this.zzoz.transact(9, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label132:
          localParcel1.writeInt(0);
        }
      }
      
      public void zza(UserDataType paramUserDataType, LatLngBounds paramLatLngBounds, List<String> paramList, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramUserDataType != null)
            {
              localParcel1.writeInt(1);
              paramUserDataType.writeToParcel(localParcel1, 0);
              if (paramLatLngBounds != null)
              {
                localParcel1.writeInt(1);
                paramLatLngBounds.writeToParcel(localParcel1, 0);
                localParcel1.writeStringList(paramList);
                if (paramPlacesParams == null) {
                  break label159;
                }
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
                if (paramzzi == null) {
                  break label168;
                }
                paramUserDataType = paramzzi.asBinder();
                localParcel1.writeStrongBinder(paramUserDataType);
                this.zzoz.transact(8, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label159:
          localParcel1.writeInt(0);
          continue;
          label168:
          paramUserDataType = null;
        }
      }
      
      public void zza(PlacesParams paramPlacesParams, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramPlacesParams != null)
            {
              localParcel1.writeInt(1);
              paramPlacesParams.writeToParcel(localParcel1, 0);
              if (paramPendingIntent != null)
              {
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                this.zzoz.transact(10, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(PlaceAlias paramPlaceAlias, PlacesParams paramPlacesParams, zza paramzza)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramPlaceAlias != null)
            {
              localParcel1.writeInt(1);
              paramPlaceAlias.writeToParcel(localParcel1, 0);
              if (paramPlacesParams != null)
              {
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
                if (paramzza == null) {
                  break label132;
                }
                paramPlaceAlias = paramzza.asBinder();
                localParcel1.writeStrongBinder(paramPlaceAlias);
                this.zzoz.transact(21, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label132:
          paramPlaceAlias = null;
        }
      }
      
      public void zza(PlaceAlias paramPlaceAlias, String paramString1, String paramString2, PlacesParams paramPlacesParams, zza paramzza)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramPlaceAlias != null)
            {
              localParcel1.writeInt(1);
              paramPlaceAlias.writeToParcel(localParcel1, 0);
              localParcel1.writeString(paramString1);
              localParcel1.writeString(paramString2);
              if (paramPlacesParams != null)
              {
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
                if (paramzza == null) {
                  break label148;
                }
                paramPlaceAlias = paramzza.asBinder();
                localParcel1.writeStrongBinder(paramPlaceAlias);
                this.zzoz.transact(16, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label148:
          paramPlaceAlias = null;
        }
      }
      
      public void zza(LatLng paramLatLng, PlaceFilter paramPlaceFilter, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramLatLng != null)
            {
              localParcel1.writeInt(1);
              paramLatLng.writeToParcel(localParcel1, 0);
              if (paramPlaceFilter != null)
              {
                localParcel1.writeInt(1);
                paramPlaceFilter.writeToParcel(localParcel1, 0);
                if (paramPlacesParams == null) {
                  break label150;
                }
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
                if (paramzzi == null) {
                  break label159;
                }
                paramLatLng = paramzzi.asBinder();
                localParcel1.writeStrongBinder(paramLatLng);
                this.zzoz.transact(4, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label150:
          localParcel1.writeInt(0);
          continue;
          label159:
          paramLatLng = null;
        }
      }
      
      public void zza(LatLng paramLatLng, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramLatLng != null)
            {
              localParcel1.writeInt(1);
              paramLatLng.writeToParcel(localParcel1, 0);
              if (paramPlacesParams != null)
              {
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
                if (paramzzi == null) {
                  break label132;
                }
                paramLatLng = paramzzi.asBinder();
                localParcel1.writeStrongBinder(paramLatLng);
                this.zzoz.transact(22, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label132:
          paramLatLng = null;
        }
      }
      
      public void zza(LatLngBounds paramLatLngBounds, int paramInt, String paramString, PlaceFilter paramPlaceFilter, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramLatLngBounds != null)
            {
              localParcel1.writeInt(1);
              paramLatLngBounds.writeToParcel(localParcel1, 0);
              localParcel1.writeInt(paramInt);
              localParcel1.writeString(paramString);
              if (paramPlaceFilter != null)
              {
                localParcel1.writeInt(1);
                paramPlaceFilter.writeToParcel(localParcel1, 0);
                if (paramPlacesParams == null) {
                  break label166;
                }
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
                if (paramzzi == null) {
                  break label175;
                }
                paramLatLngBounds = paramzzi.asBinder();
                localParcel1.writeStrongBinder(paramLatLngBounds);
                this.zzoz.transact(2, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label166:
          localParcel1.writeInt(0);
          continue;
          label175:
          paramLatLngBounds = null;
        }
      }
      
      public void zza(String paramString, int paramInt1, int paramInt2, int paramInt3, PlacesParams paramPlacesParams, zzh paramzzh)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            localParcel1.writeString(paramString);
            localParcel1.writeInt(paramInt1);
            localParcel1.writeInt(paramInt2);
            localParcel1.writeInt(paramInt3);
            if (paramPlacesParams != null)
            {
              localParcel1.writeInt(1);
              paramPlacesParams.writeToParcel(localParcel1, 0);
              if (paramzzh != null)
              {
                paramString = paramzzh.asBinder();
                localParcel1.writeStrongBinder(paramString);
                this.zzoz.transact(20, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramString = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(String paramString, int paramInt, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            localParcel1.writeString(paramString);
            localParcel1.writeInt(paramInt);
            if (paramPlacesParams != null)
            {
              localParcel1.writeInt(1);
              paramPlacesParams.writeToParcel(localParcel1, 0);
              if (paramzzi != null)
              {
                paramString = paramzzi.asBinder();
                localParcel1.writeStrongBinder(paramString);
                this.zzoz.transact(18, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramString = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(String paramString, PlacesParams paramPlacesParams, zzh paramzzh)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            localParcel1.writeString(paramString);
            if (paramPlacesParams != null)
            {
              localParcel1.writeInt(1);
              paramPlacesParams.writeToParcel(localParcel1, 0);
              if (paramzzh != null)
              {
                paramString = paramzzh.asBinder();
                localParcel1.writeStrongBinder(paramString);
                this.zzoz.transact(19, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramString = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(String paramString, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            localParcel1.writeString(paramString);
            if (paramPlacesParams != null)
            {
              localParcel1.writeInt(1);
              paramPlacesParams.writeToParcel(localParcel1, 0);
              if (paramzzi != null)
              {
                paramString = paramzzi.asBinder();
                localParcel1.writeStrongBinder(paramString);
                this.zzoz.transact(3, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramString = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(String paramString, LatLngBounds paramLatLngBounds, AutocompleteFilter paramAutocompleteFilter, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            localParcel1.writeString(paramString);
            if (paramLatLngBounds != null)
            {
              localParcel1.writeInt(1);
              paramLatLngBounds.writeToParcel(localParcel1, 0);
              if (paramAutocompleteFilter != null)
              {
                localParcel1.writeInt(1);
                paramAutocompleteFilter.writeToParcel(localParcel1, 0);
                if (paramPlacesParams == null) {
                  break label159;
                }
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
                if (paramzzi == null) {
                  break label168;
                }
                paramString = paramzzi.asBinder();
                localParcel1.writeStrongBinder(paramString);
                this.zzoz.transact(13, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label159:
          localParcel1.writeInt(0);
          continue;
          label168:
          paramString = null;
        }
      }
      
      public void zza(List<String> paramList, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            localParcel1.writeStringList(paramList);
            if (paramPlacesParams != null)
            {
              localParcel1.writeInt(1);
              paramPlacesParams.writeToParcel(localParcel1, 0);
              if (paramzzi != null)
              {
                paramList = paramzzi.asBinder();
                localParcel1.writeStrongBinder(paramList);
                this.zzoz.transact(7, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramList = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zzb(PlaceFilter paramPlaceFilter, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramPlaceFilter != null)
            {
              localParcel1.writeInt(1);
              paramPlaceFilter.writeToParcel(localParcel1, 0);
              if (paramPlacesParams != null)
              {
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
                if (paramzzi == null) {
                  break label131;
                }
                paramPlaceFilter = paramzzi.asBinder();
                localParcel1.writeStrongBinder(paramPlaceFilter);
                this.zzoz.transact(5, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label131:
          paramPlaceFilter = null;
        }
      }
      
      public void zzb(PlacesParams paramPlacesParams, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramPlacesParams != null)
            {
              localParcel1.writeInt(1);
              paramPlacesParams.writeToParcel(localParcel1, 0);
              if (paramPendingIntent != null)
              {
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                this.zzoz.transact(12, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zzb(String paramString, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            localParcel1.writeString(paramString);
            if (paramPlacesParams != null)
            {
              localParcel1.writeInt(1);
              paramPlacesParams.writeToParcel(localParcel1, 0);
              if (paramzzi != null)
              {
                paramString = paramzzi.asBinder();
                localParcel1.writeStrongBinder(paramString);
                this.zzoz.transact(6, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramString = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zzb(List<String> paramList, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            localParcel1.writeStringList(paramList);
            if (paramPlacesParams != null)
            {
              localParcel1.writeInt(1);
              paramPlacesParams.writeToParcel(localParcel1, 0);
              if (paramzzi != null)
              {
                paramList = paramzzi.asBinder();
                localParcel1.writeStrongBinder(paramList);
                this.zzoz.transact(17, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramList = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\location\places\internal\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */