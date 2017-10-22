package ru.galkinivan.StudentsAppBackend.service;

/**
 * Service for Security.
 *
 * @author Galkin Ivan
 * @version 1.0
 */

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
