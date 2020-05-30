package pb.spring.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import pb.spring.domain.Beer;

import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {//extends crudRepository
}
