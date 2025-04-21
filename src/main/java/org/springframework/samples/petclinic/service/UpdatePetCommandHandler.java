package org.springframework.samples.petclinic.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.stereotype.Component;

@Component
public class UpdatePetCommandHandler implements CommandHandler<UpdatePetCommand, Pet> {

    private final ClinicService clinicService;

    @Autowired
    public UpdatePetCommandHandler(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @Transactional
    @Override
    public Pet execute(UpdatePetCommand updatePetCommand) {
        Pet currentPet = this.clinicService.findPetById(updatePetCommand.id());
        if (currentPet == null) {
            return null;
        }
        currentPet.setBirthDate(updatePetCommand.birthDate());
        currentPet.setName(updatePetCommand.name());
        currentPet.setType(this.clinicService.findPetTypeById(updatePetCommand.typeId()));
        this.clinicService.savePet(currentPet);
        return currentPet;
    }
}
