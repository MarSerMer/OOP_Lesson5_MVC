package personal.controllers;

import personal.model.Repository;
import personal.model.User;
import personal.model.ValidateUser;

import java.util.List;

public class UserController {
    private ValidateUser validator = new ValidateUser();
    private final Repository repository;

    public UserController(Repository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) throws Exception {
        validator.check(user);
        repository.CreateUser(user);
    }
    public void replaceUser(User user, String userID) throws Exception {
        validator.check(user);
        repository.replaceUserInFile(user,userID);
    }

    public User readUser(String userId) throws Exception {
        List<User> users = repository.getAllUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }

        throw new Exception("User not found");
    }

    public List<User> readUsers(){
        List<User> users = repository.getAllUsers();
        return users;
    }

    public void deleteUser (String id){
        repository.deleteUser(id);
    }

    public void updateInformation(String userID, String option, String newInformation,User u) throws Exception {
        switch (option) {
                case "1": //first name
                    replaceUser(new User(userID, newInformation, u.getLastName(), u.getPhone()),userID);
                    break;
                case "2": //last name
                    replaceUser(new User(userID, u.getFirstName(), newInformation, u.getPhone()),userID);
                    break;
                case "3": //phone
                    replaceUser(new User(userID, u.getFirstName(), u.getLastName(), newInformation),userID);
                    break;
            }
    }

}
