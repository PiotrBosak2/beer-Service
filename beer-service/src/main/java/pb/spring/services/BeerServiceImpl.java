package pb.spring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pb.spring.exception.NotFoundException;
import pb.spring.repositories.BeerRepository;
import pb.spring.web.mapper.BeerMapper;
import pb.spring.web.model.BeerDto;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository repository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getById(UUID id) {
        return beerMapper.beerToBeerDto(
                repository.findById(id).orElseThrow(NotFoundException::new)
        );
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(repository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        var beer = repository.findById(beerId).orElseThrow(NotFoundException::new);

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());
        return beerMapper.beerToBeerDto(repository.save(beer));
    }
}
