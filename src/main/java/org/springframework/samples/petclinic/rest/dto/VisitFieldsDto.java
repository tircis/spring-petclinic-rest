package org.springframework.samples.petclinic.rest.dto;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

/**
 * Editable fields of a vet visit.
 */

@Schema(name = "VisitFields", description = "Editable fields of a vet visit.")
@JsonTypeName("VisitFields")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-25T10:14:13.839343600+01:00[Europe/Paris]", comments = "Generator version: 7.12.0")
public class VisitFieldsDto {

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate date;

  private String description;

  public VisitFieldsDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public VisitFieldsDto(String description) {
    this.description = description;
  }

  public VisitFieldsDto date(LocalDate date) {
    this.date = date;
    return this;
  }

  /**
   * The date of the visit.
   * @return date
   */
  @Valid
  @Schema(name = "date", example = "2013-01-01", description = "The date of the visit.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("date")
  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public VisitFieldsDto description(String description) {
    this.description = description;
    return this;
  }

  /**
   * The description for the visit.
   * @return description
   */
  @NotNull @Size(min = 1, max = 255)
  @Schema(name = "description", example = "rabies shot", description = "The description for the visit.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VisitFieldsDto visitFields = (VisitFieldsDto) o;
    return Objects.equals(this.date, visitFields.date) &&
        Objects.equals(this.description, visitFields.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VisitFieldsDto {\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

