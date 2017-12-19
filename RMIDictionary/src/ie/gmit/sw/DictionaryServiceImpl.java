package ie.gmit.sw;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class DictionaryServiceImpl extends UnicastRemoteObject implements DictionaryService {
    private static final long serialVersionUID = 1L;
    // store the query word
    private String querytxt;
    // store dictionary words in a list
    ArrayList<Dictionary> dics;

    // constructor with no parameter
    protected DictionaryServiceImpl() throws RemoteException {
    }

    // getter and setters of querytxt
    public String getQuerytxt() {
        return querytxt;
    }
    public void setQuerytxt(String querytxt) {
        this.querytxt = querytxt;
    }

    // check method, used to check word is/isn't exist
    public String lookup(String querytxt) throws RemoteException {
        // sleep 1 minutes, simulate a real asynchronous service
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // for loop to search words
        for (Dictionary d:dics) {
            if(d.getWord().toLowerCase().equals(querytxt)){
                return d.getDefinition();
            }

        }
        // if no result found, return msg
        return "oops, string not found !";
    }

    // load dictionary data.
    public void loadDictionary() {
        dics = new ArrayList<Dictionary>();
        dics.add(new Dictionary("ABATOR","One who abates a nuisance. (b) A person who, without right, enters into a freehold on the death of the last possessor, before the heir or devisee. Blackstone."));
        dics.add(new Dictionary("ABATURE","Grass and sprigs beaten or trampled down by a stag passing through them. Crabb."));
        //add more ...

    }
}

// bean class, to store word object
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
