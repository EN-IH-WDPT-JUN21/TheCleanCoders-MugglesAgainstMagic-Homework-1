import java.util.ArrayList;
import java.util.List;

public class Graveyard {
    //Properties
    private List<String> graveyard1 = new ArrayList<>();
    private List<String> graveyard2 = new ArrayList<>();

    //Constructor
    public Graveyard() {
        setGraveyard1(graveyard1);
        setGraveyard2(graveyard2);
    }

    // Methods
    public void addCharacter(Character character, int team) {
        if (team == 1) {
            this.graveyard1.add(character.getName());
        } else if (team == 2) {
            this.graveyard2.add(character.getName());
        }
    }

    public String toString() {
        return "✝✝✝✝ Graveyard ✝✝✝✝" + '\n' +
                "Team 1: " + graveyard1 + '\n' +
                "Team 2: " + graveyard2 + '\n';
    }

    // Setters

    public void setGraveyard1(List<String> graveyard1) {
        this.graveyard1 = graveyard1;
    }

    public void setGraveyard2(List<String> graveyard2) {
        this.graveyard2 = graveyard2;
    }
}