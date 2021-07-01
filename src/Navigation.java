import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Navigation {
    static Scanner scan = new Scanner(System.in);
    static int answer;

    public static boolean Navigate(Party party1, Party party2) throws FileNotFoundException {
        MainMenuDraw();
        answer = TakeMenuIntAnswer();
        switch (answer) {
            case 1:
                TeamMenuDraw();
                answer = TakeMenuIntAnswer();
                switch (answer) {
                    case 1: //Create new team
                        party1.setName(TeamNameQDraw(1));
                        party1.setPartySize(TeamSizeQDraw(1));
                        party2.setName(TeamNameQDraw(2));
                        party2.setPartySize(TeamSizeQDraw(2));
                        RandomQMenuDraw();
                        answer = TakeMenuIntAnswer();
                        switch (answer) {
                            case 1:
                                party1.setWarParty(party1.generateRandomParty(party1.getPartySize()));
                                party2.setWarParty(party2.generateRandomParty(party2.getPartySize()));
                                Battle battle1 = new Battle(party1, party2);
                                System.out.println(party1);
                                System.out.println(party2);
                                battle1.battle(4, 3);
                                battle1.battle(3, 2);
                                battle1.battle(0, 0);
                                //TODO: What else?
                            case 2:

                                //TODO: in Party class write methods to manually add characters

                                return false;
                            //Add new characters to team
                        }
                        break;
                    case 2: //Import team from a csv file
                        ImportTeamFileMenu();
                        Battle battle1 = new Battle(party1, party2);
                        System.out.println(party1);
                        System.out.println(party2);
                        battle1.battle(4, 3);
                        battle1.battle(3, 2);
                        battle1.battle(0, 0);
                        //TODO: What else?
                }
                break;
            case 2:
                return false;
            default:
                return true;
        }
        return true;
    }


    public static void MainMenuDraw() {
        System.out.println("Welcome to our game 'Muggle against Magic'\n");
        System.out.println("1 : Play new game");
        System.out.println("2 : Exit");
    }

    public static void TeamMenuDraw() {
        System.out.println("Team Creator'\n");
        System.out.println("1 : Create new teams");
        System.out.println("2 : Import team from a csv file");
    }

    public static void RandomQMenuDraw() {
        System.out.println("Generate random characters?");
        System.out.println("1 : yes");
        System.out.println("2 : no");
    }

    public static String TeamNameQDraw(int i) {
        System.out.println("Please enter name of the " + i + " team:");
        return TakeStringAnswer();
    }

    public static int TeamSizeQDraw(int i) {
        System.out.println("Please enter size of the " + i + " team:");
        return TakeIntAnswer();
    }

    public static void ImportTeamFileMenu() {
        System.out.println("Please enter the path of the csv file: ");
        String path = TakeStringAnswer();
        ImportExport.readPartyFromFile(path);
    }

    public static void ManualCharacterMenu(Party Team) {
        System.out.println("PLease define characters for " + Team.getName() + "team");
        List<Character> CharactersList;
        /*TODO: Constructor in Character/Wizard/Warrior class which take name and type of character
        after that:
        writePartyToFile(Team.getWarParty());
         */
    }

    public static int TakeMenuIntAnswer() {
        int choice = 0;
        while (true) {
            try {
                System.out.print(">");
                choice = scan.nextInt();
                scan.nextLine();
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer [1 or 2]:");
                scan.next();
                continue;
            }
        }
    }

    public static int TakeIntAnswer() {
        int choice = 0;
        while (true) {
            try {
                System.out.print(">");
                choice = scan.nextInt();
                scan.nextLine();
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer:");
                scan.next();
                continue;
            }
        }
    }

    public static String TakeStringAnswer() {
        Scanner scan = new Scanner(System.in);
        String answer = "";
        while (true) {
            try {
                System.out.print(">");
                answer = scan.nextLine();
                return answer;
            } catch (InputMismatchException e) {
                System.out.println("Please enter an String: ");
                scan.next();
                continue;
            }
        }
    }
}