package Main;

import Menu_Management.Answer;
import Menu_Management.Navigation;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
         Navigation.goToMainMenu();
         Answer.scan.close();
    }
}