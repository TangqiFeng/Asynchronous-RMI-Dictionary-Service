package ie.gmit.sw;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DictionaryService extends Remote {
    public String check(String querytxt) throws RemoteException;
    public void loadDictionary()throws RemoteException;
}
