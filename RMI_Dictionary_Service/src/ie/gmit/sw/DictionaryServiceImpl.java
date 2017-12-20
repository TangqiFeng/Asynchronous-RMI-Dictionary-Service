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
    // here, I just add 50 instances. By another way, can use fileReader etc.
    public void loadDictionary() {
        dics = new ArrayList<Dictionary>();
        dics.add(new Dictionary("ABATOR","One who abates a nuisance. (b) A person who, without right, enters into a freehold on the death of the last possessor, before the heir or devisee. Blackstone."));
        dics.add(new Dictionary("ABATURE","Grass and sprigs beaten or trampled down by a stag passing through them. Crabb."));
        dics.add(new Dictionary("ABATABLE","Capable of being abated; as, an abatable writ or nuisance."));
        dics.add(new Dictionary("ANIMALCULISM","The theory which seeks to explain certain physiological and pathological by means of animalcules."));
        dics.add(new Dictionary("ANKUS","An elephant goad with a sharp spike and hook, resembling a short-handled boat hook. [India] Kipling"));
        dics.add(new Dictionary("ANLACE","A broad dagger formerly worn at the girdle. [Written also anelace.]"));
        dics.add(new Dictionary("ANONYMOUS","Nameless; of unknown name; also, of unknown /or unavowed authorship; as, an anonymous benefactor; an anonymous pamphlet or letter."));
        dics.add(new Dictionary("ANORTHOPIA","Distorted vision, in which straight lines appear bent."));
        dics.add(new Dictionary("ANSA","A name given to either of the projecting ends of Saturn's ring."));
        dics.add(new Dictionary("DIAGEOTROPISM","The tendency of organs (as roots) of plants to assume a position oblique or transverse to a direction towards the center of the earth."));
        dics.add(new Dictionary("DIAGNOSE","To ascertain by diagnosis; to diagnosticate. See Diagnosticate."));
        dics.add(new Dictionary("DIAGNOSTIC","The mark or symptom by which one disease is known or distinguished from others."));
        dics.add(new Dictionary("DIAGOMETER","A sort of electroscope, invented by Rousseau, in which the dry pile is employed to measure the amount of electricity transmitted by different bodies, or to determine their conducting power. Nichol."));
        dics.add(new Dictionary("COADJUST","To adjust by mutual adaptations. R. Owen."));
        dics.add(new Dictionary("COADUNATE","United at the base, as contiguous lobes of a leaf."));
        dics.add(new Dictionary("COAGULATE","To cause (a liquid) to change into a curdlike or semisolid state, not by evaporation but by some kind of chemical reaction; to curdle; as, rennet coagulates milk; heat coagulates the white of an egg."));
        dics.add(new Dictionary("COAGULUM","The thick, curdy precipitate formed by the coagulation of albuminous matter; any mass of coagulated matter, as a clot of bloot."));
        dics.add(new Dictionary("COALITIONIST","One who joins or promotes a coalition; one who advocates coalition."));
        dics.add(new Dictionary("COALSACK","Any one of the spaces in the Milky Way which are very black, owing to the nearly complete absence of stars; esp., the large space near the Southern Cross sometimes called the Black Magellanic Cloud."));
        dics.add(new Dictionary("COARSELY","In a coarse manner; roughly; rudely; inelegantly; uncivilly; meanly."));
        dics.add(new Dictionary("LEUCOMA","A white opacity in the cornea of the eye; — called also albugo."));
        dics.add(new Dictionary("LEUCOPATHY","The state of an albino, or of a white child of black parents."));
        dics.add(new Dictionary("LEUCORRHOEA","A discharge of a white, yellowish, or greenish, viscid mucus, resulting from inflammation or irritation of the membrane lining the genital organs of the female; the whites. Dunglison."));
        dics.add(new Dictionary("LEUCOSOID","Like or pertaining to the Leucosoidea, a tribe of marine crabs including the box crab or Calappa."));
        dics.add(new Dictionary("LEUCOTURIC","Pertaining to, or designating, a nitrogenous organic substance of the uric acid group, called leucoturic acid or oxalantin. See Oxalantin."));
        dics.add(new Dictionary("LEVANTER","One who levants, or decamps. [Colloq. Eng.]"));
        dics.add(new Dictionary("LIFESTRING","A nerve, or string, that is imagined to be essential to life.Daniel."));
        dics.add(new Dictionary("MONOPHYSITICAL","Of or pertaining to Monophysites, or their doctrines."));
        dics.add(new Dictionary("MONOPHYSITE","One of a sect, in the ancient church, who maintained that the human and divine in Jesus Christ constituted but one composite nature. Also used adjectively."));
        dics.add(new Dictionary("MONORHYME","A composition in verse, in which all the lines end with the same rhyme."));
        dics.add(new Dictionary("MONOSTICHOUS","Arranged in a single row on one side of an axis, as the flowers in grasses of the tribe Chloridæ."));
        dics.add(new Dictionary("MOONSTONE","A nearly pellucid variety of feldspar, showing pearly or opaline reflections from within. It is used as a gem. The best specimens come from Ceylon."));
        dics.add(new Dictionary("MORMAL","A bad sore; a gangrene; a cancer. [Obs.] [Written also morrimal and mortmal.] Chaucer."));
        dics.add(new Dictionary("MOUILLATION","The act of uttering the sound of a mouillé letter."));
        dics.add(new Dictionary("PLAGIOCLASE","A general term used of any triclinic feldspar. See the Note under Feldspar."));
        dics.add(new Dictionary("PLAIN","To lament; to bewail; to complain. [Archaic & Poetic] Milton. We with piteous heart unto you pleyne. Chaucer."));
        dics.add(new Dictionary("PLANARIA","Any species of turbellarian worms belonging to Planaria, and many allied genera. The body is usually flat, thin, and smooth. Some species, in warm countries, are terrestrial."));
        dics.add(new Dictionary("PLANE","Any tree of the genus Platanus."));
        dics.add(new Dictionary("PLASMIN","A proteid body, separated by some physiologists from blood plasma. It is probably identical with fibrinogen."));
        dics.add(new Dictionary("PLATINIRIDIUM","A natural alloy of platinum and iridium occurring in grayish metallic rounded or cubical grains with platinum."));
        dics.add(new Dictionary("PLATTEN","To flatten and make into sheets or plates; as, to platten cylinder glass."));
        dics.add(new Dictionary("STARTISH","Apt to start; skittish; shy; — said especially of a horse."));
        dics.add(new Dictionary("STATABLE","That can be stated; as, a statablegrievance; the question at issue is statable."));
        dics.add(new Dictionary("STATIONERY","The articles usually sold by stationers, as paper, pens, ink, quills, blank books, etc."));
        dics.add(new Dictionary("STATOCRACY","Government by the state, or by political power, in distinction from government by ecclesiastical power. [R.] O. A. Brownson."));
        dics.add(new Dictionary("STEARATE","A salt of stearic acid; as, ordinary soap consists largely of sodium or potassium stearates"));
        dics.add(new Dictionary("STEAROLIC","Of, pertaining to, or designating, an acid of the acetylene series, isologous with stearis acid, and obtained, as a white crystalline substance, from oleïc acid."));
        dics.add(new Dictionary("STEATITE","A massive variety of talc, of a grayish green or brown color. It forms extensive beds, and is quarried for fireplaces and for coarse utensils. Called also potstone, lard stone, and soapstone."));
        dics.add(new Dictionary("STEEVE","To project upward, or make an angle with the horizon or with the line of a vessel's keel; — said of the bowsprit, etc."));
        dics.add(new Dictionary("STERILITY","Quality of being sterile; infecundity; also, the state of being free from germs or spores"));
        //add more ...

    }

    // add word to dictionary
    public String addItem(String word, String def) throws RemoteException {
        dics.add(new Dictionary(word,def));
        return "add success !";
    }

    // delete word from dictionary
    public String deleteItem(String word) throws RemoteException {
        for (Dictionary d:dics){
            if(d.getWord().equals(word)){
                dics.remove(d);
                return "delete success !";
            }
        }
        return "delete failed ! (do not find such word)";
    }

    // modify word to dictionary
    public String modifyItem(String word, String def) throws RemoteException {
        for (Dictionary d:dics){
            if(d.getWord().equals(word)){
                dics.remove(d);
                dics.add(new Dictionary(word,def));
                return "modify success !";
            }
        }
        return "modify failed ! (do not find such word)";
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
