package org.springframework.samples.petclinic.rest.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

public class AddPetCommandDto {

    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    private PetTypeDto type;

    private int ownerId;

    public AddPetCommandDto() {
        super();
    }

    public AddPetCommandDto name(String name) {
        this.name = name;
        return this;
    }

    /**
     * The name of the pet.
     *
     * @return name
     */
    @NotNull
    @Size(max = 30)
    @Schema(name = "name", example = "Leo", description = "The name of the pet.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddPetCommandDto birthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    /**
     * The date of birth of the pet.
     *
     * @return birthDate
     */
    @NotNull
    @Valid
    @Schema(name = "birthDate", example = "2010-09-07", description = "The date of birth of the pet.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("birthDate")
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public AddPetCommandDto type(PetTypeDto type) {
        this.type = type;
        return this;
    }

    /**
     * Get type
     *
     * @return type
     */
    @NotNull
    @Valid
    @Schema(name = "type", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("type")
    public PetTypeDto getType() {
        return type;
    }

    public void setType(PetTypeDto type) {
        this.type = type;
    }

    public AddPetCommandDto ownerId(int ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    /**
     * The ID of the pet's owner.
     * minimum: 0
     *
     * @return ownerId
     */
    @Min(0)
    @Schema(name = "ownerId", accessMode = Schema.AccessMode.READ_ONLY, example = "1", description = "The ID of the pet's owner.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("ownerId")
    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PetDto {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    birthDate: ").append(toIndentedString(birthDate)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    ownerId: ").append(toIndentedString(ownerId)).append("\n");
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

