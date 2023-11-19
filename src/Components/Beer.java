package Components;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Beer {
    private String name;
    private String type;
    private boolean alcoholic;
    private String manufacturer;
    private List<String> ingredients = new ArrayList<>();
    private List<BeerChar> chars = new ArrayList<>();

    public Beer() {
    }

    public Beer(String name, String type, boolean alcoholic, String manufacturer,
                List<String> ingredients, List<BeerChar> chars) {
        this.name = name;
        this.type = type;
        this.alcoholic = alcoholic;
        this.manufacturer = manufacturer;

        if (ingredients != null) {
            this.ingredients = ingredients;
        }

        if (chars != null) {
            this.chars = chars;
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAlcoholic() {
        return alcoholic;
    }

    public void setAlcoholic(boolean alcoholic) {
        this.alcoholic = alcoholic;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<BeerChar> getChars() {
        return chars;
    }

    public void setChars(List<BeerChar> chars) {
        this.chars = chars;
    }

    @Override
    public String toString() {
        return "Components.Beer{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", alcoholic=" + alcoholic +
                ", manufacturer='" + manufacturer + '\'' +
                ", ingredients=" + ingredients +
                ", chars=" + chars +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Beer beer = (Beer) obj;
        return Objects.equals(name, beer.name) &&
                Objects.equals(type, beer.type) &&
                Objects.equals(alcoholic, beer.alcoholic) &&
                Objects.equals(manufacturer, beer.manufacturer) &&
                Objects.equals(ingredients, beer.ingredients) &&
                Objects.equals(chars, beer.chars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, alcoholic, manufacturer, ingredients, chars);
    }
}
