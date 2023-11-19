package tests;
import check_sort.BeerComparator;
import Components.Beer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BeerComparatorTest {

    @Test
    void testBeerComparator() {
        Beer beer1 = new Beer("IPA", "India Pale Ale", false, "Example Brewery", Collections.emptyList(), Collections.emptyList());
        Beer beer2 = new Beer("Stout", "Dark", true, "Another Brewery", Collections.emptyList(), Collections.emptyList());
        Beer beer3 = new Beer("Pilsner", "Light", true, "Yet Another Brewery", Collections.emptyList(), Collections.emptyList());

        List<Beer> unsortedBeers = Arrays.asList(beer1, beer2, beer3);

        Collections.sort(unsortedBeers, new BeerComparator("name"));

        List<Beer> expectedBeers = Arrays.asList(beer1, beer3, beer2);

        assertEquals(expectedBeers, unsortedBeers);
    }
}
