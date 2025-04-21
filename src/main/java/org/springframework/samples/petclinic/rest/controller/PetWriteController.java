/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic.rest.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.mapper.PetMapper;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.rest.api.PetsWriteApi;
import org.springframework.samples.petclinic.rest.dto.AddPetCommandDto;
import org.springframework.samples.petclinic.rest.dto.PetDto;
import org.springframework.samples.petclinic.rest.dto.UpdatePetCommandDto;
import org.springframework.samples.petclinic.service.AddPetCommand;
import org.springframework.samples.petclinic.service.AddPetCommandHandler;
import org.springframework.samples.petclinic.service.DeleteCommandHandler;
import org.springframework.samples.petclinic.service.DeletePetCommand;
import org.springframework.samples.petclinic.service.UpdatePetCommand;
import org.springframework.samples.petclinic.service.UpdatePetCommandHandler;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Vitaliy Fedoriv
 */

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("api")
public class PetWriteController implements PetsWriteApi {

    private final PetMapper petMapper;

    private final UpdatePetCommandHandler updatePetCommandHandler;
    private final AddPetCommandHandler addPetCommandHandler;
    private final DeleteCommandHandler deleteCommandHandler;

    public PetWriteController(PetMapper petMapper,
                              UpdatePetCommandHandler updatePetCommandHandler,
                              AddPetCommandHandler addPetCommandHandler,
                              DeleteCommandHandler deleteCommandHandler) {
        this.petMapper = petMapper;
        this.updatePetCommandHandler = updatePetCommandHandler;
        this.addPetCommandHandler = addPetCommandHandler;
        this.deleteCommandHandler = deleteCommandHandler;
    }

    @PreAuthorize("hasRole(@roles.OWNER_ADMIN)")
    @Override
    public ResponseEntity<PetDto> updatePet(Integer petId, UpdatePetCommandDto petDto) {
        Pet currentPet = updatePetCommandHandler.execute(new UpdatePetCommand(petDto.getId(), petDto.getName(), petDto.getBirthDate(), petDto.getType().getId()));
        if (currentPet == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(petMapper.toPetDto(currentPet), HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole(@roles.OWNER_ADMIN)")
    @Override
    public ResponseEntity<PetDto> deletePet(Integer petId) {
        Pet pet = deleteCommandHandler.execute(new DeletePetCommand(petId));
        if (pet == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole(@roles.OWNER_ADMIN)")
    @Override
    public ResponseEntity<PetDto> addPet(AddPetCommandDto petDto) {
        Pet newPet = addPetCommandHandler.execute(new AddPetCommand(petDto.getName(), petDto.getBirthDate(), petDto.getType().getId(), petDto.getOwnerId()));
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(UriComponentsBuilder.newInstance().path("/api/pets/{id}").buildAndExpand(newPet.getId()).toUri());
        return new ResponseEntity<>(petMapper.toPetDto(newPet), headers, HttpStatus.CREATED);
    }
}
