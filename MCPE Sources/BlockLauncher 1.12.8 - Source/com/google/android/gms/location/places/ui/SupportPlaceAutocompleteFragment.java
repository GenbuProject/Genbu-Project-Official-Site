package com.google.android.gms.location.places.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import com.google.android.gms.R.id;
import com.google.android.gms.R.layout;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLngBounds;

public class SupportPlaceAutocompleteFragment
  extends Fragment
{
  private View zzaRh;
  private View zzaRi;
  private EditText zzaRj;
  @Nullable
  private LatLngBounds zzaRk;
  @Nullable
  private AutocompleteFilter zzaRl;
  @Nullable
  private PlaceSelectionListener zzaRm;
  
  private void zzzF()
  {
    int j = 0;
    View localView;
    if (!this.zzaRj.getText().toString().isEmpty())
    {
      i = 1;
      localView = this.zzaRi;
      if (i == 0) {
        break label42;
      }
    }
    label42:
    for (int i = j;; i = 8)
    {
      localView.setVisibility(i);
      return;
      i = 0;
      break;
    }
  }
  
  private void zzzG()
  {
    try
    {
      startActivityForResult(new PlaceAutocomplete.IntentBuilder(2).setBoundsBias(this.zzaRk).setFilter(this.zzaRl).zzeq(this.zzaRj.getText().toString()).zzig(1).build(getActivity()), 1);
      i = -1;
    }
    catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
    {
      for (;;)
      {
        i = localGooglePlayServicesRepairableException.getConnectionStatusCode();
        Log.e("Places", "Could not open autocomplete activity", localGooglePlayServicesRepairableException);
      }
    }
    catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
    {
      for (;;)
      {
        int i = localGooglePlayServicesNotAvailableException.errorCode;
        Log.e("Places", "Could not open autocomplete activity", localGooglePlayServicesNotAvailableException);
      }
    }
    if (i != -1) {
      GoogleApiAvailability.getInstance().showErrorDialogFragment(getActivity(), i, 2);
    }
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    Object localObject;
    if (paramInt1 == 1)
    {
      if (paramInt2 != -1) {
        break label62;
      }
      localObject = PlaceAutocomplete.getPlace(getActivity(), paramIntent);
      if (this.zzaRm != null) {
        this.zzaRm.onPlaceSelected((Place)localObject);
      }
      setText(((Place)localObject).getName().toString());
    }
    for (;;)
    {
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
      return;
      label62:
      if (paramInt2 == 2)
      {
        localObject = PlaceAutocomplete.getStatus(getActivity(), paramIntent);
        if (this.zzaRm != null) {
          this.zzaRm.onError((Status)localObject);
        }
      }
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(R.layout.place_autocomplete_fragment, paramViewGroup, false);
    this.zzaRh = paramLayoutInflater.findViewById(R.id.place_autocomplete_search_button);
    this.zzaRi = paramLayoutInflater.findViewById(R.id.place_autocomplete_clear_button);
    this.zzaRj = ((EditText)paramLayoutInflater.findViewById(R.id.place_autocomplete_search_input));
    paramViewGroup = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SupportPlaceAutocompleteFragment.zza(SupportPlaceAutocompleteFragment.this);
      }
    };
    this.zzaRh.setOnClickListener(paramViewGroup);
    this.zzaRj.setOnClickListener(paramViewGroup);
    this.zzaRi.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SupportPlaceAutocompleteFragment.this.setText("");
      }
    });
    zzzF();
    return paramLayoutInflater;
  }
  
  public void onDestroyView()
  {
    this.zzaRh = null;
    this.zzaRi = null;
    this.zzaRj = null;
    super.onDestroyView();
  }
  
  public void setBoundsBias(@Nullable LatLngBounds paramLatLngBounds)
  {
    this.zzaRk = paramLatLngBounds;
  }
  
  public void setFilter(@Nullable AutocompleteFilter paramAutocompleteFilter)
  {
    this.zzaRl = paramAutocompleteFilter;
  }
  
  public void setHint(CharSequence paramCharSequence)
  {
    this.zzaRj.setHint(paramCharSequence);
    this.zzaRh.setContentDescription(paramCharSequence);
  }
  
  public void setOnPlaceSelectedListener(PlaceSelectionListener paramPlaceSelectionListener)
  {
    this.zzaRm = paramPlaceSelectionListener;
  }
  
  public void setText(CharSequence paramCharSequence)
  {
    this.zzaRj.setText(paramCharSequence);
    zzzF();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\location\places\ui\SupportPlaceAutocompleteFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */