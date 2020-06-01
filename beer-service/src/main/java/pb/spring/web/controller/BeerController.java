package pb.spring.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pb.spring.services.BeerService;
import pb.spring.web.mapper.BeerMapper;
import pb.spring.web.model.BeerDto;

import javax.validation.Valid;
import java.util.UUID;


@RequestMapping("api/v1/beer/")
@RestController

public class BeerController {

    private final BeerMapper mapper;
    private final BeerService service;

    public BeerController(BeerMapper mapper, BeerService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID id) {
        return new ResponseEntity<>(mapper.beerToBeerDto(service.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@Valid @RequestBody BeerDto beerDto) {
        service.save(mapper.beerDtoToBeer(beerDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@Valid @RequestBody BeerDto beerDto, @PathVariable("beerId") UUID id) {
        var beer = service.findById(id);
        if (beer != null) {
            beer.setBeerName(beerDto.getBeerName());
            beer.setBeerStyle(beerDto.getBeerStyle().name());
            beer.setPrice(beerDto.getPrice());
            beer.setUpc(beerDto.getUpc());
            service.save(beer);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}



