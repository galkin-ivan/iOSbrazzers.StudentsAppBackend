package ru.galkinivan.StudentsAppBackend.service;

import ru.galkinivan.StudentsAppBackend.model.User;

/**
 * Service class for {@link ru.galkinivan.StudentsAppBackend.model.User}
 *
 * @author galkin ivan
 * @version 1.0
 */

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
