package ebgame;

public class Game {
    private String firstCountry;
    private String secondCountry;
    private int firstCountryScore;
    private int secondCountryScore;

    public Game(String firstCountry, String secondCountry, int firstCountryScore, int secondCountryScore) {
        this.firstCountry = firstCountry;
        this.secondCountry = secondCountry;
        this.firstCountryScore = firstCountryScore;
        this.secondCountryScore = secondCountryScore;
    }

    public String getWinner() {
        if (firstCountryScore == secondCountryScore) {
            return "Draw";
        } else {
            return firstCountryScore > secondCountryScore ? firstCountry : secondCountry;
        }
    }

    public int getScoreDiff() {
        return Math.abs(firstCountryScore-secondCountryScore);
    }

    public String getFirstCountry() {
        return firstCountry;
    }

    public String getSecondCountry() {
        return secondCountry;
    }

    public int getFirstCountryScore() {
        return firstCountryScore;
    }

    public int getSecondCountryScore() {
        return secondCountryScore;
    }

    @Override
    public String toString() {
        return "Game{" +
                "firstCountry='" + firstCountry + '\'' +
                ", secondCountry='" + secondCountry + '\'' +
                ", firstCountryScore=" + firstCountryScore +
                ", secondCountryScore=" + secondCountryScore +
                '}';
    }
}
