import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        //Party party1 = new Party("Party1", 5);
        //Party party2 = new Party("Party2", 5);

        Party party1 = new Party();
        Party party2 = new Party();


        //if(Navigation.Navigate(party1, party2)){
        Navigation.Navigate(party1, party2);
        // }


        /*ImportExport.writePartyToFile("Party1.csv", party1.getAliveCharacters());


        Battle battle1 = new Battle(party1, party2);

        System.out.println(party1);
        System.out.println(party2);


        battle1.battle(4, 3);
        battle1.battle(3, 2);
        battle1.battle(0, 0);

        System.out.println();*/


    }
}