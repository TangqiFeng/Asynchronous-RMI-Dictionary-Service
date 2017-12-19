package ie.gmit.sw;

import java.rmi.Remote;
import java.rmi.RemoteException;

//Remote interface
public interface DictionaryService extends Remote {
    public String lookup(String querytxt) throws RemoteException;
    public void loadDictionary()throws RemoteException;
}
