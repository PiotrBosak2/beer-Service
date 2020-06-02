package pb.spring.web.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import pb.spring.domain.Beer;
import pb.spring.web.model.BeerDto;
@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDto beerDto);

    BeerDto beerToBeerDto(Beer beer);

}
