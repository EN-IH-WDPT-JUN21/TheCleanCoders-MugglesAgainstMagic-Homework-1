import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ImportExport {

    public static Party readPartyFromFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        List<List<String>> lines = new ArrayList<>();
        Scanner inputStream;

        try {
            inputStream = new Scanner(file);

            while (inputStream.hasNext()) {
                String line = inputStream.next();
                String[] values = line.split(",");
                lines.add(Arrays.asList(values));
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Party party = new Party();
        String partyName = fileName.replace(".csv", "").replace("_", " ");
        party.setName(partyName);
        party.setPartySize(lines.size());

        for (List<String> line : lines) {
            String name = line.get(0).replace("_", " ");
            String className = line.get(2);
            int hp = Integer.parseInt(line.get(1));

            if (className.equals("Wizard")) {
                int mana = Integer.parseInt(line.get(3));
                int intelligence = Integer.parseInt(line.get(4));
                party.getWarParty().add(new Wizard(name, hp, mana, intelligence));
            } else if (className.equals("Warrior")) {
                int stamina = Integer.parseInt(line.get(3));
                int strength = Integer.parseInt(line.get(4));
                party.getWarParty().add(new Warrior(name, hp, stamina, strength));
            }
        }
        return party;
    }

    public static void writePartyToFile(Party party) throws IOException {
        String filePath = party.getName().replace(" ", "_") + ".csv";
        List<Character> members = party.getAliveCharacters();
        FileWriter writer = new FileWriter(filePath, false);

        for (Character c : members) {
            writer.write(c.getName().replace(" ", "_") + ",");
            writer.write(c.getHp() + ",");

            if (c instanceof Wizard) {
                writer.write("Wizard,");
                writer.write(((Wizard) c).getMana() + ",");
                writer.write(((Wizard) c).getIntelligence() + ",");
            } else if (c instanceof Warrior) {
                writer.write("Warrior,");
                writer.write(((Warrior) c).getStamina() + ",");
                writer.write(((Warrior) c).getStrength() + ",");
            }
            writer.write("\n");
        }
        writer.close();
    }
}
