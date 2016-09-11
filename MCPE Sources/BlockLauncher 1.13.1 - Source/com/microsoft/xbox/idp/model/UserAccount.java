package com.microsoft.xbox.idp.model;

import com.google.gson.GsonBuilder;
import com.microsoft.xbox.idp.model.serialization.UTCDateConverter.UTCDateConverterJSONDeserializer;
import java.util.Date;

public class UserAccount
{
  public String administeredConsoles;
  public Date dateOfBirth;
  public String email;
  public String firstName;
  public String gamerTag;
  public String gamerTagChangeReason;
  public Address homeAddressInfo;
  public String homeConsole;
  public String imageUrl;
  public boolean isAdult;
  public String lastName;
  public String legalCountry;
  public String locale;
  public String midasConsole;
  public boolean msftOptin;
  public String ownerHash;
  public String ownerXuid;
  public boolean partnerOptin;
  public Date touAcceptanceDate;
  public String userHash;
  public String userKey;
  public String userXuid;
  
  public static GsonBuilder registerAdapters(GsonBuilder paramGsonBuilder)
  {
    return paramGsonBuilder.registerTypeAdapter(Date.class, new UTCDateConverter.UTCDateConverterJSONDeserializer());
  }
  
  public static class Address
  {
    public String city;
    public String country;
    public String postalCode;
    public String state;
    public String street1;
    public String street2;
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\idp\model\UserAccount.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */