package com.photobook.services;

import com.photobook.data.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by masya on 2/9/17.
 */
public class UserService {

    private final Map<Integer, User> usersMap = new HashMap<>();

    private int nextId = 0;


    public void addUser(User user) throws UserServiceException {
        final int userId = nextId++;
        if (user.getName() == null && user.getName().isEmpty()) {
            throw new UserServiceException("User name should be provided");
        }

        User newUser = new User(userId, user.getName(), user.getEmail(), user.getPhone(), user.getDeliveryAddress(), user.getPostalCode(),user.getPaymentMethod());
        usersMap.put(userId, newUser);
    }

    public void modifyUser (User userWithModifiedFields) throws UserServiceException {
        final User existingUser = usersMap.get(userWithModifiedFields.getId());
        //todo check if user exists
        if (existingUser == null)
        {
            throw new UserServiceException("User with ID: " + userWithModifiedFields.getId() + " does not exist");
        }

        final User modifiedUser = existingUser.merge(userWithModifiedFields);
        usersMap.put(existingUser.getId(), modifiedUser);

    }

    public User [] getUsers (){
        return usersMap.values().toArray(new User[usersMap.values().size()]);
    }
    public User getUser (int id) {
        return usersMap.get(id);
    }

     public void deleteUser (int id){
        usersMap.remove(id);
     }


}
