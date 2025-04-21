package org.springframework.samples.petclinic.service;

import java.time.LocalDate;

public record AddPetCommand(String name, LocalDate birthDate, int typeId, int ownerId) implements Command {
}

