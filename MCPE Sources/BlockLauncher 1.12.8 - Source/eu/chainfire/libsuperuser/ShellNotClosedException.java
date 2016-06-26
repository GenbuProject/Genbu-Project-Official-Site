package eu.chainfire.libsuperuser;

public class ShellNotClosedException
  extends RuntimeException
{
  public static final String EXCEPTION_NOT_CLOSED = "Application did not close() interactive shell";
  
  public ShellNotClosedException()
  {
    super("Application did not close() interactive shell");
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\eu\chainfire\libsuperuser\ShellNotClosedException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */