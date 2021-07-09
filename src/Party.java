import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Party {

    //Properties

    private String name;
    private int partySize;
    private List<Character> aliveCharacters;
    final static int MAX_SIZE = 7;

    //Constructor

    public Party(){
        setAliveCharacters(new ArrayList<>());
    }

    public List<Character> generateRandomCharacterList(int partySize) throws FileNotFoundException {
        //Creates an ArrayList that accepts both Warrior and Wizard objects
        List<Character> characterList = new ArrayList<>();

        //The following populates the ArrayList with as random elements as the party size
        for (int i = 0; i < partySize; i++) {
            characterList.add(generatePartyElement());

            // If the name generated is already in the party, add Jr at the end
            for(int j = 0; j < i; j++) {
                if(characterList.get(i).getName().equals(characterList.get(j).getName())){
                    characterList.get(i).setName(characterList.get(i).getName() + " Jr");
                }
            }
        }

        return characterList;
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

    public String toString() {
        return "=== " + name.toUpperCase() + " ===" + '\n' + aliveMembersString();
    }

    public String aliveMembersString() {
        StringBuilder result = new StringBuilder();
        int count = 1;
        for (Character member : aliveCharacters) {
            result.append(count++).append(": ").append(member.toString()).append("\n");
        }
        return result.toString();
    }

    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setPartySize(int partySize) {
        this.partySize = partySize;
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

    public List<Character> getAliveCharacters() {
        return this.aliveCharacters;
    }
}