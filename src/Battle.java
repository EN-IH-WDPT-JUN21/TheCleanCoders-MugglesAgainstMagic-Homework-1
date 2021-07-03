import java.util.List;

public class Battle {

    // Properties

    private int roundNumber;
    private Graveyard graveyard;
    private Party party1;
    private Party party2;
    Object winner = new Object();

    // Constructor

    public Battle(Party party1, Party party2) {
        setRoundNumber(1);
        setParty1(party1);
        setParty2(party2);
        this.graveyard = new Graveyard();
    }

    // Methods

    // Course of the fight
    public void battle(int combatantId1, int combatantId2) {
        //Selected combatants
        Character combatant1 = party1.getAliveCharacters().get(combatantId1);
        Character combatant2 = party2.getAliveCharacters().get(combatantId2);


        System.out.println(combatant1.getName().toUpperCase() + " vs. " + combatant2.getName().toUpperCase());
        System.out.println();
        System.out.println("3.. 2.. 1.. FIGHT!!!!!!!");
        System.out.println();

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


            // The duel could end in a tie
            if(combatant1.getHp() <= 0 && combatant2.getHp() <= 0) {
                loseDuel(party1, combatant1, combatantId1);
                loseDuel(party2, combatant2, combatantId2);

                // Combatant1 lose the fight
            } else if(combatant1.getHp() <= 0){
                winDuel(party2, combatant2, combatantId2);
                loseDuel(party1, combatant1, combatantId1);

                // Combatant2 lose the fight
            } else if(combatant2.getHp() <= 0) {
                winDuel(party1, combatant1, combatantId1);
                loseDuel(party2, combatant2, combatantId2);

            }

            setRoundNumber(getRoundNumber() + 1);
        }

        if(combatant1.isAlive() || combatant2.isAlive()) {
            System.out.println(winner);
        } else {
            System.out.println("None of the combatants survived...");
        }

        System.out.println();

        System.out.println(graveyard.toString());

        setRoundNumber(1);

    }

    // What happens when a combatant loses a duel
    public void loseDuel(Party party, Character combatant, int combatantId){
        combatant.setAlive(false);

        // Add the loser to the corresponding graveyard list
        if(party == party1) {
            graveyard.addCharacter(party.getAliveCharacters().get(combatantId), 1);
        } else if(party == party2){
            graveyard.addCharacter(party.getAliveCharacters().get(combatantId), 2);
        }

        // Remove the character from the aliveCharacters list
        toGraveyard(party.getAliveCharacters(), combatantId);
    }

    // What happens when a combatant wins a duel
    public void winDuel(Party party, Character combatant, int combatantId){
        winner = combatant;

        // Save the new stats of the winner
        party.updateAliveCharacters(combatantId, combatant);

        System.out.println("THE WINNER IS " + combatant.getName().toUpperCase());
    }

    // Remove a character from the aliveCharacters list
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

    public Object getWinner() {
        return this.winner;
    }
}