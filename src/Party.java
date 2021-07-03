import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Party {

    //Properties

    private String name;
    private int partySize;
    private List<Character> warParty;
    private List<Character> aliveCharacters;

    //Constructor

    public Party(String name, int partySize) throws FileNotFoundException{
        setName(name);
        setPartySize(partySize);
        this.warParty = generateRandomParty(partySize);
        this.aliveCharacters = this.warParty;
    }

    public Party() throws FileNotFoundException{
    }

    public List<Character> generateRandomParty(int partySize) throws FileNotFoundException {
        //Creates an ArrayList that accepts both Warrior and Wizard objects
        List<Character> party = new ArrayList<>();

        //The following populates the ArrayList with as random elements as the party size
        for (int i = 0; i < partySize; i++) {
            party.add(generatePartyElement());
            if(i == partySize){
                /*Prints the stats of each one of the elements of the warParty Array
                System.out.println("War Party elements are:");
                for (Character element : party) {
                    System.out.println(element);
                }*/
                return party;
            }
        }

        return party;
    }

    public static Character generatePartyElement() throws FileNotFoundException {
        //This method creates a random wizard or warrior
        Random random = new Random();
        int x = random.nextInt(2);
        if(x == 0) {
            return new Warrior();
        } else {
            return new Wizard();
        }
    }

    public String warPartyString() {
        String result = "";
        int count = 1;
        for (Character member : warParty) {
            result += count++ +": " + member.toString() + "\n";
        }
        return result;
    }

    public String aliveMembersString() {
        String result = "";
        int count = 1;
        for (Character member : aliveCharacters) {
            result += count++ +": " + member.toString() + "\n";
        }
        return result;
    }

    public String toString() {
        return "=== " + name.toUpperCase() + " ===" + '\n' +
                "· originalPartyMembers(" + partySize +"): " + '\n' + warPartyString() + '\n' +
                "· aliveCharacters:" + '\n' + aliveMembersString() + '\n';
    }

    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setPartySize(int partySize) {
        this.partySize = partySize;
    }

    public void setWarParty(List<Character> warParty) {
        this.warParty = warParty;
    }

    public void setAliveCharacters(List<Character> aliveCharacters) {
        this.aliveCharacters = aliveCharacters;
    }

    public void updateAliveCharacters(int originalCharacterId, Character stillAliveCharacter){
        this.aliveCharacters.set(originalCharacterId, stillAliveCharacter);
    }

    // Getters

    public String getName() {
        return this.name;
    }

    public int getPartySize() {
        return this.partySize;
    }

    public List<Character> getWarParty() {
        return this.warParty;
    }

    public List<Character> getAliveCharacters() {
        return this.aliveCharacters;
    }
}