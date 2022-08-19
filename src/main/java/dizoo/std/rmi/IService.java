package dizoo.std.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by hx on 16-11-10.
 */
public interface IService extends Remote {
  public String queryName(String no) throws RemoteException;
}
