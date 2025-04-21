package org.springframework.samples.petclinic.service;

import org.springframework.samples.petclinic.model.Pet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeleteCommandHandler implements CommandHandler<DeletePetCommand, Pet> {

    private final ClinicService clinicService;

    public DeleteCommandHandler(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @Transactional
    @Override
    public Pet execute(DeletePetCommand command) {
        Pet foundPet = this.clinicService.findPetById(command.petId());
        if (foundPet == null) {
            return null;
        } else {
        this.clinicService.deletePet(foundPet);
        }
        return foundPet;
    }
}
