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
    final static int MAX_SIZE_TEAM = 10;

    public static boolean Navigate(Party party1, Party party2) throws FileNotFoundException {
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

    public static boolean TeamMenuNav(Party party1, Party party2) throws FileNotFoundException {
        answer = TakeMenuIntAnswer(TeamMenuDraw());
        switch (answer) {
            case 1: //Create new team
                CreateNewTeamNav(party1, party2);
                break;
            case 2: //Import team from a csv file
                ImportTeamFileMenu();
                BatlleNav(party1, party2);
                //TODO: What else?
                break;
        }
        return false;
        //break;
    }

    public static void GenerateNewTeamNav(Party party1, Party party2) {
        party1.setName(TeamNameQDraw(1));
        party1.setPartySize(TeamSizeQDraw(1));
        party2.setName(TeamNameQDraw(2));
        party2.setPartySize(TeamSizeQDraw(2));
    }

    public static void CreateNewTeamNav(Party party1, Party party2) throws FileNotFoundException {
        GenerateNewTeamNav(party1, party2);
        answer = TakeMenuIntAnswer(RandomQMenuDraw());
        switch (answer) {
            case 1: //Generate random characters
                GenerateNewCharRandomNav(party1, party2);
                BatlleNav(party1, party2);
                break;
            case 2: //Generate manual characters
                GenerateNewCharManualNav(party1, party2);

                BatlleNav(party1, party2);
                break;
        }
    }

    public static void GenerateNewCharRandomNav(Party party1, Party party2) throws FileNotFoundException {
        party1.setWarParty(party1.generateRandomParty(party1.getPartySize()));
        party2.setWarParty(party2.generateRandomParty(party2.getPartySize()));
    }

    public static void GenerateNewCharManualNav(Party party1, Party party2) throws FileNotFoundException {
        listParty1.clear();
        System.out.println("###################################################");
        System.out.println("Adding new characters for " + party1.getName());
        System.out.println("###################################################");
        for (int i=0; i< party1.getPartySize(); i++) {
            answer = TakeMenuIntAnswer(GenerateNewCharManualMenuDraw());
            System.out.println("Enter new character's name for " + party1.getName() + "\n");
            switch (answer) {
                case 1:
                    listParty1.add(new Warrior(CharacterNameDraw("Muggle")));
                    break;
                case 2:
                    listParty1.add(new Wizard(CharacterNameDraw("Wizard")));
                    break;
            }
        }
        party1.setWarParty(listParty1);
        party1.setAliveCharacters(listParty1);

        listParty2.clear();
        System.out.println("###################################################");
        System.out.println("Adding new characters for " + party2.getName());
        System.out.println("###################################################");
        for (int i=0; i< party2.getPartySize(); i++) {
            answer = TakeMenuIntAnswer(GenerateNewCharManualMenuDraw());
            System.out.println("Enter new character's name for " + party2.getName() + "\n");
            switch (answer) {
                case 1:
                    listParty2.add(new Warrior(CharacterNameDraw("Muggle")));
                    break;
                case 2:
                    listParty2.add(new Wizard(CharacterNameDraw("Wizard")));
                    break;
            }
        }
        party2.setWarParty(listParty2);
        party2.setAliveCharacters(listParty2);
    }

    public static void ExportCharactersToFileNav(Party party1, Party party2) throws IOException {
        answer = TakeMenuIntAnswer(ExportCharactersToFileMenuDraw());
        switch(answer){
            case 1:
                System.out.println("Please enter the path to the csv file for " + party1.getName());
                String path1 = TakeStringAnswer();
                ImportExport.writePartyToFile(path1, listParty1);
                System.out.println("Please enter the path to the csv file for " + party2.getName());
                String path2 = TakeStringAnswer();
                ImportExport.writePartyToFile(path2, listParty2);
                BatlleNav(party1, party2);
                break;
            case 2:
                BatlleNav(party1, party2);
                break;
        }
    }

    public static int ExportCharactersToFileMenuDraw() {
        System.out.println("Would you like to save created teams to CSV file?\n");
        System.out.println("1 : Yes");
        System.out.println("2 : No");
        //Return size of menu
        return 2;
    }

    public static int GenerateNewCharManualMenuDraw(){
        System.out.println("Choose type of the new Character\n");
        System.out.println("1 : Muggle");
        System.out.println("2 : Wizard");
        //Return size of menu
        return 2;
    }

    public static void BatlleNav(Party party1, Party party2) throws FileNotFoundException {
        Battle battle1 = new Battle(party1, party2);
        System.out.println(party1);
        System.out.println(party2);
        battle1.battle(4, 3);
        battle1.battle(3, 2);
        battle1.battle(0, 0);
        AfterBattleNav(party1, party2, battle1);
    }

    public static void AfterBattleNav(Party party1, Party party2, Battle battle) throws FileNotFoundException {
        answer = TakeMenuIntAnswer(AfterBattleMenuDraw());
        switch (answer) {
            case 1:
                ShowGraveyardNav(party1, party2, battle);
                break;
            case 2:
                BatlleNav(party1, party2); //TODO: something wrong, maybe reset battle in constructor? or don't change party1, party2 during battle?
            case 3:
                TeamMenuNav(party1, party2);
                break;
            case 4: //exit game

        }
    }

    public static void ShowGraveyardNav(Party party1, Party party2, Battle battle) throws FileNotFoundException {
        System.out.println(battle.graveyard.toString());
        AfterBattleNav(party1, party2, battle);

    }

    public static int MainMenuDraw() {
        System.out.println("Welcome to our game 'Muggle against Magic'\n");
        System.out.println("1 : Play new game");
        System.out.println("2 : Exit");
        //Return size of menu
        return 2;
    }

    public static int TeamMenuDraw() {
        System.out.println("Team Creator\n");
        System.out.println("1 : Create new teams");
        System.out.println("2 : Import team from a csv file");
        //Return size of menu
        return 2;
    }

    public static int RandomQMenuDraw() {
        System.out.println("Generate random characters?");
        System.out.println("1 : yes");
        System.out.println("2 : no");
        //Return size of menu
        return 2;
    }

    public static String TeamNameQDraw(int i) {
        System.out.println("Please enter name of the " + i + " team:");
        return TakeStringAnswer();
    }

    public static String CharacterNameDraw(String name) {
        System.out.println("Please enter name of the new " + name);
        return TakeStringAnswer();
    }

    public static int TeamSizeQDraw(int i) {
        System.out.println("Please enter size of the " + i + " team:");
        return TakeSizeAnswer();
    }

    public static int AfterBattleMenuDraw() {
        System.out.println("1 : Show graveyard");
        System.out.println("2 : Start another battle");
        System.out.println("3 : Play new game");
        System.out.println("4 : Exit");
        //Return size of menu
        return 4;
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
