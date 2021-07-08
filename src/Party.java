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
    final static int MAX_SIZE = 7;

    //Constructor

    public Party(){
        setWarParty(new ArrayList<>());
        setAliveCharacters(this.warParty);
    }

    public List<Character> generateRandomParty(int partySize) throws FileNotFoundException {
        //Creates an ArrayList that accepts both Warrior and Wizard objects
        List<Character> party = new ArrayList<>();

        //The following populates the ArrayList with as random elements as the party size
        for (int i = 0; i < partySize; i++) {
            party.add(generatePartyElement());

            // If the name generated is already in the party, add Jr at the end
            for(int j = 0; j < i; j++) {
                if(party.get(i).getName().equals(party.get(j).getName())){
                    party.get(i).setName(party.get(i).getName() + " Jr");
                }
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
        StringBuilder result = new StringBuilder();
        int count = 1;
        for (Character member : warParty) {
            result.append(count++).append(": ").append(member.toString()).append("\n");
        }
        return result.toString();
    }

    public String aliveMembersString() {
        StringBuilder result = new StringBuilder();
        int count = 1;
        for (Character member : aliveCharacters) {
            result.append(count++).append(": ").append(member.toString()).append("\n");
        }
        return result.toString();
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