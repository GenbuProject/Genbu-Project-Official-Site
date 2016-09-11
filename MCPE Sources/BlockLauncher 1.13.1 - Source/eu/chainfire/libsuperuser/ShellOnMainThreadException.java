package eu.chainfire.libsuperuser;

public class ShellOnMainThreadException
  extends RuntimeException
{
  public static final String EXCEPTION_COMMAND = "Application attempted to run a shell command from the main thread";
  public static final String EXCEPTION_NOT_IDLE = "Application attempted to wait for a non-idle shell to close on the main thread";
  public static final String EXCEPTION_WAIT_IDLE = "Application attempted to wait for a shell to become idle on the main thread";
  
  public ShellOnMainThreadException(String paramString)
  {
    super(paramString);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\eu\chainfire\libsuperuser\ShellOnMainThreadException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */