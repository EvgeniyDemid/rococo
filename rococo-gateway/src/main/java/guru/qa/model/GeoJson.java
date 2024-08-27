package guru.qa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class GeoJson {
    @JsonProperty("city")
    String city;
    @JsonProperty("country")
    CountryJson country;
}
