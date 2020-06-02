package pb.spring.services;

import org.springframework.stereotype.Service;
import pb.spring.domain.Beer;
import pb.spring.web.model.BeerDto;

import java.util.UUID;

@Service
public interface BeerService {

    BeerDto getById(UUID id);
    BeerDto saveNewBeer(BeerDto beerDto);
    BeerDto updateBeer(UUID beerId,BeerDto beerDto);


}
