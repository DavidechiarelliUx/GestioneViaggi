package it.epicode.gestioneViaggi.exception;

import jakarta.persistence.EntityNotFoundException;

public class NotFoundException extends EntityNotFoundException {
    public NotFoundException(Long id) {
        super("Resource not found: id=" + id);
    }
}