package org.springframework.samples.petclinic.rest.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Editable fields of a pet type.
 */

@Schema(name = "PetTypeFields", description = "Editable fields of a pet type.")
@JsonTypeName("PetTypeFields")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-25T10:14:13.839343600+01:00[Europe/Paris]", comments = "Generator version: 7.12.0")
public class PetTypeFieldsDto {

  private String name;

  public PetTypeFieldsDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PetTypeFieldsDto(String name) {
    this.name = name;
  }

  public PetTypeFieldsDto name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the pet type.
   * @return name
   */
  @NotNull @Size(min = 1, max = 80)
  @Schema(name = "name", example = "cat", description = "The name of the pet type.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PetTypeFieldsDto petTypeFields = (PetTypeFieldsDto) o;
    return Objects.equals(this.name, petTypeFields.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PetTypeFieldsDto {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

