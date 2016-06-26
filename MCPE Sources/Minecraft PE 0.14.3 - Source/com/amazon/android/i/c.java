package com.amazon.android.i;

import com.amazon.android.framework.util.KiwiLogger;
import java.util.Date;

public abstract class c
  extends b
{
  private static final KiwiLogger LOGGER = new KiwiLogger("RelativeExpirable");
  private static final long MILLISECONDS_PER_SECOND = 1000L;
  private final Date instantiation = new Date();
  
  public final Date getExpiration()
  {
    LOGGER.trace("RelativeExpiration duration: " + getExpirationDurationInSeconds() + ", expirable: " + this);
    Date localDate = new Date(this.instantiation.getTime() + 1000L * getExpirationDurationInSeconds());
    LOGGER.trace("Expiration should occur at time: " + localDate);
    return localDate;
  }
  
  protected abstract long getExpirationDurationInSeconds();
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\i\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */