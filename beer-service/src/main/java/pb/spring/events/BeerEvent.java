package pb.spring.events;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import pb.spring.web.model.BeerDto;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {
    private static final long serialVersionUID = -3919829091716147393L;
    private final BeerDto beerDto;


}
