package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class CountryJson {

	@JsonProperty("id")
	UUID id;
	@JsonProperty("name")
	String name;
}
