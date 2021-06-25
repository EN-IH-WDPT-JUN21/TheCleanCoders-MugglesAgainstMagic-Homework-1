import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Warrior warrior = new Warrior();
        Wizard wizard = new Wizard();

        //Creates an ArrayList that accepts both Warrior and Wizard objects
        ArrayList<Object> warParty = new ArrayList<Object>();
        //The following populates the ArrayList with 10 random elements
      for (int i = 0; i < 10; i++) {
          warParty.add(generatePartyElement());
      }
        //Prints the stats of each one of the elements of the warParty Array
        System.out.println("War Party elements are:");
      for (Object element : warParty) {
          System.out.println(element);
      }

        /*
        System.out.println(warParty.get(0));
        System.out.println(warParty.get(1));


        System.out.println(warrior);
        System.out.println(warrior.getId());
        System.out.println(wizard);
        System.out.println(wizard.getId());

        System.out.println();

        while (warrior.getHp() > 0 && wizard.getHp() > 0) {

            warrior.receiveDamage(wizard.attack());
            wizard.receiveDamage(warrior.attack());

            System.out.println();

            System.out.println(warrior);
            System.out.println(wizard);

            System.out.println();

        }

         */
    }

    public static Object generatePartyElement() throws FileNotFoundException {
        //This method creates a random wizard or warrior
        Random random = new Random();
        int x = random.nextInt(2);
        if(x == 0) {
            return new Warrior();
        } else {
            return new Wizard();
        }
    }
}
