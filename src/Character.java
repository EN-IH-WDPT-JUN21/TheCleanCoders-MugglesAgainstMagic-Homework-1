import java.util.List;

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