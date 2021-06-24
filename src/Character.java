import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public abstract class Character {
    // Properties
    private int id = 0;
    private String name;
    private int hp;
    private boolean isAlive = true;

    public Character() throws FileNotFoundException { //the names will be random, so we don't need any parameter on this constructor
        setName(name);
        setAlive(isAlive);
        id++; //Each time a Character is created, the id increases. It will be unique for each Character.
            //Still not sure what we'll use it for, though.
    }

    public void receiveDamage(int damage) {
        setHp(getHp() - damage);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws FileNotFoundException {
        File nameFile = new File("names.txt");
        File surnameFile = new File("surnames.txt");
        Scanner nameList = new Scanner(nameFile);
        Scanner surnameList = new Scanner(surnameFile);
        Random random = new Random();
        int indexName = random.nextInt(200);
        int indexSurname = random.nextInt(100);
        for (int i = 0; i < indexName; i++) {
            nameList.nextLine();
        }
        for (int i = 0; i < indexSurname; i++) {
            surnameList.nextLine();
        }

        name = nameList.nextLine() + " " +(surnameList.nextLine());
        this.name = name;

    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
