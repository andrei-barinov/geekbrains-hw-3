package ru.geekbrains.exception;

public class PersonNotFoundException extends NotFoundException{
    public PersonNotFoundException(String message) {
        super(message);
    }
}
