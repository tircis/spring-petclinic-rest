package org.springframework.samples.petclinic.rest.dto;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * A pet owner.
 */

@Schema(name = "Owner", description = "A pet owner.")
@JsonTypeName("Owner")
public class FoundOwnerDto {

  private String firstName;

  private String lastName;

  private String address;

  private String city;

  private String telephone;

  private int id;

  private Set<FoundPetDto> pets = new LinkedHashSet<>();

  public FoundOwnerDto() {
    super();
  }

  public FoundOwnerDto firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * The first name of the pet owner.
   * @return firstName
   */
  @Schema(name = "firstName", example = "George", description = "The first name of the pet owner.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("firstName")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public FoundOwnerDto lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * The last name of the pet owner.
   * @return lastName
   */
  @Schema(name = "lastName", example = "Franklin", description = "The last name of the pet owner.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("lastName")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public FoundOwnerDto address(String address) {
    this.address = address;
    return this;
  }

  /**
   * The postal address of the pet owner.
   * @return address
   */
  @Schema(name = "address", example = "110 W. Liberty St.", description = "The postal address of the pet owner.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("address")
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public FoundOwnerDto city(String city) {
    this.city = city;
    return this;
  }

  /**
   * The city of the pet owner.
   * @return city
   */
  @Schema(name = "city", example = "Madison", description = "The city of the pet owner.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("city")
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public FoundOwnerDto telephone(String telephone) {
    this.telephone = telephone;
    return this;
  }

  /**
   * The telephone number of the pet owner.
   * @return telephone
   */
  @Schema(name = "telephone", example = "6085551023", description = "The telephone number of the pet owner.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("telephone")
  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public FoundOwnerDto id(int id) {
    this.id = id;
    return this;
  }

  /**
   * The ID of the pet owner.
   * minimum: 0
   * @return id
   */
  @Schema(name = "id", accessMode = Schema.AccessMode.READ_ONLY, example = "1", description = "The ID of the pet owner.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  /**
   * The pets owned by this individual including any booked vet visits.
   * @return pets
   */
  @Schema(name = "pets", accessMode = Schema.AccessMode.READ_ONLY, description = "The pets owned by this individual including any booked vet visits.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("pets")
  public Set<FoundPetDto> getPets() {
    return pets;
  }

  public void setPets(Set<String> pets) {
    this.pets = pets.stream().map(FoundPetDto::new).collect(Collectors.toSet());
  }


  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
      FoundOwnerDto owner = (FoundOwnerDto) o;
      return Objects.equals(this.firstName, owner.firstName) &&
          Objects.equals(this.lastName, owner.lastName) &&
          Objects.equals(this.address, owner.address) &&
          Objects.equals(this.city, owner.city) &&
          Objects.equals(this.telephone, owner.telephone) &&
          Objects.equals(this.id, owner.id) &&
          Objects.equals(this.pets, owner.pets);
  }

  @Override
  public int hashCode() {
      return Objects.hash(firstName, lastName, address, city, telephone, id, pets);
  }

  @Override
  public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class OwnerDto {\n");
      sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
      sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
      sb.append("    address: ").append(toIndentedString(address)).append("\n");
      sb.append("    city: ").append(toIndentedString(city)).append("\n");
      sb.append("    telephone: ").append(toIndentedString(telephone)).append("\n");
      sb.append("    id: ").append(toIndentedString(id)).append("\n");
      sb.append("    pets: ").append(toIndentedString(pets)).append("\n");
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

  public static class FoundPetDto {

      private final String name;

      public FoundPetDto(String name) {
          this.name = name;
      }

      public String getName() {
          return name;
      }
  }
}

