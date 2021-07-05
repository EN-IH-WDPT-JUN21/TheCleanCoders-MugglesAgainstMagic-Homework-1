import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Navigation {
    static Scanner scan = new Scanner(System.in);
    static List<Character> listParty1 = new ArrayList<>();
    static List<Character> listParty2 = new ArrayList<>();
    static int answer;
    static int comb1;
    static int comb2;
    final static int MAX_SIZE_TEAM = 7;

    public static int mainMenuDraw() {
        Lines.printUpper();
        Lines.printEmpty(1);
        System.out.println("Welcome to our game MUGGLES AGAINST MAGIC");
        Lines.printEmpty(1);
        System.out.println("      Created by TheCleanCoders");
        Lines.printEmpty(2);
        System.out.println("For the best experience resize your terminal so it fits 15 lines");
        Lines.printEmpty(1);
        System.out.println("Main menu");
        Lines.printEmpty(1);
        System.out.println("1 : Play new game");
        System.out.println("2 : Exit");
        //Return size of menu
        return 2;
    }

    public static boolean navigate(Party party1, Party party2) throws IOException, InterruptedException {
        answer = takeMenuIntAnswer(mainMenuDraw());
        switch (answer) {
            case 1: //Create new team
                teamMenuNav(party1, party2);
                break;
            case 2: //Exit game
                return false;
            default:
                return true;
        }
        return true;
    }

    public static int teamMenuDraw() {
        Lines.printUpper();
        Lines.printGameName();
        Lines.printEmpty(6);
        System.out.println("Team Creator\n");
        System.out.println("1 : Create new teams");
        System.out.println("2 : Import teams from CSV files");
        //Return size of menu
        return 2;
    }

    public static boolean teamMenuNav(Party party1, Party party2) throws IOException, InterruptedException {
        answer = takeMenuIntAnswer(teamMenuDraw());
        switch (answer) {
            case 1: //Create new team
                createNewTeamNav(party1, party2);
                break;
            case 2: //Import team from a csv file
                importFileMenu();
                break;
        }
        return false;
        //break;
    }

    public static int randomQMenuDraw() {
        Lines.printUpper();
        Lines.printGameName();
        Lines.printEmpty(7);
        System.out.println("Generate random characters?");
        System.out.println("1 : Yes");
        System.out.println("2 : No");
        //Return size of menu
        return 2;
    }

    public static void createNewTeamNav(Party party1, Party party2) throws IOException, InterruptedException {
        generateNewTeamNav(party1, party2);
        answer = takeMenuIntAnswer(randomQMenuDraw());
        switch (answer) {
            case 1: //Generate random characters
                generateNewCharRandomNav(party1, party2);
                exportCharactersToFileNav(party1, party2);
                battleNav(party1, party2);
                break;
            case 2: //Generate manual characters
                generateNewCharManualNav(party1, listParty1);
                generateNewCharManualNav(party2, listParty2);
                exportCharactersToFileNav(party1, party2);
                battleNav(party1, party2);
                break;
        }
    }

    public static void importFileMenu() throws IOException, InterruptedException {
        Party party1 = null;
        Party party2 = null;
        while(party1 == null) {
            party1 = importTeamFileMenu(1);
        }
        while(party2 == null) {
            party2 = importTeamFileMenu(2);
        }
        battleNav(party1, party2);
    }

    public static Party importTeamFileMenu(int teamNumber) throws IOException, InterruptedException {
        Lines.printUpper();
        Lines.printGameName();
        Lines.printEmpty(8);
        System.out.println("\nPlease enter the path of the CSV file for " + teamNumber + " team: ");
        String path = takeStringAnswer();
        File file = new File(path);
        while (!file.exists()) {
            Lines.printUpper();
            Lines.printGameName();
            Lines.printEmpty(6);
            System.out.println("Specified file does not exist.\n");
            System.out.println("1 : Try again");
            System.out.println("2 : Return to the main menu");
            answer = takeMenuIntAnswer(2);
            switch (answer) {
                case 1:
                    return null;
                case 2:
                    navigate(new Party(), new Party());
                    break;
            }
        }
        return ImportExport.readPartyFromFile(path);
    }

    public static String teamNameQDraw(int i) {
        Lines.printUpper();
        Lines.printGameName();
        Lines.printEmpty(8);
        System.out.println("\nPlease enter name of the " + i + " team:");
        return takeStringAnswer();
    }

    public static int teamSizeQDraw(int i) {
        Lines.printUpper();
        Lines.printGameName();
        Lines.printEmpty(8);
        System.out.println("\nPlease enter size of the " + i + " team:");
        return takeSizeAnswer();
    }

    public static void generateNewTeamNav(Party party1, Party party2) {
        party1.setName(teamNameQDraw(1));
        party1.setPartySize(teamSizeQDraw(1));
        party2.setName(teamNameQDraw(2));
        party2.setPartySize(teamSizeQDraw(2));
    }

    public static void generateNewCharRandomNav(Party party1, Party party2) throws FileNotFoundException {
        party1.setWarParty(party1.generateRandomParty(party1.getPartySize()));
        party1.setAliveCharacters(party1.getWarParty());
        party2.setWarParty(party2.generateRandomParty(party2.getPartySize()));
        party2.setAliveCharacters(party2.getWarParty());

    }

    public static int generateNewCharManualMenuDraw() {
        System.out.println("Choose type of the new character\n");
        System.out.println("1 : Muggle");
        System.out.println("2 : Wizard");
        //Return size of menu
        return 2;
    }

    public static void generateNewCharManualNav(Party party, List<Character> list) throws FileNotFoundException {
        list.clear();
        Lines.printUpper();
        Lines.printGameName();
        Lines.printEmpty(4);
        System.out.println("Adding new characters for " + party.getName());
        Lines.printEmpty(1);
        for (int i = 0; i < party.getPartySize(); i++) {
            answer = takeMenuIntAnswer(generateNewCharManualMenuDraw());
            System.out.println("Enter new character's name for " + party.getName() + "\n");
            switch (answer) {
                case 1:
                    String nameOfWarrior = characterNameDraw("Muggle");
                    Warrior warrior = new Warrior(nameOfWarrior);
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(j).getName().equals(warrior.getName())) {
                            warrior.setName(warrior.getName() + " Jr");
                        }
                    }
                    list.add(warrior);
                    break;
                case 2:
                    String nameOfWizard = characterNameDraw("Wizard");
                    Wizard wizard = new Wizard(nameOfWizard);
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(j).getName().equals(wizard.getName())) {
                            wizard.setName(wizard.getName() + " Jr");
                        }
                    }
                    list.add(wizard);
                    break;
            }
        }
        party.setWarParty(list);
        party.setAliveCharacters(list);
    }

    public static String characterNameDraw(String name) {
        Lines.printUpper();
        Lines.printGameName();
        Lines.printEmpty(8);
        System.out.println("\nPlease enter name of the new " + name);
        return takeStringAnswer();
    }

    public static int exportCharactersToFileMenuDraw() {
        Lines.printUpper();
        Lines.printGameName();
        Lines.printEmpty(6);
        System.out.println("Would you like to save created teams to CSV file?\n");
        System.out.println("1 : Yes");
        System.out.println("2 : No");
        //Return size of menu
        return 2;
    }

    public static void exportCharactersToFileNav(Party party1, Party party2) throws IOException, InterruptedException {
        answer = takeMenuIntAnswer(exportCharactersToFileMenuDraw());
        switch (answer) {
            case 1:
                ImportExport.writePartyToFile(party1);
                ImportExport.writePartyToFile(party2);
                battleNav(party1, party2);
                break;
            case 2:
                battleNav(party1, party2);
                break;
        }
    }

    public static void battleNav(Party party1, Party party2) throws IOException, InterruptedException {
        Battle battle1 = new Battle(party1, party2);

        while (party1.getAliveCharacters().size() > 0 && party2.getAliveCharacters().size() > 0) {
            charForDuelDraw(party1, party2, battle1);
            afterDuelNav(party1, party2, battle1);
        }
        String winner = "";

        Lines.printUpper();
        Lines.printGameName();
        Lines.printEmpty(5);

        if (party1.getAliveCharacters().size() == 0 && party2.getAliveCharacters().size() == 0) {
            System.out.println("It's a tie! All characters died....");
        } else if (party1.getAliveCharacters().size() == 0) {
            winner = party2.getName();
        } else if (party2.getAliveCharacters().size() == 0) {
            winner = party1.getName();
        }
        if (!winner.equals("")) {
            System.out.println(winner + " WINS!!!\n");
        }

        afterBattleNav(party1, party2, battle1);
    }

    public static void afterDuelNav(Party party1, Party party2, Battle battle) throws IOException {
        answer = takeMenuIntAnswer(afterDuelMenuDraw());
        switch (answer) {
            case 1:
                return;
        }
    }

    public static int afterDuelMenuDraw() {
        System.out.println("1 : Continue");
        //Return size of menu
        return 1;
    }

    public static void charForDuelDraw(Party party1, Party party2, Battle battle) throws InterruptedException {
        Lines.printUpper();
        Lines.printGameName();
        Lines.printEmpty(1);
        System.out.println("Characters of " + party1.getName() + '\n' + party1.aliveMembersString());
        Lines.printEmpty(7 - party1.getAliveCharacters().size());
        System.out.println("ROUND " + battle.getRoundNumber());
        System.out.println("Pick character of the " + party1.getName() + " for the next duel");
        comb1 = takeCombatId(party1);

        Lines.printUpper();
        Lines.printGameName();
        Lines.printEmpty(1);
        System.out.println("Characters of " + party2.getName() + '\n' + party2.aliveMembersString());
        Lines.printEmpty(7 - party2.getAliveCharacters().size());
        System.out.println("ROUND " + battle.getRoundNumber());
        System.out.println("Pick character of the " + party2.getName() + " for the next duel");
        comb2 = takeCombatId(party2);

        battle.battle(comb1, comb2);
    }

    public static int afterBattleMenuDraw() {
        System.out.println("1 : Show graveyard");
        System.out.println("2 : Play new game");
        System.out.println("3 : Exit");
        //Return size of menu
        return 3;
    }

    public static void afterBattleNav(Party party1, Party party2, Battle battle) throws IOException, InterruptedException {
        answer = takeMenuIntAnswer(afterBattleMenuDraw());
        switch (answer) {
            case 1:
                showGraveyardNav(party1, party2, battle);
                break;
            case 2:
                teamMenuNav(party1, party2);
                break;
            case 3: //exit game

        }
    }

    public static void showGraveyardNav(Party party1, Party party2, Battle battle) throws IOException, InterruptedException {
        Lines.printUpper();
        Lines.printGameName();
        Lines.printEmpty(2);
        System.out.println(battle.graveyard.toString());
        Lines.printEmpty(1);
        afterBattleNav(party1, party2, battle);
    }

    public static int takeMenuIntAnswer(int menuSize) {
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
                continue;
            }
        }
    }

    public static int takeSizeAnswer() {
        int choice = 0;
        while (true) {
            try {
                do {
                    if (choice < 1 || choice > MAX_SIZE_TEAM) {
                        System.out.println("\nPlease enter an integer [1 to " + MAX_SIZE_TEAM + "]:");
                    }
                    System.out.print("> ");
                    choice = scan.nextInt();
                    scan.nextLine();
                }
                while ((choice < 1 || choice > MAX_SIZE_TEAM));
                return choice;
            } catch (InputMismatchException e) {
                scan.next();
                continue;
            }
        }
    }

    public static String takeStringAnswer() {
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
                continue;
            }
        }
    }

    public static int takeCombatId(Party party) {
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
                continue;
            }
        }
    }

}
