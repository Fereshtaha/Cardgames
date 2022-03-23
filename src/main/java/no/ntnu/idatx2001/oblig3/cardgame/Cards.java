package no.ntnu.idatx2001.oblig3.cardgame;

public class Cards {

    public static Object List;
    private final String type;
    private final int value;

    public Cards(String type, int value) {
        this.type = type;
        this.value = value;
    }

    public String toString() {
        if (this.value >= 1 && this.value <= 10) {
            return this.type + this.value;
        } if (this.value == 11) {
            return this.value + "Knekt";
        } if (this.value == 12) {
            return this.value + "Dame";
        } if (this.value == 13) {
            return this.value + "Konge";
        } return "ugyldig kort";
    }
}
