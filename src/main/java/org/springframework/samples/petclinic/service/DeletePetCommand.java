package org.springframework.samples.petclinic.service;

public record DeletePetCommand(int petId) implements Command {
}
