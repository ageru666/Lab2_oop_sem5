package Components;

import java.util.List;
public class Beers {
    private List<Beer> beer;

    public Beers(List<Beer> beer) {
        this.beer = beer;
    }

    public List<Beer> getBeer() {
        return beer;
    }

    public void setBeer(List<Beer> beer) {
        this.beer = beer;
    }

    @Override
    public String toString() {
        return "Components.Beers{" +
                "beer=" + beer +
                '}';
    }
}
