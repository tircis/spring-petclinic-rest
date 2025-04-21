package org.springframework.samples.petclinic.service;

/**
 * Executes a command.
 *
 * It's possible to have it return an object, but this isn't a common case.
 * In the usual case where the execution doesn't return anything, the return type should be {@link Void} (R becomes {@link Void})
 * and the method will "return null".
 *
 * @param <C>
 * @param <R>
 */
@FunctionalInterface
public interface CommandHandler<C extends Command, R> {

    R execute(C command);
}
