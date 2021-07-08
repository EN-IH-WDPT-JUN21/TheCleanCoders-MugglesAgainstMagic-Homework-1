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

    public static boolean mainMenu(Party party1, Party party2) throws IOException, InterruptedException {
        answer = Answer.takeMenuInt(Print.mainMenu());
        switch (answer) {
            case 1: //Create new teams
                teamMenu(party1, party2);
                break;
            case 2: //Exit game
                System.exit(0);
            default:
                return true;
        }
        return true;
    }

    public static boolean teamMenu(Party party1, Party party2) throws IOException, InterruptedException {
        answer = Answer.takeMenuInt(Print.teamMenu());
        switch (answer) {
            case 1: //Create new teams
                createNewTeamsManuallyMenu(party1, party2);
                break;
            case 2: //Import teams from a csv file
                importTeamsFromFileMenu();
                break;
        }
        return false;
        //break;
    }

    public static void createNewTeamsManuallyMenu(Party party1, Party party2) throws IOException, InterruptedException {
        generateNewTeamsMenu(party1, party2);
        answer = Answer.takeMenuInt(Print.createNewTeamsManuallyMenu());
        switch (answer) {
            case 1: //Generate random characters
                generateRandomCharactersMenu(party1, party2);
                exportTeamsToFileMenu(party1, party2);
                battleMenu(party1, party2);
                break;
            case 2: //Generate characters manually
                generateCharactersManuallyMenu(party1, listParty1);
                generateCharactersManuallyMenu(party2, listParty2);
                exportTeamsToFileMenu(party1, party2);
                battleMenu(party1, party2);
                break;
        }
    }

    public static void importTeamsFromFileMenu() throws IOException, InterruptedException {
        Party party1 = null;
        Party party2 = null;
        while(party1 == null) {
            party1 = importOneTeamFromFileMenu(1);
        }
        while(party2 == null) {
            party2 = importOneTeamFromFileMenu(2);
        }
        battleMenu(party1, party2);
    }

    public static Party importOneTeamFromFileMenu(int teamNumber) throws IOException, InterruptedException {
        String path = Print.importOneTeamFromFileMenu(teamNumber);
        File file = new File(path);
        while (!file.exists()) {
            answer = Answer.takeMenuInt(Print.fileDoesNotExistMenu());
            switch (answer) {
                case 1: // Try again (goes back to importTeamsFromFileMenu() method)
                    return null;
                case 2: // Return to the main menu
                    mainMenu(new Party(), new Party());
                    break;
            }
        }
        return ImportExport.readPartyFromFile(path);
    }

    public static void generateNewTeamsMenu(Party party1, Party party2) {
        party1.setName(Print.teamNameMenu(1));
        party1.setPartySize(Print.teamSizeMenu(1));
        party2.setName(Print.teamNameMenu(2));
        party2.setPartySize(Print.teamSizeMenu(2));
    }

    public static void generateRandomCharactersMenu(Party party1, Party party2) throws FileNotFoundException {
        party1.setWarParty(party1.generateRandomParty(party1.getPartySize()));
        party1.setAliveCharacters(party1.getWarParty());
        party2.setWarParty(party2.generateRandomParty(party2.getPartySize()));
        party2.setAliveCharacters(party2.getWarParty());

    }

    public static void generateCharactersManuallyMenu(Party party, List<Character> list) throws FileNotFoundException {
        Print.generateCharactersManuallyMenu(party);
        list.clear();
        for (int i = 0; i < party.getPartySize(); i++) {
            answer = Answer.takeMenuInt(Print.generateNewCharactersManuallyMenu(party));
            switch (answer) {
                case 1:
                    String nameOfWarrior = Print.characterNameMenu("Muggle", party);
                    Warrior warrior = new Warrior(nameOfWarrior);
                    warrior.addJrToNameIfNeeded(list);
                    list.add(warrior);
                    break;
                case 2:
                    String nameOfWizard = Print.characterNameMenu("Wizard", party);
                    Wizard wizard = new Wizard(nameOfWizard);
                    wizard.addJrToNameIfNeeded(list);
                    list.add(wizard);
                    break;
            }
        }
        party.setWarParty(list);
        party.setAliveCharacters(list);
    }

    public static void exportTeamsToFileMenu(Party party1, Party party2) throws IOException, InterruptedException {
        answer = Answer.takeMenuInt(Print.exportTeamsToFileMenu());
        switch (answer) {
            case 1:
                ImportExport.writePartyToFile(party1);
                ImportExport.writePartyToFile(party2);
                battleMenu(party1, party2);
                break;
            case 2:
                battleMenu(party1, party2);
                break;
        }
    }

    public static void battleMenu(Party party1, Party party2) throws IOException, InterruptedException {
        Battle battle = new Battle(party1, party2);

        while (party1.getAliveCharacters().size() > 0 && party2.getAliveCharacters().size() > 0) {
            Print.characterForDuelMenu(party1, battle);
            int combatant1 = Answer.takeCombatantId(party1);

            Print.characterForDuelMenu(party2, battle);
            int combatant2 = Answer.takeCombatantId(party2);

            battle.battle(combatant1, combatant2);

            afterDuelMenu(party1, party2, battle);
        }

        winningTeamMenu(party1, party2, battle);
    }

    public static void afterDuelMenu(Party party1, Party party2, Battle battle) throws IOException {
        answer = Answer.takeMenuInt(Print.afterDuelMenu());
        switch (answer) {
            case 1:
                return;
        }
    }

    public static void winningTeamMenu(Party party1, Party party2, Battle battle) throws IOException, InterruptedException {
        Print.winningTeamMenu(party1, party2);
        afterBattleMenu(party1, party1, battle);
    }

    public static void afterBattleMenu(Party party1, Party party2, Battle battle) throws IOException, InterruptedException {
        answer = Answer.takeMenuInt(Print.afterBattleMenu());
        switch (answer) {
            case 1: // Show graveyard
                showGraveyardMenu(party1, party2, battle);
                break;
            case 2: // Play new game
                teamMenu(party1, party2);
                break;
            case 3: //Exit game
                System.exit(0);
        }
    }

    public static void showGraveyardMenu(Party party1, Party party2, Battle battle) throws IOException, InterruptedException {
        Print.showGraveyardMenu(battle);
        afterBattleMenu(party1, party2, battle);
    }
}