package Menu_Management;

import Battle_Features.Battle;
import Character_Settings.Party;

public class Printer {
    public static void printEmptyLines(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println();
        }
    }

    public static void printUpperLine() {
        System.out.println("================================================================");
    }

    public static void printGameName() {
        System.out.println("MUGGLES AGAINST MAGIC\nby TheCleanCoders");
    }

    // Non-void methods in this class either return calls to methods in Menu_Management.Answer class OR get called as arguments by Menu_Management.Answer.takeMenuOption() in which case they return numbers of options in the menu they print
    
    public static int printMainMenu() {
        printUpperLine();
        printEmptyLines(1);
        System.out.println("Welcome to our game MUGGLES AGAINST MAGIC");
        printEmptyLines(1);
        System.out.println("      Created by TheCleanCoders");
        printEmptyLines(2);
        System.out.println("For the best experience resize your terminal so it fits 15 lines");
        printEmptyLines(1);
        System.out.println("Main.Main menu");
        printEmptyLines(1);
        System.out.println("1 : Play new game");
        System.out.println("2 : Exit");
        //Return size of menu
        return 2;
    }

    public static int PrintTeamMenu() {
        printUpperLine();
        printGameName();
        printEmptyLines(6);
        System.out.println("Team Creator\n");
        System.out.println("1 : Create new teams");
        System.out.println("2 : Import teams from CSV files");
        //Return size of menu
        return 2;
    }

    public static int PrintCreateNewTeamsManuallyMenu() {
        printUpperLine();
        printGameName();
        printEmptyLines(6);
        System.out.println("Generate random characters?\n");
        System.out.println("1 : Yes");
        System.out.println("2 : No");
        //Return size of menu
        return 2;
    }

    public static String PrintImportOneTeamFromFileMenu(int teamNumber) {
        printUpperLine();
        printGameName();
        printEmptyLines(8);
        System.out.println("\nPlease enter the path of the CSV file for " + teamNumber + " team: ");
        return Answer.takeString();
    }

    public static int PrintFileDoesNotExistMenu() {
        printUpperLine();
        printGameName();
        printEmptyLines(6);
        System.out.println("Specified file does not exist.\n");
        System.out.println("1 : Try again");
        System.out.println("2 : Return to the main menu");
        // Return size of menu
        return 2;
    }

    public static String PrintTeamNameMenu(int i) {
        printUpperLine();
        printGameName();
        printEmptyLines(8);
        System.out.println("\nPlease enter name of the " + i + " team:");
        return Answer.takeString();
    }

    public static int PrintTeamSizeMenu(int i) {
        printUpperLine();
        printGameName();
        printEmptyLines(8);
        System.out.println("\nPlease enter size of the " + i + " team:");
        return Answer.takeTeamSize();
    }

    public static void PrintGenerateCharactersManuallyMenu(Party party) {
        printUpperLine();
        printGameName();
        printEmptyLines(4);
        System.out.println("Adding new characters for " + party.getName());
        printEmptyLines(1);
    }

    public static int PrintGenerateNewCharactersManuallyMenu(Party party) {
        printUpperLine();
        printGameName();
        printEmptyLines(6);
        System.out.println("Choose type of the new character in team " + party.getName() + "\n");
        System.out.println("1 : Muggle");
        System.out.println("2 : Character_Settings.Wizard");
        //Return size of menu
        return 2;
    }

    public static String PrintCharacterNameMenu(String type, Party party) {
        printUpperLine();
        printGameName();
        printEmptyLines(8);
        System.out.println("\nPlease enter name of the new " + type + " in team " + party.getName());
        return Answer.takeString();
    }

    public static int PrintExportTeamsToFileMenu() {
        printUpperLine();
        printGameName();
        printEmptyLines(6);
        System.out.println("Would you like to save created teams to CSV file?\n");
        System.out.println("1 : Yes");
        System.out.println("2 : No");
        //Return size of menu
        return 2;
    }

    public static void PrintCharacterForDuelMenu(Party party, Battle battle){
        printUpperLine();
        printGameName();
        printEmptyLines(1);
        System.out.println("Characters of " + party.getName() + '\n' + party.aliveMembersString());
        printEmptyLines(7 - party.getAliveCharacters().size());
        System.out.println("ROUND " + battle.getRoundNumber());
        System.out.println("Pick character of the " + party.getName() + " for the next duel");
    }

    public static int PrintAfterDuelMenu() {
        System.out.println("1 : Continue");
        //Return size of menu
        return 1;
    }

    public static void PrintWinningTeamMenu(Party party1, Party party2) {
        String winner = "";

        printUpperLine();
        printGameName();
        printEmptyLines(5);

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
    }

    public static int PrintAfterBattleMenu() {
        System.out.println("1 : Show graveyard");
        System.out.println("2 : Play new game");
        System.out.println("3 : Exit");
        //Return size of menu
        return 3;
    }

    public static void PrintShowGraveyardMenu(Battle battle) {
        printUpperLine();
        printGameName();
        printEmptyLines(2);
        System.out.println(battle.graveyard.toString());
        printEmptyLines(1);
    }
}
