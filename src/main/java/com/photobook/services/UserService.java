package com.photobook.services;

import com.photobook.data.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by masya on 2/9/17.
 */
public class UserService {

    private final Map<Integer, User> usersMap = new HashMap<>();

    private int nextId = 1;


    public void addUser(User user) throws UserServiceException {

        if (user.getName() == null || user.getName().isEmpty()) {
            throw new UserServiceException("User name should be provided");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new UserServiceException("User E-mail should be provided");
        }
        if (user.getPhone() == null || user.getPhone().isEmpty()) {
            throw new UserServiceException("User phone should be provided");
        }
        if (user.getDeliveryAddress() == null || user.getDeliveryAddress().isEmpty()) {
            throw new UserServiceException("User delivery address should be provided");
        }
        if (user.getPostalCode() == null || user.getPostalCode().isEmpty()) {
            throw new UserServiceException("User postal should be provided");
        }
        if (user.getPaymentMethod() == null ) {
            throw new UserServiceException("User payment method should be provided");
        }

        for (User u : usersMap.values()) {
            if (user.getEmail().equals(u.getEmail()))
            {
                throw new UserServiceException("User E-mail " + user.getEmail() + " cannot be the same.");
            }
        }

        final int userId = nextId++;
        User newUser = new User(userId, user.getName(), user.getEmail(), user.getPhone(), user.getDeliveryAddress(),
                user.getPostalCode(),user.getPaymentMethod());

        usersMap.put(userId, newUser);
    }

    public void modifyUser (User userWithModifiedFields) throws UserServiceException, UserNotFoundException {
        final User existingUser = usersMap.get(userWithModifiedFields.getId());
        if (existingUser == null)
        {
            throw new UserNotFoundException();
        }

        for (User u : usersMap.values()) {
            if (existingUser.getId() != u.getId() && userWithModifiedFields.getEmail().equals(u.getEmail()))
            {
                throw new UserServiceException("User E-mail " + userWithModifiedFields.getEmail() + " cannot be the same.");
            }
        }

        final User modifiedUser = existingUser.merge(userWithModifiedFields);
        usersMap.put(existingUser.getId(), modifiedUser);
    }

    public User[] getUsers () {
        return usersMap.values().toArray(new User[usersMap.values().size()]);
    }

    public User getUser (int id) {
        return usersMap.get(id);
    }

     public void deleteUser (int id) throws UserNotFoundException {
        if (usersMap.remove(id) == null) {
            throw new UserNotFoundException();
        }
     }


}
