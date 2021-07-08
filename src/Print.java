public class Print {
    public static int mainMenu() {
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

    public static int teamMenu() {
        Lines.printUpper();
        Lines.printGameName();
        Lines.printEmpty(6);
        System.out.println("Team Creator\n");
        System.out.println("1 : Create new teams");
        System.out.println("2 : Import teams from CSV files");
        //Return size of menu
        return 2;
    }

    public static int randomQMenu() {
        Lines.printUpper();
        Lines.printGameName();
        Lines.printEmpty(7);
        System.out.println("Generate random characters?");
        System.out.println("1 : Yes");
        System.out.println("2 : No");
        //Return size of menu
        return 2;
    }

    public static String teamNameQMenu(int i) {
        Lines.printUpper();
        Lines.printGameName();
        Lines.printEmpty(8);
        System.out.println("\nPlease enter name of the " + i + " team:");
        return Answer.takeString();
    }

    public static int teamSizeQMenu(int i) {
        Lines.printUpper();
        Lines.printGameName();
        Lines.printEmpty(8);
        System.out.println("\nPlease enter size of the " + i + " team:");
        return Answer.takeSize();
    }

    public static int generateNewCharManualMenu() {
        System.out.println("Choose type of the new character\n");
        System.out.println("1 : Muggle");
        System.out.println("2 : Wizard");
        //Return size of menu
        return 2;
    }

    public static String characterNameMenu(String name) {
        Lines.printUpper();
        Lines.printGameName();
        Lines.printEmpty(8);
        System.out.println("\nPlease enter name of the new " + name);
        return Answer.takeString();
    }

    public static int exportCharactersToFileMenu() {
        Lines.printUpper();
        Lines.printGameName();
        Lines.printEmpty(6);
        System.out.println("Would you like to save created teams to CSV file?\n");
        System.out.println("1 : Yes");
        System.out.println("2 : No");
        //Return size of menu
        return 2;
    }

    public static int afterDuelMenu() {
        System.out.println("1 : Continue");
        //Return size of menu
        return 1;
    }

    public static void charForDuelMenu(Party party1, Party party2, Battle battle) throws InterruptedException {
        Lines.printUpper();
        Lines.printGameName();
        Lines.printEmpty(1);
        System.out.println("Characters of " + party1.getName() + '\n' + party1.aliveMembersString());
        Lines.printEmpty(7 - party1.getAliveCharacters().size());
        System.out.println("ROUND " + battle.getRoundNumber());
        System.out.println("Pick character of the " + party1.getName() + " for the next duel");
        int comb1 = Answer.takeCombatantId(party1);

        Lines.printUpper();
        Lines.printGameName();
        Lines.printEmpty(1);
        System.out.println("Characters of " + party2.getName() + '\n' + party2.aliveMembersString());
        Lines.printEmpty(7 - party2.getAliveCharacters().size());
        System.out.println("ROUND " + battle.getRoundNumber());
        System.out.println("Pick character of the " + party2.getName() + " for the next duel");
        int comb2 = Answer.takeCombatantId(party2);

        battle.battle(comb1, comb2);
    }

    public static int afterBattleMenu() {
        System.out.println("1 : Show graveyard");
        System.out.println("2 : Play new game");
        System.out.println("3 : Exit");
        //Return size of menu
        return 3;
    }
}
