import java.util.List;

public class Battle {

    // Properties

    private int roundNumber;
    public Graveyard graveyard;
    private Party party1;
    private Party party2;

    // Constructor

    public Battle(Party party1, Party party2) {
        setRoundNumber(1);
        setParty1(party1);
        setParty2(party2);
        this.graveyard = new Graveyard();
    }

    // Methods

    public void battle(int combatantId1, int combatantId2) {
        //Selected combatants
        Character combatant1 = party1.getAliveCharacters().get(combatantId1);
        Character combatant2 = party2.getAliveCharacters().get(combatantId2);

        Object winner = new Object();

        System.out.println(combatant1.getName().toUpperCase() + " vs. " + combatant2.getName().toUpperCase());
        System.out.println();
        System.out.println("3.. 2.. 1.. FIGHT!!!!!!!");

        // Scene of the battle
        while(combatant1.isAlive() && combatant2.isAlive()) {
            System.out.println(" == ROUND " + getRoundNumber() + " ==");

            // Attack
            combatant1.receiveDamage(combatant2.attack());
            combatant2.receiveDamage(combatant1.attack());

            System.out.println();

            // Stats after being attacked
            System.out.println(combatant1);
            System.out.println(combatant2);

            System.out.println();

            // The combat could end in a tie
            if(combatant1.getHp() <= 0 && combatant2.getHp() <= 0) {
                winner = null;
                System.out.println("None of the combatants survived.");

                combatant1.setAlive(false);
                combatant2.setAlive(false);
                // Add both to the graveyard list
                graveyard.addCharacter(party1.getAliveCharacters().get(combatantId1), 1);
                graveyard.addCharacter(party2.getAliveCharacters().get(combatantId2), 2);
                // Eliminate both character from the alive characters list
                toGraveyard(party1.getAliveCharacters(), combatantId1);
                toGraveyard(party2.getAliveCharacters(), combatantId2);

                // Combatant1 lose the fight
            } else if(combatant1.getHp() <= 0){
                winner = combatant2;
                combatant1.setAlive(false);

                // Add the loser to the graveyard list
                graveyard.addCharacter(party1.getAliveCharacters().get(combatantId1), 1);
                // Eliminate the character from the alive characters list
                toGraveyard(party1.getAliveCharacters(), combatantId1);

                // Save the new stats of the winner
                party2.updateAliveCharacters(combatantId2, combatant2);

                System.out.println("THE WINNER IS " + combatant2.getName().toUpperCase());

                // Combatant2 lose the fight
            } else if(combatant2.getHp() <= 0) {
                winner = combatant1;
                combatant2.setAlive(false);

                // Add the loser to the graveyard list
                graveyard.addCharacter(party2.getAliveCharacters().get(combatantId2), 2);
                // Eliminate the character from the alive characters list
                toGraveyard(party2.getAliveCharacters(), combatantId2);

                // Save the new stats of the winner
                party1.updateAliveCharacters(combatantId1, combatant1);

                System.out.println("THE WINNER IS " + combatant1.getName().toUpperCase());

            }

            setRoundNumber(getRoundNumber() + 1);
        }
        System.out.println(winner);
        System.out.println();
        System.out.println(graveyard.toString());

    }

    // Remove the loser character from the alive characters list
    public List<Character> toGraveyard(List<Character> aliveCharacters, int index){
        aliveCharacters.remove(index);
        return aliveCharacters;
    }

    // Setters

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public void setParty1(Party party1) {
        this.party1 = party1;
    }

    public void setParty2(Party party2) {
        this.party2 = party2;
    }

    // Getters

    public int getRoundNumber() {
        return this.roundNumber;
    }

    public Party getParty1() {
        return this.party1;
    }

    public Party getParty2() {
        return this.party2;
    }
}