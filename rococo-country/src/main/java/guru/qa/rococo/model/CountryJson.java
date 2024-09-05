package guru.qa.rococo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import guru.qa.rococo.data.CountryEntity;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CountryJson {

    @JsonProperty("id")
    UUID id;
    @JsonProperty("name")
    String name;

    public static @Nullable CountryJson fromEntity(@Nullable CountryEntity entity) {
        CountryJson countryJson = new CountryJson();
        countryJson.setId(entity.getId());
        countryJson.setName(entity.getName());
        return countryJson;
    }

    public @Nonnull CountryEntity toEntity() {
        CountryEntity entity = new CountryEntity();
        entity.setName(name);
        return entity;
    }
}
