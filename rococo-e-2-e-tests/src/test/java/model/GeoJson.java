package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class GeoJson {

	@JsonProperty("id")
	UUID id;
    @JsonProperty("city")
    String city;
    @JsonProperty("country")
	CountryJson country;
}
