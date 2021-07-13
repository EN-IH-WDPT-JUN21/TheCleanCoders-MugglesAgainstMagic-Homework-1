package Character_Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.jar.Attributes;

public class Party {

    //Properties

    private String name;
    private int partySize;
    private List<Character> aliveCharacters;
    public final static int MAX_SIZE = 7;

    //Constructor

    public Party() {
        setAliveCharacters(new ArrayList<>());
    }

    public List<Character> generateRandomCharacterList(int partySize, Name randomNameDatabase) {
        //Creates an ArrayList that accepts both Character_Settings.Warrior and Character_Settings.Wizard objects
        List<Character> characterList = new ArrayList<>();

        //The following populates the ArrayList with as random elements as the party size
        for (int i = 0; i < partySize; i++) {
            Character newRandomCharacter = generateRandomCharacter(randomNameDatabase);
            newRandomCharacter.addJrToNameIfNeeded(characterList);
            characterList.add(newRandomCharacter);
        }

        return characterList;
    }

    public static Character generateRandomCharacter(Name randomNameDatabase) {
        //This method creates a random wizard or warrior
        String characterName = randomNameDatabase.generateRandomName();
        Random random = new Random();
        int x = random.nextInt(2);
        if (x == 0) {
            return new Warrior(characterName);
        } else {
            return new Wizard(characterName);
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

    public void updateAliveCharacters(int originalCharacterId, Character stillAliveCharacter) {
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