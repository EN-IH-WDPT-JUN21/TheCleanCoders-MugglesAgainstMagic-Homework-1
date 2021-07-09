import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Name {
    // Empty ArrayLists that will be filled with the contents of the files
    private final ArrayList<String> namesArray = new ArrayList<>();
    private final ArrayList<String> surnamesArray = new ArrayList<>();

    public Name() throws FileNotFoundException {
        // "open" both files to be read
        File nameFile = new File("names.txt");
        File surnameFile = new File("surnames.txt");

        //Instantiating scanner class objects for each file
        Scanner nameList = new Scanner(nameFile);
        Scanner surnameList = new Scanner(surnameFile);

        //Create an ArrayList of names:
        while (nameList.hasNextLine()) {
            namesArray.add(nameList.nextLine());
        }
        //and the ArrayList of surnames:
        while (surnameList.hasNextLine()) {
            surnamesArray.add(surnameList.nextLine());
        }
        //Good practice: close scanner buffers
        nameList.close();
        surnameList.close();
    }

    public String generateRandomName() {
        Random random = new Random();
        int indexName = random.nextInt(namesArray.size());
        int indexSurname = random.nextInt(surnamesArray.size());
        return namesArray.get(indexName) + " " + surnamesArray.get(indexSurname);
    }
}
