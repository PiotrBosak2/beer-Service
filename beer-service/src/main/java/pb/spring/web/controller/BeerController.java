package pb.spring.web.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@Valid @RequestBody BeerDto beerDto) {
        service.saveNewBeer(beerDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@Valid @RequestBody BeerDto beerDto, @PathVariable("beerId") UUID id) {
        return new ResponseEntity<>(service.updateBeer(id, beerDto), HttpStatus.NO_CONTENT);
    }
}



