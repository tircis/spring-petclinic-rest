package org.springframework.samples.petclinic.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.stereotype.Component;

@Component
public class AddPetCommandHandler implements CommandHandler<AddPetCommand, Pet> {

    private final ClinicService clinicService;

    @Autowired
    public AddPetCommandHandler(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @Transactional
    @Override
    public Pet execute(AddPetCommand addPetCommand) {
        Pet pet = toPet(addPetCommand);
        this.clinicService.savePet(pet);
        return pet;
    }

    private Pet toPet(AddPetCommand petDto) {
        Pet pet = new Pet();
        pet.setOwner(clinicService.findOwnerById(petDto.ownerId()));
        pet.setName(petDto.name());
        pet.setBirthDate(petDto.birthDate());
        pet.setType(clinicService.findPetTypeById(petDto.typeId()));
        return pet;
    }
}
