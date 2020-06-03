package pb.spring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pb.spring.domain.Beer;
import pb.spring.exception.NotFoundException;
import pb.spring.repositories.BeerRepository;
import pb.spring.web.mapper.BeerMapper;
import pb.spring.web.model.BeerDto;
import pb.spring.web.model.BeerPagedList;
import pb.spring.web.model.BeerStyle;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository repository;
    private final BeerMapper beerMapper;

    @Cacheable(cacheNames = "beerCache",key = "#beerId",condition = "#withInventory == false ")
    @Override
    public BeerDto getById(UUID id, Boolean withInventory) {
        if (withInventory != null && withInventory) {
            return beerMapper.beerToBeerDtoWithInventory(
                    repository.findById(id).orElseThrow(NotFoundException::new)
            );
        } else {
            return beerMapper.beerToBeerDto(
                    repository.findById(id).orElseThrow(NotFoundException::new)
            );
        }
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

    @Cacheable(cacheNames = "beerListCache", condition = "#withInventory == false ")
    @Override
    public BeerPagedList listBeers(String name, BeerStyle style, boolean withInventory, PageRequest pageRequest) {
        BeerPagedList list;
        Page<Beer> beerPage;
        if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(style))
            beerPage = repository.findAllByBeerNameAndAndBeerStyle(name, style, pageRequest);
        else if (!StringUtils.isEmpty(name))
            beerPage = repository.findAllByBeerName(name, pageRequest);
        else if (!StringUtils.isEmpty(style))
            beerPage = repository.findAllByBeerStyle(style, pageRequest);
        else beerPage = repository.findAll(pageRequest);

        if (withInventory) {
            list = new BeerPagedList(beerPage
                    .getContent()
                    .stream()
                    .map(beerMapper::beerToBeerDtoWithInventory)
                    .collect(Collectors.toList()));
        } else {
            list = new BeerPagedList(beerPage
                    .getContent()
                    .stream()
                    .map(beerMapper::beerToBeerDto)
                    .collect(Collectors.toList()));
        }
//                PageRequest.of(beerPage.getPageable().getPageNumber(),
//                        beerPage.getPageable().getPageSize());
//    }
        return list;//may not work

    }
}
