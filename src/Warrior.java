import java.io.FileNotFoundException;

public class Warrior extends Character implements Attacker {
    // Properties
    private int stamina;
    private int strength;

    public Warrior() throws FileNotFoundException {
        super();

        // hp: random between 100-200 to warriors
        int hp = (int)(Math.random() * 100) + 100;
        // stamina: random between 10-50
        int stamina = (int)(Math.random() * 40) + 10;
        // strength: random between 1-10
        int strength = (int)(Math.random() * 9) + 1;

        setHp(hp);
        setStamina(stamina);
        setStrength(strength);
    }

    public Warrior(String name) throws FileNotFoundException {
        super();
        setName(name);

        // hp: random between 100-200 to warriors
        int hp = (int)(Math.random() * 100) + 100;
        // stamina: random between 10-50
        int stamina = (int)(Math.random() * 40) + 10;
        // strength: random between 1-10
        int strength = (int)(Math.random() * 9) + 1;

        setHp(hp);
        setStamina(stamina);
        setStrength(strength);
    }

    @Override
    public int attack() {
        int damage;

        if (getStamina() >= 5) {
            damage = performHeavyAttack();
        } else {
            damage = performWeakAttack();
        }

        return damage;
    }

    public int performHeavyAttack() {
        int damage = getStrength();

        System.out.println("Muggle " + getName() + " is performing Heavy Attack causing damage of "  + damage + ".");

        setStamina(getStamina() - 5);

        return damage;
    }

    public int performWeakAttack() {
        int damage = getStrength() / 2;

        System.out.println("Muggle " + getName() + " is performing Weak Attack causing damage of "  + damage + ".");

        setStamina(getStamina() + 1);

        return damage;
    }

    public String toString() {
        return "Muggle " + getName() + ", HP: " + getHp() + ", Stamina: " + getStamina() + ", Strength: " + getStrength();
    }

    // Getters and Setters
    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setName(String name){ super.name = name; }
}