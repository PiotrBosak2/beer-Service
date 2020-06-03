package pb.spring.web.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pb.spring.services.BeerService;
import pb.spring.web.mapper.BeerMapper;
import pb.spring.web.model.BeerDto;
import pb.spring.web.model.BeerPagedList;
import pb.spring.web.model.BeerStyle;

import javax.validation.Valid;
import java.util.UUID;


@RequestMapping("api/v1/beer/")
@RestController
public class BeerController {
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final BeerService service;

    @GetMapping(produces = {"application/json"})
    public ResponseEntity<BeerPagedList> listBeers(
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "beerName", required = false) String beerName,
            @RequestParam(value = "beerStyle", required = false) BeerStyle beerStyle,
            @RequestParam(value = "withInventory", required = false) Boolean withInventory
    ) {
        if (pageNumber == null || pageNumber < 0)
            pageNumber = DEFAULT_PAGE_NUMBER;
        if (pageSize == null || pageSize < 1)
            pageSize = DEFAULT_PAGE_SIZE;
        var inventory = withInventory != null && withInventory;
        var beerList = service.listBeers(beerName, beerStyle, inventory, PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(beerList, HttpStatus.OK);
    }

    @Autowired
    public BeerController(BeerService service) {
        this.service = service;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID id,
                                               @RequestParam(value = "withInventory", required = false) Boolean withInventory) {
        return new ResponseEntity<>(service.getById(id, withInventory), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@Valid @RequestBody BeerDto beerDto) {
        service.saveNewBeer(beerDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/beerUpc/{beerUpc}")
    public ResponseEntity<BeerDto> getBeerByUPC(
            @PathVariable("beerUpc") String upc,
            @RequestParam(value = "withInventory", required = false) Boolean withInventory) {
        if (withInventory == null)
            withInventory = false;
        var beerDto = service.getByUpc(upc,withInventory);
        return new ResponseEntity<>(beerDto,HttpStatus.OK);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@Valid @RequestBody BeerDto beerDto, @PathVariable("beerId") UUID id) {
        return new ResponseEntity<>(service.updateBeer(id, beerDto), HttpStatus.NO_CONTENT);
    }
}



