package personal.views;

import personal.controllers.UserController;
import personal.model.RepositoryFile;
import personal.model.User;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewUser {

    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com = Commands.NONE;

        while (true) {
            try {
                String command = prompt("Введите команду: ");
                com = Commands.valueOf(command.toUpperCase());

                if (com == Commands.EXIT) return;
                switch (com) {
                    case CREATE:
                        caseCreate();
                        break;
                    case READ:
                        caseRead();
                        break;
                    case LIST:
                        caseList();
                        break;
                    case DELETE:
                        caseDelete();
                        break;
                    case UPDATE:
                        caseUpdate();
                        break;
                }
            } catch (Exception ee){
                System.out.printf("Something went wrong with commands: %s \n", ee.getMessage());
            }
            }
        }

    private void caseDelete() {
        String id = prompt("Enter the id: ");
        userController.deleteUser(id);
        System.out.println();
    }


    private void caseCreate() throws Exception {
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        userController.saveUser(new User(firstName, lastName, phone));
    }

    private void caseRead() {
        String id = prompt("Идентификатор пользователя: ");
        try {
            User user = userController.readUser(id);
            System.out.println(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void caseList() {
        List <User> usersList = userController.readUsers();
        for (User u:usersList){
            System.out.println(u);
        }
    }
    private void caseUpdate() throws Exception {
        System.out.println("So you want to update one of the contacts");
        String userID = prompt("Please enter ID: ");
        User u = userController.readUser(userID);
        String askOption = prompt("Please enter 1 to change the first name, enter 2 to change the last name, enter 3 to change phone number: ");
        if (askOption.equals("1")||askOption.equals("2")||askOption.equals("3")){
            String newInformation = prompt("Please enter new information: ");
            userController.updateInformation(userID,askOption,newInformation,u);
        } else {
            System.out.println("Wrong option");
        }

    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
