package org.springframework.samples.petclinic.service;

/**
 * Marker interface for commands available in the domain: all system commands must implement it.
 *
 * Commands are actions that have an impact on the system's state, so they are generally all write actions.
 * Commands contain only what is strictly necessary for their execution; for example, a delete command
 * will contain only the identifier of the object to delete, and not the entire object as is usually seen
 * in the paradigm where entities are exposed to the Front end.
 */
public interface Command {
}
