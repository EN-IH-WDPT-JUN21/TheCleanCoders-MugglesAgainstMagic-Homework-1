public abstract class Character {
    // Properties
    private int id;
    private String name;
    private int hp;
    private boolean isAlive = true;

    public Character(String name) {
        setName(name);
        setAlive(isAlive);
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

    public void setName(String name) {
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
