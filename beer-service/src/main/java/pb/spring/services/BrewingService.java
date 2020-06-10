package pb.spring.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pb.spring.config.JmsConfig;
import pb.spring.domain.Beer;
import pb.spring.events.BeerBrewEvent;
import pb.spring.repositories.BeerRepository;
import pb.spring.services.inventory.BeerInventoryService;
import pb.spring.web.mapper.BeerMapper;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {
    private final BeerRepository repository;
    private final BeerInventoryService service;
    private final JmsTemplate template;
    private final BeerMapper mapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory() {
        List<Beer> beers = repository.findAll();

        beers.forEach(beer -> {
            var inventoryOnHand = service.getOnHandInventory(beer.getId());
            log.debug("Min Onhand is" + beer.getMinOnHand());
            log.debug("Inventory is" + inventoryOnHand);
            if (beer.getMinOnHand() >= inventoryOnHand)
                template.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BeerBrewEvent(mapper.beerToBeerDto(beer)));
        });
    }
}
