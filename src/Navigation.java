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
    final static int MAX_SIZE_TEAM = 10;

    public static int MainMenuDraw() {
        System.out.println("Welcome to our game 'Muggle against Magic'\n");
        System.out.println("1 : Play new game");
        System.out.println("2 : Exit");
        //Return size of menu
        return 2;
    }

    public static boolean Navigate(Party party1, Party party2) throws IOException {
        answer = TakeMenuIntAnswer(MainMenuDraw());
        switch (answer) {
            case 1: //Create new team
                TeamMenuNav(party1, party2);
                break;
            case 2: //Exit game
                return false;
            default:
                return true;
        }
        return true;
    }

    public static int TeamMenuDraw() {
        System.out.println("Team Creator\n");
        System.out.println("1 : Create new teams");
        System.out.println("2 : Import teams from CSV files");
        //Return size of menu
        return 2;
    }

    public static boolean TeamMenuNav(Party party1, Party party2) throws IOException {
        answer = TakeMenuIntAnswer(TeamMenuDraw());
        switch (answer) {
            case 1: //Create new team
                CreateNewTeamNav(party1, party2);
                break;
            case 2: //Import team from a csv file
                BatlleNav(ImportTeamFileMenu(), ImportOpponentTeamFileMenu());
                //TODO: What else?
                break;
        }
        return false;
        //break;
    }

    public static int RandomQMenuDraw() {
        System.out.println("Generate random characters?");
        System.out.println("1 : yes");
        System.out.println("2 : no");
        //Return size of menu
        return 2;
    }

    public static void CreateNewTeamNav(Party party1, Party party2) throws IOException {
        GenerateNewTeamNav(party1, party2);
        answer = TakeMenuIntAnswer(RandomQMenuDraw());
        switch (answer) {
            case 1: //Generate random characters
                GenerateNewCharRandomNav(party1, party2);
                ExportCharactersToFileNav(party1, party2);
                BatlleNav(party1, party2);
                break;
            case 2: //Generate manual characters
                GenerateNewCharManualNav(party1, listParty1);
                GenerateNewCharManualNav(party2, listParty2);
                ExportCharactersToFileNav(party1, party2);
                BatlleNav(party1, party2);
                break;
        }
    }

    public static Party ImportTeamFileMenu() throws FileNotFoundException {
        System.out.println("Please enter the path of the csv file for your team: ");
        String path = TakeStringAnswer();
        Party party = ImportExport.readPartyFromFile(path);

        return party;
    }

    public static Party ImportOpponentTeamFileMenu() throws FileNotFoundException {
        System.out.println("Please enter the path of the csv file for opponent team: ");
        String path = TakeStringAnswer();
        Party party = ImportExport.readPartyFromFile(path);

        return party;
    }

    public static String TeamNameQDraw(int i) {
        System.out.println("Please enter name of the " + i + " team:");
        return TakeStringAnswer();
    }

    public static int TeamSizeQDraw(int i) {
        System.out.println("Please enter size of the " + i + " team:");
        return TakeSizeAnswer();
    }

    public static void GenerateNewTeamNav(Party party1, Party party2) {
        party1.setName(TeamNameQDraw(1));
        party1.setPartySize(TeamSizeQDraw(1));
        party2.setName(TeamNameQDraw(2));
        party2.setPartySize(TeamSizeQDraw(2));
    }

    public static void GenerateNewCharRandomNav(Party party1, Party party2) throws FileNotFoundException {
        party1.setWarParty(party1.generateRandomParty(party1.getPartySize()));
        party1.setAliveCharacters(party1.getWarParty());
        party2.setWarParty(party2.generateRandomParty(party2.getPartySize()));
        party2.setAliveCharacters(party2.getWarParty());

    }

    public static int GenerateNewCharManualMenuDraw() {
        System.out.println("Choose type of the new character\n");
        System.out.println("1 : Muggle");
        System.out.println("2 : Wizard");
        //Return size of menu
        return 2;
    }

    public static void GenerateNewCharManualNav(Party party, List<Character> list) throws FileNotFoundException {
        list.clear();
        System.out.println("###################################################");
        System.out.println("Adding new characters for " + party.getName());
        System.out.println("###################################################");
        for (int i = 0; i < party.getPartySize(); i++) {
            answer = TakeMenuIntAnswer(GenerateNewCharManualMenuDraw());
            System.out.println("Enter new character's name for " + party.getName() + "\n");
            switch (answer) {
                case 1:
                    list.add(new Warrior(CharacterNameDraw("Muggle")));
                    break;
                case 2:
                    list.add(new Wizard(CharacterNameDraw("Wizard")));
                    break;
            }
        }
        party.setWarParty(list);
        party.setAliveCharacters(list);
    }

    public static String CharacterNameDraw(String name) {
        System.out.println("Please enter name of the new " + name);
        return TakeStringAnswer();
    }

    public static int ExportCharactersToFileMenuDraw() {
        System.out.println("Would you like to save created teams to CSV file?\n");
        System.out.println("1 : Yes");
        System.out.println("2 : No");
        //Return size of menu
        return 2;
    }

    public static void ExportCharactersToFileNav(Party party1, Party party2) throws IOException {
        answer = TakeMenuIntAnswer(ExportCharactersToFileMenuDraw());
        switch (answer) {
            case 1:
                ImportExport.writePartyToFile(party1);
                ImportExport.writePartyToFile(party2);
                BatlleNav(party1, party2);
                break;
            case 2:
                BatlleNav(party1, party2);
                break;
        }
    }

    public static void BatlleNav(Party party1, Party party2) throws IOException {
        Battle battle1 = new Battle(party1, party2);
        //System.out.println(party1);
        //System.out.println(party2);

        while (party1.getAliveCharacters().size() > 0 && party2.getAliveCharacters().size() > 0) {
            CharForDuelDraw(party1, party2, battle1);
        }
        AfterBattleNav(party1, party2, battle1);
    }

    public static void CharForDuelDraw(Party party1, Party party2, Battle battle) {
        System.out.println("Characters of " + party1.getName() + '\n' + party1.aliveMembersString() + '\n');
        System.out.println("Characters of " + party2.getName() + '\n' + party2.aliveMembersString() + '\n');
        System.out.println("ROUND " + battle.getRoundNumber());
        System.out.println("Pick character of the " + party1.getName() + " for the next duel");
        comb1 = TakeCombatId(party1);
        System.out.println("Pick character of the " + party2.getName() + " for the next duel");
        comb2 = TakeCombatId(party2);
        battle.battle(comb1, comb2);

    }

    public static int AfterBattleMenuDraw() {
        System.out.println("1 : Show graveyard");
        System.out.println("2 : Play new game");
        System.out.println("3 : Exit");
        //Return size of menu
        return 3;
    }

    public static void AfterBattleNav(Party party1, Party party2, Battle battle) throws IOException {
        answer = TakeMenuIntAnswer(AfterBattleMenuDraw());
        switch (answer) {
            case 1:
                ShowGraveyardNav(party1, party2, battle);
                break;
            case 2:
                TeamMenuNav(party1, party2);
                break;
            case 3: //exit game

        }
    }

    public static void ShowGraveyardNav(Party party1, Party party2, Battle battle) throws IOException {
        System.out.println(battle.graveyard.toString());
        AfterBattleNav(party1, party2, battle);

    }

    public static int TakeMenuIntAnswer(int menuSize) {
        int choice = 0;
        while (true) {
            try {
                do {
                    if (choice < 1 || choice > menuSize) {
                        System.out.println("Please enter an integer [1 to " + menuSize + "]:");
                    }
                    System.out.print(">");
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

    public static int TakeSizeAnswer() {
        int choice = 0;
        while (true) {
            try {
                do {
                    if (choice < 1 || choice > MAX_SIZE_TEAM) {
                        System.out.println("Please enter an integer [1 to " + MAX_SIZE_TEAM + "]:");
                    }
                    System.out.print(">");
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

    public static String TakeStringAnswer() {
        Scanner scan = new Scanner(System.in);
        String answer = "";
        while (true) {
            try {
                do {
                    if (answer.isBlank()) {
                        System.out.println("String couldn't be empty");
                    }
                    System.out.print(">");
                    answer = scan.nextLine();
                }
                while (answer.isBlank());
                return answer;
            } catch (InputMismatchException e) {
                System.out.println("Please enter an String: ");
                scan.next();
                continue;
            }
        }
    }

    public static int TakeCombatId(Party party) {
        int choice = 0;
        while (true) {
            try {
                do {
                    if (choice < 0 || choice > party.getPartySize()) {
                        System.out.println("Please enter an integer [1 to " + party.getAliveCharacters().size() + "]:");
                    }
                    System.out.print(">");
                    choice = scan.nextInt() - 1;
                    scan.nextLine();
                }
                while ((choice < 0 || choice > party.getAliveCharacters().size()));
                return choice;
            } catch (InputMismatchException e) {
                scan.next();
                continue;
            }
        }
    }

}
