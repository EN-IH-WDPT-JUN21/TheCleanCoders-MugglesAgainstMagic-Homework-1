import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ImportExport {

    public static void readPartyFromFile (String filePath) {
        String fileName = "Party2.csv";
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
        int lineNo = 1;
        for (List<String> line : lines) {
            int columnNo = 1;
            for (String value : line) {
                System.out.println("Line " + lineNo + " Column " + columnNo + ": " + value);
                columnNo++;
            }
            lineNo++;
        }
    }

    public static void writePartyToFile (String filePath, List<Character> members) throws IOException {
        FileWriter writer = new FileWriter(filePath, false);
        for (int i = 0; i < members.size(); i++){
            Character c = members.get(i);
            writer.write(c.getName() + "," );
            writer.write(c.getHp() + "," );
            if(c instanceof Wizard){
                writer.write(((Wizard) c).getMana() + ",");
                writer.write(((Wizard) c).getIntelligence() + ",");

            } else if (c instanceof Warrior) {
                writer.write(((Warrior) c).getStamina() + ",");
                writer.write(((Warrior) c).getStrength() + ",");
            }
            writer.write("\n");
        }
        writer.close();
    }
}

