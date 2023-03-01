package personal.model;

import java.util.ArrayList;
import java.util.List;

public class RepositoryFile implements Repository {
    private Mapper mapper;
    private FileOperation fileOperation;

    public RepositoryFile(FileOperation fileOperation, Mapper mapper) {
        this.fileOperation = fileOperation;
        this.mapper = mapper;
    }

    @Override
    public List<User> getAllUsers() {
        List<String> lines = fileOperation.readAllLines();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            users.add(mapper.map(line));
        }
        return users;
    }

    @Override
    public String CreateUser(User user) {

        List<User> users = getAllUsers();
        int max = 0;
        for (User item : users) {
            int id = Integer.parseInt(item.getId());
            if (max < id){
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        user.setId(id);
        users.add(user);
        saveRepository(users);
        return id;
    }

    public void replaceUserInFile(User user, String userID) {

        List<User> users = getAllUsers();
        int i = 0;
        for (User u:users){
            if (u.getId().equals(userID)){
                i = users.indexOf(u);
                user.setId(userID);
                users.set(i,user);
                saveRepository(users);
            }
        }

    }

    private void saveRepository(List<User> users) {
        List<String> lines = new ArrayList<>();
        for (User item: users) {
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);
    }

    @Override
    public void deleteUser(String id){
        List<User> users = getAllUsers();
        User foundUser=null;
        for(User u:users){
            if(u.getId().equals(id)){
                foundUser = u;
                break;
            }
        }
        if (foundUser!=null) {
            users.remove(foundUser);
            saveRepository(users);
        }
    }
}
