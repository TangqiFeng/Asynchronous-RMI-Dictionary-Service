package ie.gmit.sw;

import java.rmi.Remote;
import java.rmi.RemoteException;

//Remote interface
public interface DictionaryService extends Remote {
    public String lookup(String querytxt) throws RemoteException;
    public void loadDictionary()throws RemoteException;
    public String addItem(String word,String def)throws RemoteException;
    public String deleteItem(String word)throws RemoteException;
    public String modifyItem(String word,String def)throws RemoteException;
}
