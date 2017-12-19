package ie.gmit.sw;

import ie.gmit.sw.DictionaryService;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIDictionaryServer {
    public static void main(String[] args)throws Exception {
        //Create an instance of a DictionaryServiceImpl. As DictionaryServiceImpl implements the DictionaryService
        //interface, it can be referred to as a DictionaryService type.
        DictionaryService dic = new DictionaryServiceImpl();

        //Start the RMI registry on port 1099
        LocateRegistry.createRegistry(1099);

        //Bind our remote object to the registry with the human-readable name "DictionaryService"
        Naming.rebind("dictionaryService", dic);

        //Print a message to standard output
        System.out.println("Server is ready.");

    }
}
