package com.google.android.gms.drive.internal;

import android.content.IntentSender;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.drive.RealtimeDocumentSyncRequest;

public abstract interface zzam
  extends IInterface
{
  public abstract IntentSender zza(CreateFileIntentSenderRequest paramCreateFileIntentSenderRequest)
    throws RemoteException;
  
  public abstract IntentSender zza(OpenFileIntentSenderRequest paramOpenFileIntentSenderRequest)
    throws RemoteException;
  
  public abstract DriveServiceResponse zza(OpenContentsRequest paramOpenContentsRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract DriveServiceResponse zza(StreamContentsRequest paramStreamContentsRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(RealtimeDocumentSyncRequest paramRealtimeDocumentSyncRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(AddEventListenerRequest paramAddEventListenerRequest, zzao paramzzao, String paramString, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(AddPermissionRequest paramAddPermissionRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(AuthorizeAccessRequest paramAuthorizeAccessRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(CancelPendingActionsRequest paramCancelPendingActionsRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(ChangeResourceParentsRequest paramChangeResourceParentsRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(CheckResourceIdsExistRequest paramCheckResourceIdsExistRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(CloseContentsAndUpdateMetadataRequest paramCloseContentsAndUpdateMetadataRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(CloseContentsRequest paramCloseContentsRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(ControlProgressRequest paramControlProgressRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(CreateContentsRequest paramCreateContentsRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(CreateFileRequest paramCreateFileRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(CreateFolderRequest paramCreateFolderRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(DeleteResourceRequest paramDeleteResourceRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(DisconnectRequest paramDisconnectRequest)
    throws RemoteException;
  
  public abstract void zza(FetchThumbnailRequest paramFetchThumbnailRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(GetChangesRequest paramGetChangesRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(GetDriveIdFromUniqueIdentifierRequest paramGetDriveIdFromUniqueIdentifierRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(GetMetadataRequest paramGetMetadataRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(GetPermissionsRequest paramGetPermissionsRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(ListParentsRequest paramListParentsRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(LoadRealtimeRequest paramLoadRealtimeRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(QueryRequest paramQueryRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(QueryRequest paramQueryRequest, zzao paramzzao, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(RemoveEventListenerRequest paramRemoveEventListenerRequest, zzao paramzzao, String paramString, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(RemovePermissionRequest paramRemovePermissionRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(SetFileUploadPreferencesRequest paramSetFileUploadPreferencesRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(SetPinnedDownloadPreferencesRequest paramSetPinnedDownloadPreferencesRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(SetResourceParentsRequest paramSetResourceParentsRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(TrashResourceRequest paramTrashResourceRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(UnsubscribeResourceRequest paramUnsubscribeResourceRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(UntrashResourceRequest paramUntrashResourceRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(UpdateMetadataRequest paramUpdateMetadataRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(UpdatePermissionRequest paramUpdatePermissionRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(zzan paramzzan)
    throws RemoteException;
  
  public abstract void zza(zzao paramzzao, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zzb(QueryRequest paramQueryRequest, zzan paramzzan)
    throws RemoteException;
  
  public abstract void zzb(zzan paramzzan)
    throws RemoteException;
  
  public abstract void zzc(zzan paramzzan)
    throws RemoteException;
  
  public abstract void zzd(zzan paramzzan)
    throws RemoteException;
  
  public abstract void zze(zzan paramzzan)
    throws RemoteException;
  
  public abstract void zzf(zzan paramzzan)
    throws RemoteException;
  
  public abstract void zzg(zzan paramzzan)
    throws RemoteException;
  
  public abstract void zzh(zzan paramzzan)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzam
  {
    public static zzam zzba(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.drive.internal.IDriveService");
      if ((localIInterface != null) && ((localIInterface instanceof zzam))) {
        return (zzam)localIInterface;
      }
      return new zza(paramIBinder);
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      Object localObject2 = null;
      Object localObject3 = null;
      Object localObject4 = null;
      Object localObject5 = null;
      Object localObject6 = null;
      Object localObject7 = null;
      Object localObject8 = null;
      Object localObject9 = null;
      Object localObject10 = null;
      Object localObject11 = null;
      Object localObject12 = null;
      Object localObject13 = null;
      Object localObject14 = null;
      Object localObject15 = null;
      Object localObject16 = null;
      Object localObject17 = null;
      Object localObject18 = null;
      Object localObject19 = null;
      Object localObject20 = null;
      Object localObject21 = null;
      Object localObject22 = null;
      Object localObject23 = null;
      Object localObject24 = null;
      Object localObject25 = null;
      Object localObject26 = null;
      Object localObject27 = null;
      Object localObject28 = null;
      Object localObject29 = null;
      Object localObject30 = null;
      Object localObject31 = null;
      Object localObject32 = null;
      Object localObject33 = null;
      Object localObject34 = null;
      Object localObject35 = null;
      Object localObject36 = null;
      Object localObject37 = null;
      Object localObject38 = null;
      Object localObject39 = null;
      Object localObject1 = null;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.drive.internal.IDriveService");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        if (paramParcel1.readInt() != 0) {
          localObject1 = (GetMetadataRequest)GetMetadataRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((GetMetadataRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject2;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (QueryRequest)QueryRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((QueryRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject3;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (UpdateMetadataRequest)UpdateMetadataRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((UpdateMetadataRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 4: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject4;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (CreateContentsRequest)CreateContentsRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((CreateContentsRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 5: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject5;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (CreateFileRequest)CreateFileRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((CreateFileRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 6: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject6;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (CreateFolderRequest)CreateFolderRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((CreateFolderRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 7: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject7;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (OpenContentsRequest)OpenContentsRequest.CREATOR.createFromParcel(paramParcel1);
        }
        paramParcel1 = zza((OpenContentsRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
        }
        for (;;)
        {
          return true;
          paramParcel2.writeInt(0);
        }
      case 8: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject8;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (CloseContentsRequest)CloseContentsRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((CloseContentsRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 9: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        zza(zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 10: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject9;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (OpenFileIntentSenderRequest)OpenFileIntentSenderRequest.CREATOR.createFromParcel(paramParcel1);
        }
        paramParcel1 = zza((OpenFileIntentSenderRequest)localObject1);
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
        }
        for (;;)
        {
          return true;
          paramParcel2.writeInt(0);
        }
      case 11: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject10;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (CreateFileIntentSenderRequest)CreateFileIntentSenderRequest.CREATOR.createFromParcel(paramParcel1);
        }
        paramParcel1 = zza((CreateFileIntentSenderRequest)localObject1);
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
        }
        for (;;)
        {
          return true;
          paramParcel2.writeInt(0);
        }
      case 12: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject11;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (AuthorizeAccessRequest)AuthorizeAccessRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((AuthorizeAccessRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 13: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject12;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (ListParentsRequest)ListParentsRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((ListParentsRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 14: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject13;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (AddEventListenerRequest)AddEventListenerRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((AddEventListenerRequest)localObject1, zzao.zza.zzbc(paramParcel1.readStrongBinder()), paramParcel1.readString(), zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 15: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject14;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (RemoveEventListenerRequest)RemoveEventListenerRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((RemoveEventListenerRequest)localObject1, zzao.zza.zzbc(paramParcel1.readStrongBinder()), paramParcel1.readString(), zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 16: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject15;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (DisconnectRequest)DisconnectRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((DisconnectRequest)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 17: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject16;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (TrashResourceRequest)TrashResourceRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((TrashResourceRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 18: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject17;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (CloseContentsAndUpdateMetadataRequest)CloseContentsAndUpdateMetadataRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((CloseContentsAndUpdateMetadataRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 19: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject18;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (QueryRequest)QueryRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zzb((QueryRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 24: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject19;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (DeleteResourceRequest)DeleteResourceRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((DeleteResourceRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 27: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject20;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (LoadRealtimeRequest)LoadRealtimeRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((LoadRealtimeRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 28: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject21;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (SetResourceParentsRequest)SetResourceParentsRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((SetResourceParentsRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 29: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject22;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (GetDriveIdFromUniqueIdentifierRequest)GetDriveIdFromUniqueIdentifierRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((GetDriveIdFromUniqueIdentifierRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 30: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject23;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (CheckResourceIdsExistRequest)CheckResourceIdsExistRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((CheckResourceIdsExistRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 31: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        zzb(zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 32: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        zzc(zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 33: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject24;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (SetPinnedDownloadPreferencesRequest)SetPinnedDownloadPreferencesRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((SetPinnedDownloadPreferencesRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 34: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject25;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (RealtimeDocumentSyncRequest)RealtimeDocumentSyncRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((RealtimeDocumentSyncRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 35: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        zzd(zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 36: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject26;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (SetFileUploadPreferencesRequest)SetFileUploadPreferencesRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((SetFileUploadPreferencesRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 37: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject27;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (CancelPendingActionsRequest)CancelPendingActionsRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((CancelPendingActionsRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 38: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject28;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (UntrashResourceRequest)UntrashResourceRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((UntrashResourceRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 41: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        zze(zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 42: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject29;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (FetchThumbnailRequest)FetchThumbnailRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((FetchThumbnailRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 43: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        zzf(zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 44: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject30;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (GetChangesRequest)GetChangesRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((GetChangesRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 46: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject31;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (UnsubscribeResourceRequest)UnsubscribeResourceRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((UnsubscribeResourceRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 47: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject32;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (GetPermissionsRequest)GetPermissionsRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((GetPermissionsRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 48: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject33;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (AddPermissionRequest)AddPermissionRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((AddPermissionRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 49: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject34;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (UpdatePermissionRequest)UpdatePermissionRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((UpdatePermissionRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 50: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject35;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (RemovePermissionRequest)RemovePermissionRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((RemovePermissionRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 51: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject36;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (QueryRequest)QueryRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((QueryRequest)localObject1, zzao.zza.zzbc(paramParcel1.readStrongBinder()), zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 52: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        zza(zzao.zza.zzbc(paramParcel1.readStrongBinder()), zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 53: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject37;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (ControlProgressRequest)ControlProgressRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((ControlProgressRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 54: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        zzg(zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 55: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject38;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (ChangeResourceParentsRequest)ChangeResourceParentsRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((ChangeResourceParentsRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 56: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject39;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (StreamContentsRequest)StreamContentsRequest.CREATOR.createFromParcel(paramParcel1);
        }
        paramParcel1 = zza((StreamContentsRequest)localObject1, zzan.zza.zzbb(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
        }
        for (;;)
        {
          return true;
          paramParcel2.writeInt(0);
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
      zzh(zzan.zza.zzbb(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    }
    
    private static class zza
      implements zzam
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
      
      public IntentSender zza(CreateFileIntentSenderRequest paramCreateFileIntentSenderRequest)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramCreateFileIntentSenderRequest != null)
            {
              localParcel1.writeInt(1);
              paramCreateFileIntentSenderRequest.writeToParcel(localParcel1, 0);
              this.zzoz.transact(11, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                paramCreateFileIntentSenderRequest = (IntentSender)IntentSender.CREATOR.createFromParcel(localParcel2);
                return paramCreateFileIntentSenderRequest;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramCreateFileIntentSenderRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public IntentSender zza(OpenFileIntentSenderRequest paramOpenFileIntentSenderRequest)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramOpenFileIntentSenderRequest != null)
            {
              localParcel1.writeInt(1);
              paramOpenFileIntentSenderRequest.writeToParcel(localParcel1, 0);
              this.zzoz.transact(10, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                paramOpenFileIntentSenderRequest = (IntentSender)IntentSender.CREATOR.createFromParcel(localParcel2);
                return paramOpenFileIntentSenderRequest;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramOpenFileIntentSenderRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public DriveServiceResponse zza(OpenContentsRequest paramOpenContentsRequest, zzan paramzzan)
        throws RemoteException
      {
        Object localObject = null;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramOpenContentsRequest != null)
            {
              localParcel1.writeInt(1);
              paramOpenContentsRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramOpenContentsRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramOpenContentsRequest);
                this.zzoz.transact(7, localParcel1, localParcel2, 0);
                localParcel2.readException();
                paramOpenContentsRequest = (OpenContentsRequest)localObject;
                if (localParcel2.readInt() != 0) {
                  paramOpenContentsRequest = (DriveServiceResponse)DriveServiceResponse.CREATOR.createFromParcel(localParcel2);
                }
                return paramOpenContentsRequest;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramOpenContentsRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public DriveServiceResponse zza(StreamContentsRequest paramStreamContentsRequest, zzan paramzzan)
        throws RemoteException
      {
        Object localObject = null;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramStreamContentsRequest != null)
            {
              localParcel1.writeInt(1);
              paramStreamContentsRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramStreamContentsRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramStreamContentsRequest);
                this.zzoz.transact(56, localParcel1, localParcel2, 0);
                localParcel2.readException();
                paramStreamContentsRequest = (StreamContentsRequest)localObject;
                if (localParcel2.readInt() != 0) {
                  paramStreamContentsRequest = (DriveServiceResponse)DriveServiceResponse.CREATOR.createFromParcel(localParcel2);
                }
                return paramStreamContentsRequest;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramStreamContentsRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(RealtimeDocumentSyncRequest paramRealtimeDocumentSyncRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramRealtimeDocumentSyncRequest != null)
            {
              localParcel1.writeInt(1);
              paramRealtimeDocumentSyncRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramRealtimeDocumentSyncRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramRealtimeDocumentSyncRequest);
                this.zzoz.transact(34, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramRealtimeDocumentSyncRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(AddEventListenerRequest paramAddEventListenerRequest, zzao paramzzao, String paramString, zzan paramzzan)
        throws RemoteException
      {
        Object localObject = null;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramAddEventListenerRequest != null)
            {
              localParcel1.writeInt(1);
              paramAddEventListenerRequest.writeToParcel(localParcel1, 0);
              if (paramzzao != null)
              {
                paramAddEventListenerRequest = paramzzao.asBinder();
                localParcel1.writeStrongBinder(paramAddEventListenerRequest);
                localParcel1.writeString(paramString);
                paramAddEventListenerRequest = (AddEventListenerRequest)localObject;
                if (paramzzan != null) {
                  paramAddEventListenerRequest = paramzzan.asBinder();
                }
                localParcel1.writeStrongBinder(paramAddEventListenerRequest);
                this.zzoz.transact(14, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramAddEventListenerRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(AddPermissionRequest paramAddPermissionRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramAddPermissionRequest != null)
            {
              localParcel1.writeInt(1);
              paramAddPermissionRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramAddPermissionRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramAddPermissionRequest);
                this.zzoz.transact(48, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramAddPermissionRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(AuthorizeAccessRequest paramAuthorizeAccessRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramAuthorizeAccessRequest != null)
            {
              localParcel1.writeInt(1);
              paramAuthorizeAccessRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramAuthorizeAccessRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramAuthorizeAccessRequest);
                this.zzoz.transact(12, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramAuthorizeAccessRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(CancelPendingActionsRequest paramCancelPendingActionsRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramCancelPendingActionsRequest != null)
            {
              localParcel1.writeInt(1);
              paramCancelPendingActionsRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramCancelPendingActionsRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramCancelPendingActionsRequest);
                this.zzoz.transact(37, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramCancelPendingActionsRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(ChangeResourceParentsRequest paramChangeResourceParentsRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramChangeResourceParentsRequest != null)
            {
              localParcel1.writeInt(1);
              paramChangeResourceParentsRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramChangeResourceParentsRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramChangeResourceParentsRequest);
                this.zzoz.transact(55, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramChangeResourceParentsRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(CheckResourceIdsExistRequest paramCheckResourceIdsExistRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramCheckResourceIdsExistRequest != null)
            {
              localParcel1.writeInt(1);
              paramCheckResourceIdsExistRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramCheckResourceIdsExistRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramCheckResourceIdsExistRequest);
                this.zzoz.transact(30, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramCheckResourceIdsExistRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(CloseContentsAndUpdateMetadataRequest paramCloseContentsAndUpdateMetadataRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramCloseContentsAndUpdateMetadataRequest != null)
            {
              localParcel1.writeInt(1);
              paramCloseContentsAndUpdateMetadataRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramCloseContentsAndUpdateMetadataRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramCloseContentsAndUpdateMetadataRequest);
                this.zzoz.transact(18, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramCloseContentsAndUpdateMetadataRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(CloseContentsRequest paramCloseContentsRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramCloseContentsRequest != null)
            {
              localParcel1.writeInt(1);
              paramCloseContentsRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramCloseContentsRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramCloseContentsRequest);
                this.zzoz.transact(8, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramCloseContentsRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(ControlProgressRequest paramControlProgressRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramControlProgressRequest != null)
            {
              localParcel1.writeInt(1);
              paramControlProgressRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramControlProgressRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramControlProgressRequest);
                this.zzoz.transact(53, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramControlProgressRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(CreateContentsRequest paramCreateContentsRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramCreateContentsRequest != null)
            {
              localParcel1.writeInt(1);
              paramCreateContentsRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramCreateContentsRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramCreateContentsRequest);
                this.zzoz.transact(4, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramCreateContentsRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(CreateFileRequest paramCreateFileRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramCreateFileRequest != null)
            {
              localParcel1.writeInt(1);
              paramCreateFileRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramCreateFileRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramCreateFileRequest);
                this.zzoz.transact(5, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramCreateFileRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(CreateFolderRequest paramCreateFolderRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramCreateFolderRequest != null)
            {
              localParcel1.writeInt(1);
              paramCreateFolderRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramCreateFolderRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramCreateFolderRequest);
                this.zzoz.transact(6, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramCreateFolderRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(DeleteResourceRequest paramDeleteResourceRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramDeleteResourceRequest != null)
            {
              localParcel1.writeInt(1);
              paramDeleteResourceRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramDeleteResourceRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramDeleteResourceRequest);
                this.zzoz.transact(24, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramDeleteResourceRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      /* Error */
      public void zza(DisconnectRequest paramDisconnectRequest)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +42 -> 57
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 163	com/google/android/gms/drive/internal/DisconnectRequest:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/drive/internal/zzam$zza$zza:zzoz	Landroid/os/IBinder;
        //   33: bipush 16
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 52 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 55	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 74	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 74	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aload_2
        //   58: iconst_0
        //   59: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   62: goto -33 -> 29
        //   65: astore_1
        //   66: aload_3
        //   67: invokevirtual 74	android/os/Parcel:recycle	()V
        //   70: aload_2
        //   71: invokevirtual 74	android/os/Parcel:recycle	()V
        //   74: aload_1
        //   75: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	76	0	this	zza
        //   0	76	1	paramDisconnectRequest	DisconnectRequest
        //   3	68	2	localParcel1	Parcel
        //   7	60	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	65	finally
        //   18	29	65	finally
        //   29	48	65	finally
        //   57	62	65	finally
      }
      
      public void zza(FetchThumbnailRequest paramFetchThumbnailRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramFetchThumbnailRequest != null)
            {
              localParcel1.writeInt(1);
              paramFetchThumbnailRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramFetchThumbnailRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramFetchThumbnailRequest);
                this.zzoz.transact(42, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramFetchThumbnailRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(GetChangesRequest paramGetChangesRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramGetChangesRequest != null)
            {
              localParcel1.writeInt(1);
              paramGetChangesRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramGetChangesRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramGetChangesRequest);
                this.zzoz.transact(44, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramGetChangesRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(GetDriveIdFromUniqueIdentifierRequest paramGetDriveIdFromUniqueIdentifierRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramGetDriveIdFromUniqueIdentifierRequest != null)
            {
              localParcel1.writeInt(1);
              paramGetDriveIdFromUniqueIdentifierRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramGetDriveIdFromUniqueIdentifierRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramGetDriveIdFromUniqueIdentifierRequest);
                this.zzoz.transact(29, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramGetDriveIdFromUniqueIdentifierRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(GetMetadataRequest paramGetMetadataRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramGetMetadataRequest != null)
            {
              localParcel1.writeInt(1);
              paramGetMetadataRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramGetMetadataRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramGetMetadataRequest);
                this.zzoz.transact(1, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramGetMetadataRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(GetPermissionsRequest paramGetPermissionsRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramGetPermissionsRequest != null)
            {
              localParcel1.writeInt(1);
              paramGetPermissionsRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramGetPermissionsRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramGetPermissionsRequest);
                this.zzoz.transact(47, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramGetPermissionsRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(ListParentsRequest paramListParentsRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramListParentsRequest != null)
            {
              localParcel1.writeInt(1);
              paramListParentsRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramListParentsRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramListParentsRequest);
                this.zzoz.transact(13, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramListParentsRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(LoadRealtimeRequest paramLoadRealtimeRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramLoadRealtimeRequest != null)
            {
              localParcel1.writeInt(1);
              paramLoadRealtimeRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramLoadRealtimeRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramLoadRealtimeRequest);
                this.zzoz.transact(27, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramLoadRealtimeRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(QueryRequest paramQueryRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramQueryRequest != null)
            {
              localParcel1.writeInt(1);
              paramQueryRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramQueryRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramQueryRequest);
                this.zzoz.transact(2, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramQueryRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(QueryRequest paramQueryRequest, zzao paramzzao, zzan paramzzan)
        throws RemoteException
      {
        Object localObject = null;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramQueryRequest != null)
            {
              localParcel1.writeInt(1);
              paramQueryRequest.writeToParcel(localParcel1, 0);
              if (paramzzao != null)
              {
                paramQueryRequest = paramzzao.asBinder();
                localParcel1.writeStrongBinder(paramQueryRequest);
                paramQueryRequest = (QueryRequest)localObject;
                if (paramzzan != null) {
                  paramQueryRequest = paramzzan.asBinder();
                }
                localParcel1.writeStrongBinder(paramQueryRequest);
                this.zzoz.transact(51, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramQueryRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(RemoveEventListenerRequest paramRemoveEventListenerRequest, zzao paramzzao, String paramString, zzan paramzzan)
        throws RemoteException
      {
        Object localObject = null;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramRemoveEventListenerRequest != null)
            {
              localParcel1.writeInt(1);
              paramRemoveEventListenerRequest.writeToParcel(localParcel1, 0);
              if (paramzzao != null)
              {
                paramRemoveEventListenerRequest = paramzzao.asBinder();
                localParcel1.writeStrongBinder(paramRemoveEventListenerRequest);
                localParcel1.writeString(paramString);
                paramRemoveEventListenerRequest = (RemoveEventListenerRequest)localObject;
                if (paramzzan != null) {
                  paramRemoveEventListenerRequest = paramzzan.asBinder();
                }
                localParcel1.writeStrongBinder(paramRemoveEventListenerRequest);
                this.zzoz.transact(15, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramRemoveEventListenerRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(RemovePermissionRequest paramRemovePermissionRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramRemovePermissionRequest != null)
            {
              localParcel1.writeInt(1);
              paramRemovePermissionRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramRemovePermissionRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramRemovePermissionRequest);
                this.zzoz.transact(50, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramRemovePermissionRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(SetFileUploadPreferencesRequest paramSetFileUploadPreferencesRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramSetFileUploadPreferencesRequest != null)
            {
              localParcel1.writeInt(1);
              paramSetFileUploadPreferencesRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramSetFileUploadPreferencesRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramSetFileUploadPreferencesRequest);
                this.zzoz.transact(36, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramSetFileUploadPreferencesRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(SetPinnedDownloadPreferencesRequest paramSetPinnedDownloadPreferencesRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramSetPinnedDownloadPreferencesRequest != null)
            {
              localParcel1.writeInt(1);
              paramSetPinnedDownloadPreferencesRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramSetPinnedDownloadPreferencesRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramSetPinnedDownloadPreferencesRequest);
                this.zzoz.transact(33, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramSetPinnedDownloadPreferencesRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(SetResourceParentsRequest paramSetResourceParentsRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramSetResourceParentsRequest != null)
            {
              localParcel1.writeInt(1);
              paramSetResourceParentsRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramSetResourceParentsRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramSetResourceParentsRequest);
                this.zzoz.transact(28, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramSetResourceParentsRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(TrashResourceRequest paramTrashResourceRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramTrashResourceRequest != null)
            {
              localParcel1.writeInt(1);
              paramTrashResourceRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramTrashResourceRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramTrashResourceRequest);
                this.zzoz.transact(17, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramTrashResourceRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(UnsubscribeResourceRequest paramUnsubscribeResourceRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramUnsubscribeResourceRequest != null)
            {
              localParcel1.writeInt(1);
              paramUnsubscribeResourceRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramUnsubscribeResourceRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramUnsubscribeResourceRequest);
                this.zzoz.transact(46, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramUnsubscribeResourceRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(UntrashResourceRequest paramUntrashResourceRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramUntrashResourceRequest != null)
            {
              localParcel1.writeInt(1);
              paramUntrashResourceRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramUntrashResourceRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramUntrashResourceRequest);
                this.zzoz.transact(38, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramUntrashResourceRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(UpdateMetadataRequest paramUpdateMetadataRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramUpdateMetadataRequest != null)
            {
              localParcel1.writeInt(1);
              paramUpdateMetadataRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramUpdateMetadataRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramUpdateMetadataRequest);
                this.zzoz.transact(3, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramUpdateMetadataRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(UpdatePermissionRequest paramUpdatePermissionRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramUpdatePermissionRequest != null)
            {
              localParcel1.writeInt(1);
              paramUpdatePermissionRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramUpdatePermissionRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramUpdatePermissionRequest);
                this.zzoz.transact(49, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramUpdatePermissionRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      /* Error */
      public void zza(zzan paramzzan)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +43 -> 58
        //   18: aload_1
        //   19: invokeinterface 87 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 90	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/drive/internal/zzam$zza$zza:zzoz	Landroid/os/IBinder;
        //   34: bipush 9
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 52 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 55	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 74	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 74	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 74	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 74	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	zza
        //   0	74	1	paramzzan	zzan
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void zza(zzao paramzzao, zzan paramzzan)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore_3
        //   2: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore 4
        //   7: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore 5
        //   12: aload 4
        //   14: ldc 32
        //   16: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   19: aload_1
        //   20: ifnull +68 -> 88
        //   23: aload_1
        //   24: invokeinterface 108 1 0
        //   29: astore_1
        //   30: aload 4
        //   32: aload_1
        //   33: invokevirtual 90	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload_3
        //   37: astore_1
        //   38: aload_2
        //   39: ifnull +10 -> 49
        //   42: aload_2
        //   43: invokeinterface 87 1 0
        //   48: astore_1
        //   49: aload 4
        //   51: aload_1
        //   52: invokevirtual 90	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   55: aload_0
        //   56: getfield 18	com/google/android/gms/drive/internal/zzam$zza$zza:zzoz	Landroid/os/IBinder;
        //   59: bipush 52
        //   61: aload 4
        //   63: aload 5
        //   65: iconst_0
        //   66: invokeinterface 52 5 0
        //   71: pop
        //   72: aload 5
        //   74: invokevirtual 55	android/os/Parcel:readException	()V
        //   77: aload 5
        //   79: invokevirtual 74	android/os/Parcel:recycle	()V
        //   82: aload 4
        //   84: invokevirtual 74	android/os/Parcel:recycle	()V
        //   87: return
        //   88: aconst_null
        //   89: astore_1
        //   90: goto -60 -> 30
        //   93: astore_1
        //   94: aload 5
        //   96: invokevirtual 74	android/os/Parcel:recycle	()V
        //   99: aload 4
        //   101: invokevirtual 74	android/os/Parcel:recycle	()V
        //   104: aload_1
        //   105: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	106	0	this	zza
        //   0	106	1	paramzzao	zzao
        //   0	106	2	paramzzan	zzan
        //   1	36	3	localObject	Object
        //   5	95	4	localParcel1	Parcel
        //   10	85	5	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   12	19	93	finally
        //   23	30	93	finally
        //   30	36	93	finally
        //   42	49	93	finally
        //   49	77	93	finally
      }
      
      public void zzb(QueryRequest paramQueryRequest, zzan paramzzan)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramQueryRequest != null)
            {
              localParcel1.writeInt(1);
              paramQueryRequest.writeToParcel(localParcel1, 0);
              if (paramzzan != null)
              {
                paramQueryRequest = paramzzan.asBinder();
                localParcel1.writeStrongBinder(paramQueryRequest);
                this.zzoz.transact(19, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramQueryRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      /* Error */
      public void zzb(zzan paramzzan)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +43 -> 58
        //   18: aload_1
        //   19: invokeinterface 87 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 90	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/drive/internal/zzam$zza$zza:zzoz	Landroid/os/IBinder;
        //   34: bipush 31
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 52 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 55	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 74	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 74	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 74	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 74	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	zza
        //   0	74	1	paramzzan	zzan
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void zzc(zzan paramzzan)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +43 -> 58
        //   18: aload_1
        //   19: invokeinterface 87 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 90	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/drive/internal/zzam$zza$zza:zzoz	Landroid/os/IBinder;
        //   34: bipush 32
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 52 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 55	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 74	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 74	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 74	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 74	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	zza
        //   0	74	1	paramzzan	zzan
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void zzd(zzan paramzzan)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +43 -> 58
        //   18: aload_1
        //   19: invokeinterface 87 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 90	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/drive/internal/zzam$zza$zza:zzoz	Landroid/os/IBinder;
        //   34: bipush 35
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 52 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 55	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 74	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 74	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 74	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 74	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	zza
        //   0	74	1	paramzzan	zzan
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void zze(zzan paramzzan)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +43 -> 58
        //   18: aload_1
        //   19: invokeinterface 87 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 90	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/drive/internal/zzam$zza$zza:zzoz	Landroid/os/IBinder;
        //   34: bipush 41
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 52 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 55	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 74	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 74	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 74	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 74	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	zza
        //   0	74	1	paramzzan	zzan
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void zzf(zzan paramzzan)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +43 -> 58
        //   18: aload_1
        //   19: invokeinterface 87 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 90	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/drive/internal/zzam$zza$zza:zzoz	Landroid/os/IBinder;
        //   34: bipush 43
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 52 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 55	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 74	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 74	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 74	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 74	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	zza
        //   0	74	1	paramzzan	zzan
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void zzg(zzan paramzzan)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +43 -> 58
        //   18: aload_1
        //   19: invokeinterface 87 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 90	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/drive/internal/zzam$zza$zza:zzoz	Landroid/os/IBinder;
        //   34: bipush 54
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 52 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 55	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 74	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 74	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 74	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 74	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	zza
        //   0	74	1	paramzzan	zzan
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void zzh(zzan paramzzan)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +43 -> 58
        //   18: aload_1
        //   19: invokeinterface 87 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 90	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/drive/internal/zzam$zza$zza:zzoz	Landroid/os/IBinder;
        //   34: bipush 57
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 52 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 55	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 74	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 74	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 74	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 74	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	zza
        //   0	74	1	paramzzan	zzan
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\zzam.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */