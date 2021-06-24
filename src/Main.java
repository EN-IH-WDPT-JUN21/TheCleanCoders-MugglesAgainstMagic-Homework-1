public class Main {
    public static void main(String[] args) {
        Warrior warrior = new Warrior("Dudley");
        Wizard wizard = new Wizard("Harry");

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
