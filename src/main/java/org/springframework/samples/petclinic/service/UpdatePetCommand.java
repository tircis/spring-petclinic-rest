package org.springframework.samples.petclinic.service;

import java.time.LocalDate;

public record UpdatePetCommand(int id, String name, LocalDate birthDate, int typeId) implements Command {

}
