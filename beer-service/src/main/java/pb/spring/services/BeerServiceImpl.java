package pb.spring.services;

import org.springframework.stereotype.Service;
import pb.spring.domain.Beer;
import pb.spring.exception.NotFoundException;
import pb.spring.repositories.BeerRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Service("beerService")
public class BeerServiceImpl implements BeerService {
    private final BeerRepository repository;

    public BeerServiceImpl(BeerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Beer> findAll() {
        var set = new HashSet<Beer>();

         repository.findAll().forEach(set::add);
         return set;
    }

    @Override
    public Beer findById(UUID uuid) {
        return repository.findById(uuid).orElseThrow(NotFoundException::new);
    }

    @Override
    public Beer save(Beer object) {
        return repository.save(object);
    }

    @Override
    public void delete(Beer object) {
        repository.delete(object);
    }

    @Override
    public void deleteById(UUID uuid) {
        repository.deleteById(uuid);
    }

}
