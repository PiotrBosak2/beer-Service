package pb.spring.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import pb.spring.web.jackson.OffsetDateTimeDeSerializer;
import pb.spring.web.jackson.OffsetDateTimeSerializer;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto implements Serializable {
    
    private static final long serialVersionUID = 5597643332808209546L;

    @JsonProperty("beerId")//overrides snake_casing
    @Null
    private UUID id;
    @Null
    private Integer version;
    @Null
    @JsonFormat(pattern = "yyyy--MM--dd", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime createdDate;
    @Null
    @JsonSerialize(using = OffsetDateTimeSerializer.class)
    @JsonDeserialize(using = OffsetDateTimeDeSerializer.class)
    private OffsetDateTime lastModifiedDate;

    @NotBlank
    @Size(min = 2, max = 100)
    private String beerName;
    @NotNull
    private BeerStyle beerStyle;
    @NotNull
    private String upc;

    @NotNull
    @PositiveOrZero
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal price;

    private Integer quantityOnHand;

}
