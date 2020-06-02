package pb.spring.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pb.spring.domain.Beer;
import pb.spring.web.model.BeerDto;
import pb.spring.web.model.BeerPagedList;
import pb.spring.web.model.BeerStyle;

import java.util.UUID;

@Service
public interface BeerService {

    BeerDto getById(UUID id,Boolean withInventory);
    BeerDto saveNewBeer(BeerDto beerDto);
    BeerDto updateBeer(UUID beerId,BeerDto beerDto);
    BeerPagedList listBeers(String name, BeerStyle style,boolean withInventory, PageRequest pageRequest);

}
