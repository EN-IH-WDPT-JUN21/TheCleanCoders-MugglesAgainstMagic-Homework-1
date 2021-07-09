import java.util.InputMismatchException;
import java.util.Scanner;

public class Answer {

    public static int takeMenuOption(int menuSize) {
        Scanner scan = new Scanner(System.in);
        int choice = 0;
        while (true) {
            try {
                do {
                    if (choice < 1 || choice > menuSize) {
                        System.out.println("\nPlease enter an integer [1 to " + menuSize + "]:");
                    }
                    System.out.print("> ");
                    choice = scan.nextInt();
                    scan.nextLine();
                }
                while ((choice < 1 || choice > menuSize));
                return choice;
            } catch (InputMismatchException e) {
                scan.next();
            }
        }
    }

    public static int takeTeamSize() {
        Scanner scan = new Scanner(System.in);
        int choice = 0;
        while (true) {
            try {
                do {
                    if (choice < 1 || choice > Party.MAX_SIZE) {
                        System.out.println("\nPlease enter an integer [1 to " + Party.MAX_SIZE + "]:");
                    }
                    System.out.print("> ");
                    choice = scan.nextInt();
                    scan.nextLine();
                }
                while ((choice < 1 || choice > Party.MAX_SIZE));
                return choice;
            } catch (InputMismatchException e) {
                scan.next();
            }
        }
    }

    public static String takeString() {
        Scanner scan = new Scanner(System.in);
        String answer = "";
        while (true) {
            try {
                do {
                    if (answer.isBlank()) {
                        System.out.println("\nStrings can't be empty");
                    }
                    System.out.print("> ");
                    answer = scan.nextLine();
                }
                while (answer.isBlank());
                return answer;
            } catch (InputMismatchException e) {
                System.out.println("\nPlease enter a String: ");
                scan.next();
            }
        }
    }

    public static int takeCombatantId(Party party) {
        Scanner scan = new Scanner(System.in);
        int choice = 0;
        while (true) {
            try {
                do {
                    if (choice < 0 || choice > (party.getAliveCharacters().size() - 1)) {
                        System.out.println("\nPlease enter an integer [1 to " + party.getAliveCharacters().size() + "]:");
                    }
                    System.out.print("> ");
                    choice = scan.nextInt() - 1;
                    scan.nextLine();
                }
                while ((choice < 0 || choice > (party.getAliveCharacters().size() - 1)));
                return choice;
            } catch (InputMismatchException e) {
                scan.next();
            }
        }
    }
}
