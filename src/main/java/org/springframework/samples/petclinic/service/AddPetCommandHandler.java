package org.springframework.samples.petclinic.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.mapper.PetMapper;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.rest.dto.AddPetCommandDto;
import org.springframework.stereotype.Component;

@Component
public class AddPetCommandHandler {

    private final ClinicService clinicService;
    private final PetMapper petMapper;

    @Autowired
    public AddPetCommandHandler(ClinicService clinicService, PetMapper petMapper) {
        this.clinicService = clinicService;
        this.petMapper = petMapper;
    }

    @Transactional
    public Pet execute(AddPetCommandDto addPetCommandDto) {
        Pet pet = toPet(addPetCommandDto);
        this.clinicService.savePet(pet);
        return pet;
    }

    private Pet toPet(AddPetCommandDto petDto) {
        Pet pet = new Pet();
        pet.setOwner( petDtoToOwner( petDto ) );
        pet.setName( petDto.getName() );
        pet.setBirthDate( petDto.getBirthDate() );
        pet.setType( petMapper.toPetType( petDto.getType() ) );
        return pet;
    }


    protected Owner petDtoToOwner(AddPetCommandDto petDto) {
        return clinicService.findOwnerById(petDto.getOwnerId());
    }
}
