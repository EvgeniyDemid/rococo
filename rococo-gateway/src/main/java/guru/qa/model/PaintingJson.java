package guru.qa.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
public class PaintingJson {

    @JsonProperty("id")
    UUID id;
    @JsonProperty("title")
    String title;
    @JsonProperty("description")
    String description;
    @JsonProperty("content")
    String content;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("museum")
    MuseumJson museum;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("artist")
    ArtistJson artist;
}
