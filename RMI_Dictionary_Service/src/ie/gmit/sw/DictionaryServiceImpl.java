package ie.gmit.sw;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class DictionaryServiceImpl extends UnicastRemoteObject implements DictionaryService {
    private static final long serialVersionUID = 1L;
    private String querytxt;
    ArrayList<Dictionary> dics;

    protected DictionaryServiceImpl() throws RemoteException {
    }

    // getter and setters of querytxt
    public String getQuerytxt() {
        return querytxt;
    }

    public void setQuerytxt(String querytxt) {
        this.querytxt = querytxt;
    }

    public String check(String querytxt) throws RemoteException {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Dictionary d:dics) {
            if(d.getWord().toLowerCase().equals(querytxt)){
                return d.getDefinition();
            }

        }
        return "oops, string not found !";
    }

    public void loadDictionary() {
        dics = new ArrayList<Dictionary>();
        dics.add(new Dictionary("ABATOR","One who abates a nuisance. (b) A person who, without right, enters into a freehold on the death of the last possessor, before the heir or devisee. Blackstone."));
        dics.add(new Dictionary("ABATURE","Grass and sprigs beaten or trampled down by a stag passing through them. Crabb."));
        //add more ...

    }
}

class Dictionary{
    private String word;
    private String definition;

    public Dictionary(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
