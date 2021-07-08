import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class Character implements Attacker {
    // Properties
    private final int id;
    private static int index = 0; //This works. Had to include a new variable
    private String name;
    private int hp;
    private boolean isAlive = true;

    //Constructor (overloaded)

    public Character(String name) {
        setName(name);
        setAlive(isAlive);
        index++;
        this.id = index;
    }

    // Throws a random name
    public Character() throws FileNotFoundException { //the names will be random, so we don't need any parameter on this constructor
        setName();
        setAlive(isAlive);
        index++;
        this.id = index;
    }

    public void receiveDamage(int damage) {
        setHp(getHp() - damage);
    }

    public void addJrToNameIfNeeded(List<Character> list) {
        for (Character character : list) {
            if (character.getName().equals(getName())) {
                setName(getName() + " Jr");
            }
        }
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName() throws FileNotFoundException {

        // "open" both files to be read
        File nameFile = new File("names.txt");
        File surnameFile = new File("surnames.txt");

        //Instantiating scanner class objects for each file
        Scanner nameList = new Scanner(nameFile);
        Scanner surnameList = new Scanner(surnameFile);

        // Empty ArrayLists that will be filled with the contents of the files
        ArrayList<String> namesArray = new ArrayList<>();
        ArrayList<String> surnamesArray = new ArrayList<>();

        //Create an ArrayList of names:
        while (nameList.hasNextLine()) {
            namesArray.add(nameList.nextLine());
        }
        //and the ArrayList of surnames:
        while (surnameList.hasNextLine()) {
            surnamesArray.add(surnameList.nextLine());
        }

        Random random = new Random();
        int indexName = random.nextInt(namesArray.size());
        int indexSurname = random.nextInt(surnamesArray.size());

        //FINALLY, define the name of this Character
        this.name = namesArray.get(indexName) + " " + surnamesArray.get(indexSurname);

        //Good practice: close scanner buffers
        nameList.close();
        surnameList.close();
    }

    // Getters and setters

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}