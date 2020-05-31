package pb.spring.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pb.spring.web.model.BeerDto;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("api/v1/beer/")
@RestController
public class BeerController {
    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID id) {

        //todo real impl
        return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@Valid @RequestBody BeerDto beerDto) {

        //todo
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@Valid @RequestBody BeerDto beerDto, @PathVariable("beerId") UUID id) {
        //todo real impl
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
