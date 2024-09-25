package guru.qa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
public class CountryJson {

    @JsonProperty("id")
    UUID id;
    @JsonProperty("name")
    String name;
}
