package Components;

import java.util.Objects;

public class BeerChar {
    private int turnover;
    private int transparency;
    private boolean filtered;
    private int calories;
    private int dispensingVolume;
    private String dispensingMaterial;

    public BeerChar() {
    }

    public BeerChar(int turnover, int transparency, boolean filtered, int calories,
                    int dispensingVolume, String dispensingMaterial) {
        this.turnover = turnover;
        this.transparency = transparency;
        this.filtered = filtered;
        this.calories = calories;
        this.dispensingVolume = dispensingVolume;
        this.dispensingMaterial = dispensingMaterial;
    }

    public int getTurnover() {
        return turnover;
    }

    public void setTurnover(int turnover) {
        this.turnover = turnover;
    }

    public int getTransparency() {
        return transparency;
    }

    public void setTransparency(int transparency) {
        this.transparency = transparency;
    }

    public boolean isFiltered() {
        return filtered;
    }

    public void setFiltered(boolean filtered) {
        this.filtered = filtered;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getDispensingVolume() {
        return dispensingVolume;
    }

    public void setDispensingVolume(int dispensingVolume) {
        this.dispensingVolume = dispensingVolume;
    }

    public String getDispensingMaterial() {
        return dispensingMaterial;
    }

    public void setDispensingMaterial(String dispensingMaterial) {
        this.dispensingMaterial = dispensingMaterial;
    }

    @Override
    public String toString() {
        return "Components.BeerChar{" +
                "turnover=" + turnover +
                ", transparency=" + transparency +
                ", filtered=" + filtered +
                ", calories=" + calories +
                ", dispensingVolume=" + dispensingVolume +
                ", dispensingMaterial='" + dispensingMaterial + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BeerChar beerChar = (BeerChar) obj;
        return turnover == beerChar.turnover &&
                transparency == beerChar.transparency &&
                filtered == beerChar.filtered &&
                calories == beerChar.calories &&
                dispensingVolume == beerChar.dispensingVolume &&
                Objects.equals(dispensingMaterial, beerChar.dispensingMaterial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(turnover, transparency, filtered, calories, dispensingVolume, dispensingMaterial);
    }
}
