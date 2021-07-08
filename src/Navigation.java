import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Navigation {
    static Scanner scan = new Scanner(System.in);
    static List<Character> listParty1 = new ArrayList<>();
    static List<Character> listParty2 = new ArrayList<>();
    static int answer;
    static int comb1;
    static int comb2;

    public static boolean navigate(Party party1, Party party2) throws IOException, InterruptedException {
        answer = Answer.takeMenuInt(Print.mainMenu());
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

    public static boolean teamMenuNav(Party party1, Party party2) throws IOException, InterruptedException {
        answer = Answer.takeMenuInt(Print.teamMenu());
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

    public static void createNewTeamNav(Party party1, Party party2) throws IOException, InterruptedException {
        generateNewTeamNav(party1, party2);
        answer = Answer.takeMenuInt(Print.randomQMenu());
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
        String path = Answer.takeString();
        File file = new File(path);
        while (!file.exists()) {
            Lines.printUpper();
            Lines.printGameName();
            Lines.printEmpty(6);
            System.out.println("Specified file does not exist.\n");
            System.out.println("1 : Try again");
            System.out.println("2 : Return to the main menu");
            answer = Answer.takeMenuInt(2);
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

    public static void generateNewTeamNav(Party party1, Party party2) {
        party1.setName(Print.teamNameQMenu(1));
        party1.setPartySize(Print.teamSizeQMenu(1));
        party2.setName(Print.teamNameQMenu(2));
        party2.setPartySize(Print.teamSizeQMenu(2));
    }

    public static void generateNewCharRandomNav(Party party1, Party party2) throws FileNotFoundException {
        party1.setWarParty(party1.generateRandomParty(party1.getPartySize()));
        party1.setAliveCharacters(party1.getWarParty());
        party2.setWarParty(party2.generateRandomParty(party2.getPartySize()));
        party2.setAliveCharacters(party2.getWarParty());

    }

    public static void generateNewCharManualNav(Party party, List<Character> list) throws FileNotFoundException {
        list.clear();
        Lines.printUpper();
        Lines.printGameName();
        Lines.printEmpty(4);
        System.out.println("Adding new characters for " + party.getName());
        Lines.printEmpty(1);
        for (int i = 0; i < party.getPartySize(); i++) {
            answer = Answer.takeMenuInt(Print.generateNewCharManualMenu());
            System.out.println("Enter new character's name for " + party.getName() + "\n");
            switch (answer) {
                case 1:
                    String nameOfWarrior = Print.characterNameMenu("Muggle");
                    Warrior warrior = new Warrior(nameOfWarrior);
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(j).getName().equals(warrior.getName())) {
                            warrior.setName(warrior.getName() + " Jr");
                        }
                    }
                    list.add(warrior);
                    break;
                case 2:
                    String nameOfWizard = Print.characterNameMenu("Wizard");
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

    public static void exportCharactersToFileNav(Party party1, Party party2) throws IOException, InterruptedException {
        answer = Answer.takeMenuInt(Print.exportCharactersToFileMenu());
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
            Print.charForDuelMenu(party1, party2, battle1);
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
        answer = Answer.takeMenuInt(Print.afterDuelMenu());
        switch (answer) {
            case 1:
                return;
        }
    }

    public static void afterBattleNav(Party party1, Party party2, Battle battle) throws IOException, InterruptedException {
        answer = Answer.takeMenuInt(Print.afterBattleMenu());
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
}