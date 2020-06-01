package pb.spring.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;
import pb.spring.bootstrap.BeerLoader;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
@ActiveProfiles("snake")
@JsonTest
class BeerDtoTest {
    @Autowired
    private ObjectMapper mapper;

    private  BeerDto getDto(){
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("someName")
                .beerStyle(BeerStyle.ALE)
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .price(new BigDecimal("10.00"))
                .upc(BeerLoader.BEER_2_UPC)
                .build();
    }

    @Test
    public void serializeJson() throws JsonProcessingException {
        var dto = getDto();
        var jsonString = mapper.writeValueAsString(dto);
        System.out.println(jsonString);
    }

    @Test
    public void deserializeJson() throws JsonProcessingException {
        var jsonString = "{\"id\":null,\"version\":null,\"createdDate\":null,\"lastModifiedDate\":null,\"beerName\":\"someName\",\"beerStyle\":\"ALE\",\"upc\":432432432142,\"price\":10.00,\"quantityOnHand\":null}";
        BeerDto dto = mapper.readValue(jsonString,BeerDto.class);
        System.out.println(dto.getBeerName());
    }
}