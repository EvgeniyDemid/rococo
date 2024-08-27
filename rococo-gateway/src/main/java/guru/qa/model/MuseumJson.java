package guru.qa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
public class MuseumJson {

    @JsonProperty("id")
    UUID id;
    @JsonProperty("title")
    String title;
    @JsonProperty("description")
    String description;
    @JsonProperty("photo")
    String photo;
    @JsonProperty("geo")
    GeoJson geo;

}
