package pb.spring.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pb.spring.domain.Beer;
import pb.spring.repositories.BeerRepository;

import java.math.BigDecimal;

//@Component
public class BeerLoader implements CommandLineRunner {
    private final BeerRepository beerRepository;
    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234200019";
    public static final String BEER_3_UPC = "0083783375213";

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws  Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (beerRepository.count() == 0) {
            beerRepository.save(Beer.builder()
                    .beerName("Pilsner Urquell")
                    .beerStyle("Pilsner")
                    .quantityToBrew(200)
                    .upc(BEER_1_UPC)
                    .price(new BigDecimal("10.23"))
                    .minOnHand(12)
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Warka")
                    .beerStyle("Lager")
                    .quantityToBrew(300)
                    .upc(BEER_2_UPC)
                    .price(new BigDecimal("1.99"))
                    .minOnHand(40)
                    .build());
            beerRepository.save(Beer.builder()
                    .beerName("good beer")
                    .beerStyle("stout")
                    .quantityToBrew(500)
                    .upc(BEER_3_UPC)
                    .price(new BigDecimal("1.50"))
                    .minOnHand(12)
                    .build());
        }
    }
}
