import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Navigation {
    static int answer;
    static Name randomNameDatabase;

    public static void goToMainMenu() throws IOException, InterruptedException {
        answer = Answer.takeMenuOption(Printer.printMainMenu());
        switch (answer) {
            case 1: //Create new teams
                goToTeamMenu();
                break;
            case 2: //Exit game
                System.exit(0);
        }
    }

    public static void goToTeamMenu() throws IOException, InterruptedException {
        answer = Answer.takeMenuOption(Printer.PrintTeamMenu());
        switch (answer) {
            case 1: //Create new teams
                goToCreateNewTeamsManuallyMenu();
                break;
            case 2: //Import teams from a csv file
                goToImportTeamsFromFileMenu();
                break;
        }
    }

    public static void goToCreateNewTeamsManuallyMenu() throws IOException, InterruptedException {
        Party party1 = new Party();
        Party party2 = new Party();
        goToGenerateNewTeamsMenu(party1, party2);
        answer = Answer.takeMenuOption(Printer.PrintCreateNewTeamsManuallyMenu());
        switch (answer) {
            case 1: //Generate random characters
                goToGenerateRandomCharactersMenu(party1, party2);
                goToExportTeamsToFileMenu(party1, party2);
                goToBattleMenu(party1, party2);
                break;
            case 2: //Generate characters manually
                goToGenerateCharactersManuallyMenu(party1);
                goToGenerateCharactersManuallyMenu(party2);
                goToExportTeamsToFileMenu(party1, party2);
                goToBattleMenu(party1, party2);
                break;
        }
    }

    public static void goToImportTeamsFromFileMenu() throws IOException, InterruptedException {
        Party party1 = null;
        Party party2 = null;
        while (Objects.isNull(party1)) {
            party1 = goToImportOneTeamFromFileMenu(1);
        }
        while (Objects.isNull(party2)) {
            party2 = goToImportOneTeamFromFileMenu(2);
        }
        goToBattleMenu(party1, party2);
    }

    public static Party goToImportOneTeamFromFileMenu(int teamNumber) throws IOException, InterruptedException {
        String path = Printer.PrintImportOneTeamFromFileMenu(teamNumber);
        File file = new File(path);
        while (!file.exists()) {
            answer = Answer.takeMenuOption(Printer.PrintFileDoesNotExistMenu());
            switch (answer) {
                case 1: // Try again (goes back to importTeamsFromFileMenu() method)
                    return null;
                case 2: // Return to the main menu
                    goToMainMenu();
                    break;
            }
        }
        return ImportExport.readPartyFromFile(path);
    }

    public static void goToGenerateNewTeamsMenu(Party party1, Party party2) {
        party1.setName(Printer.PrintTeamNameMenu(1));
        party1.setPartySize(Printer.PrintTeamSizeMenu(1));
        party2.setName(Printer.PrintTeamNameMenu(2));
        party2.setPartySize(Printer.PrintTeamSizeMenu(2));
    }

    public static void goToGenerateRandomCharactersMenu(Party party1, Party party2) throws FileNotFoundException {
        if (Objects.isNull(randomNameDatabase)) {
            randomNameDatabase = new Name();
        }
        party1.setAliveCharacters(party1.generateRandomCharacterList(party1.getPartySize(), randomNameDatabase));
        party2.setAliveCharacters(party2.generateRandomCharacterList(party2.getPartySize(), randomNameDatabase));

    }

    public static void goToGenerateCharactersManuallyMenu(Party party) {
        Printer.PrintGenerateCharactersManuallyMenu(party);
        List<Character> members = party.getAliveCharacters();
        members.clear();
        for (int i = 0; i < party.getPartySize(); i++) {
            answer = Answer.takeMenuOption(Printer.PrintGenerateNewCharactersManuallyMenu(party));
            switch (answer) {
                case 1:
                    String nameOfWarrior = Printer.PrintCharacterNameMenu("Muggle", party);
                    Warrior warrior = new Warrior(nameOfWarrior);
                    warrior.addJrToNameIfNeeded(members);
                    members.add(warrior);
                    break;
                case 2:
                    String nameOfWizard = Printer.PrintCharacterNameMenu("Wizard", party);
                    Wizard wizard = new Wizard(nameOfWizard);
                    wizard.addJrToNameIfNeeded(members);
                    members.add(wizard);
                    break;
            }
        }
        party.setAliveCharacters(members);
    }

    public static void goToExportTeamsToFileMenu(Party party1, Party party2) throws IOException, InterruptedException {
        answer = Answer.takeMenuOption(Printer.PrintExportTeamsToFileMenu());
        switch (answer) {
            case 1:
                ImportExport.writePartyToFile(party1);
                ImportExport.writePartyToFile(party2);
                goToBattleMenu(party1, party2);
                break;
            case 2:
                goToBattleMenu(party1, party2);
                break;
        }
    }

    public static void goToBattleMenu(Party party1, Party party2) throws IOException, InterruptedException {
        Battle battle = new Battle(party1, party2);

        while (party1.getAliveCharacters().size() > 0 && party2.getAliveCharacters().size() > 0) {
            Printer.PrintCharacterForDuelMenu(party1, battle);
            int combatant1 = Answer.takeCombatantId(party1);

            Printer.PrintCharacterForDuelMenu(party2, battle);
            int combatant2 = Answer.takeCombatantId(party2);

            battle.duel(combatant1, combatant2);

            goToAfterDuelMenu();
        }

        goToWinningTeamMenu(party1, party2, battle);
    }

    public static void goToAfterDuelMenu() {
        answer = Answer.takeMenuOption(Printer.PrintAfterDuelMenu());
    }

    public static void goToWinningTeamMenu(Party party1, Party party2, Battle battle) throws IOException, InterruptedException {
        Printer.PrintWinningTeamMenu(party1, party2);
        goToAfterBattleMenu(party1, party1, battle);
    }

    public static void goToAfterBattleMenu(Party party1, Party party2, Battle battle) throws IOException, InterruptedException {
        answer = Answer.takeMenuOption(Printer.PrintAfterBattleMenu());
        switch (answer) {
            case 1: // Show graveyard
                goToShowGraveyardMenu(party1, party2, battle);
                break;
            case 2: // Play new game
                goToTeamMenu();
                break;
            case 3: //Exit game
                System.exit(0);
        }
    }

    public static void goToShowGraveyardMenu(Party party1, Party party2, Battle battle) throws IOException, InterruptedException {
        Printer.PrintShowGraveyardMenu(battle);
        goToAfterBattleMenu(party1, party2, battle);
    }
}