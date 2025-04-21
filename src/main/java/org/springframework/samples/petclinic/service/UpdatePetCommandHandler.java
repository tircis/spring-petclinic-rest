package org.springframework.samples.petclinic.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.mapper.PetMapper;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.rest.dto.UpdatePetCommandDto;
import org.springframework.stereotype.Component;

@Component
public class UpdatePetCommandHandler {

    private final ClinicService clinicService;
    private final PetMapper petMapper;

    @Autowired
    public UpdatePetCommandHandler(ClinicService clinicService, PetMapper petMapper) {
        this.clinicService = clinicService;
        this.petMapper = petMapper;
    }

    @Transactional
    public Pet execute(UpdatePetCommandDto updatePetCommandDto) {
        Pet currentPet = this.clinicService.findPetById(updatePetCommandDto.getId());
        if (currentPet == null) {
            return null;
        }
        currentPet.setBirthDate(updatePetCommandDto.getBirthDate());
        currentPet.setName(updatePetCommandDto.getName());
        currentPet.setType(this.petMapper.toPetType(updatePetCommandDto.getType()));
        this.clinicService.savePet(currentPet);
        return currentPet;
    }
}
