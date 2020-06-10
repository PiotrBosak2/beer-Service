package pb.spring.services.brewing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import pb.spring.config.JmsConfig;
import pb.spring.events.BeerBrewEvent;
import pb.spring.events.NewInventoryEvent;
import pb.spring.repositories.BeerRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {
    private final BeerRepository repository;
    private final JmsTemplate template;

    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void listen(BeerBrewEvent event) {
        var dto = event.getBeerDto();
        var beer = repository.getOne(dto.getId());
        dto.setQuantityOnHand(beer.getQuantityToBrew());
        var newInventoryEvent = new NewInventoryEvent(dto);
        log.debug("Brewed Beer");
        template.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
    }

}
