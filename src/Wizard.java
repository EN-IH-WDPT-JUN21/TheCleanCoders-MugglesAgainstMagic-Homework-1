import java.io.FileNotFoundException;

public class Wizard extends Character implements Attacker {
    // Properties
    private int mana;
    private int intelligence;

    // Constructor (overloaded)

    public Wizard(String name) {
        super(name);

        // hp: random between 50-100 for wizards
        int hp = (int)(Math.random() * 50) + 50;
        // mana: random between 10-50
        int mana = (int)(Math.random() * 40) + 10;
        // intelligence: random between 1-50
        int intelligence = (int)(Math.random() * 49) + 1;

        setHp(hp);
        setMana(mana);
        setIntelligence(intelligence);
        setAlive(true);
    }

    // Throws a random name
    public Wizard() throws FileNotFoundException {
        super();

        // hp: random between 50-100 for wizards
        int hp = (int)(Math.random() * 50) + 50;
        // mana: random between 10-50
        int mana = (int)(Math.random() * 40) + 10;
        // intelligence: random between 1-50
        int intelligence = (int)(Math.random() * 49) + 1;

        setHp(hp);
        setMana(mana);
        setIntelligence(intelligence);
        setAlive(true);
    }

    @Override
    public int attack() {
        int damage;

        if (getMana() >= 5) {
            damage = performFireball();
        } else {
            damage = performStaffHit();
        }

        return damage;
    }

    public int performFireball() {
        int damage = getIntelligence();

        System.out.println("Wizard " + getName() + " is casting Fireball causing damage of " + damage + ".");

        setMana(getMana() - 5);

        return damage;
    }

    public int performStaffHit() {
        int damage = 2;

        System.out.println("Wizard " + getName() + " is hitting opponent with his wand causing damage of "  + damage + ".");

        setMana(getMana() + 1);

        return damage;
    }

    public String toString() {
        return "Wizard " + getName() + ", HP: " + getHp() + ", Mana: " + getMana() + ", Intelligence: " + getIntelligence();
    }

    // Getters and Setters
    public int getMana() {
        return this.mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getIntelligence() {
        return this.intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
}