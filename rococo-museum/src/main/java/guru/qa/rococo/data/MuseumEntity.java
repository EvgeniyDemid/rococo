package guru.qa.rococo.data;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "museum")
public class MuseumEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
  private UUID id;

  @Column(nullable = false, unique = true)
  private String title;

  @Column
  private String description;

  @Column
  private String city;

  @Column(columnDefinition = "bytea")
  private byte[] photo;

  @Column(name = "country_id", nullable = false)
  private UUID countryId;
}
