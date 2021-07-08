import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Party party1 = new Party();
        Party party2 = new Party();
        Navigation.goToMainMenu(party1, party2);

    }
}