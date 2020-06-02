package pb.spring.services.inventory.model;

import lombok.Data;

import java.util.UUID;

@Data
public class BeerInventoryDto {
    UUID id;
    int quantityOnHand;
}
