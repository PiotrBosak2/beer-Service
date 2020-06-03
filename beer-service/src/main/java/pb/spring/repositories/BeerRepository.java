package pb.spring.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pb.spring.domain.Beer;
import pb.spring.web.model.BeerStyle;

import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {//extends crudRepository

    Page<Beer> findAllByBeerName(String beerName, Pageable pageable);

    Page<Beer> findAllByBeerStyle(BeerStyle beerStyle, Pageable pageable);

    Page<Beer> findAllByBeerNameAndAndBeerStyle(String beerName, BeerStyle beerStyle, Pageable pageable);

    Beer findBeerByUpc(String upc);
}
