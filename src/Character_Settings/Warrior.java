package Character_Settings;

import Interface.Attacker;

public class Warrior extends Character implements Attacker {
    private int stamina;
    private int strength;

    // Constructors

    public Warrior(String name) {
        super(name);

        // hp: random between 100-200 to warriors
        int hp = (int)(Math.random() * 100) + 100;
        // stamina: random between 10-50
        int stamina = (int)(Math.random() * 40) + 10;
        // strength: random between 1-10
        int strength = (int)(Math.random() * 9) + 1;

        setHp(hp);
        setStamina(stamina);
        setStrength(strength);
        setAlive(true);
    }

    public Warrior(String name, int hp, int stamina, int strength) {
        super(name);
        setHp(hp);
        setStamina(stamina);
        setStrength(strength);
    }

    // Methods

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
        return this.stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getStrength() {
        return this.strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}