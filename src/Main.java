import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Warrior warrior = new Warrior();
        Wizard wizard = new Wizard();

        System.out.println(warrior);
        System.out.println(wizard);

        System.out.println();

        while (warrior.getHp() > 0 && wizard.getHp() > 0) {

            warrior.receiveDamage(wizard.attack());
            wizard.receiveDamage(warrior.attack());

            System.out.println();

            System.out.println(warrior);
            System.out.println(wizard);

            System.out.println();

        }
    }
}
