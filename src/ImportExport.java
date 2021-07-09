import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ImportExport {

    public static Party readPartyFromFile(String fileName) {
        File file = new File(fileName);
        List<List<String>> lines = new ArrayList<>();
        Scanner inputStream;

        try {
            inputStream = new Scanner(file);

            while (inputStream.hasNext()) {
                String line = inputStream.next();
                String[] valuesList = line.split(",");
                lines.add(Arrays.asList(valuesList));
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Party party = new Party();
        String partyName = fileName.replace(".csv", "").replace("_", " ");
        List<Character> members = party.getAliveCharacters();
        party.setName(partyName);
        party.setPartySize(lines.size());

        for (List<String> valuesList : lines) {
            members.add(createCharacterFromValuesList(valuesList));
        }
        return party;
    }

    public static Character createCharacterFromValuesList(List<String> valuesList) {
        String name = valuesList.get(0).replace("_", " ");
        String className = valuesList.get(2);
        int hp = Integer.parseInt(valuesList.get(1));
        Character newCharacter = null;

        if (className.equals("Wizard")) {
            int mana = Integer.parseInt(valuesList.get(3));
            int intelligence = Integer.parseInt(valuesList.get(4));

            newCharacter = new Wizard(name, hp, mana, intelligence);
        } else if (className.equals("Warrior")) {
            int stamina = Integer.parseInt(valuesList.get(3));
            int strength = Integer.parseInt(valuesList.get(4));

            newCharacter =  new Warrior(name, hp, stamina, strength);
        }

        return newCharacter;
    }

    public static void writePartyToFile(Party party) throws IOException {
        String partyNameWithUnderscores = party.getName().replace(" ", "_");
        String filePath = partyNameWithUnderscores + ".csv";
        List<Character> members = party.getAliveCharacters();
        FileWriter writer = new FileWriter(filePath, false);

        for (Character character : members) {
            writer.write(stringifyCharacter(character));
        }
        writer.close();
    }

    public static String stringifyCharacter(Character character) {
        StringBuilder characterString = new StringBuilder();

        String characterNameWithUnderscore = character.getName().replace(" ", "_");

        characterString.append(characterNameWithUnderscore).append(",");
        characterString.append(character.getHp()).append(",");

        if (character instanceof Wizard) {
            characterString.append("Wizard,");
            characterString.append(((Wizard) character).getMana()).append(",");
            characterString.append(((Wizard) character).getIntelligence()).append(",");
        } else if (character instanceof Warrior) {
            characterString.append("Warrior,");
            characterString.append(((Warrior) character).getStamina()).append(",");
            characterString.append(((Warrior) character).getStrength()).append(",");
        }
        characterString.append("\n");

        return characterString.toString();
    }
}
