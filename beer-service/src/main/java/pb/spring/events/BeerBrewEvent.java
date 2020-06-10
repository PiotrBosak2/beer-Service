package pb.spring.events;

import pb.spring.web.model.BeerDto;

public class BeerBrewEvent extends BeerEvent {
    public BeerBrewEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
