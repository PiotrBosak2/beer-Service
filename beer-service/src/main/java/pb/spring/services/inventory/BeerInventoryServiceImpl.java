package pb.spring.services.inventory;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pb.spring.services.inventory.model.BeerInventoryDto;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "pb.spring", ignoreUnknownFields = false)
public class BeerInventoryServiceImpl implements BeerInventoryService {
    private final String INVENTORY_PATH = "api/v1/beer/{beerId}/inventory";
    private final RestTemplate restTemplate;
    private String beerInventoryServiceHost;

    public void setBeerInventoryServiceHost(String beerInventoryServiceHost) {
        this.beerInventoryServiceHost = beerInventoryServiceHost;
    }

    public BeerInventoryServiceImpl(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public Integer getOnHandInventory(UUID beerId) {
        var responseEntity = restTemplate
                .exchange(beerInventoryServiceHost + INVENTORY_PATH, HttpMethod.GET, null
                        , new ParameterizedTypeReference<List<BeerInventoryDto>>() {
                        }, beerId);
        var onHand = Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();
        return onHand;
    }
}
