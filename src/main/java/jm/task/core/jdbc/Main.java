package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        final UserService userService = new UserServiceImpl();
//        userService.dropUsersTable();
//        userService.createUsersTable();
        userService.saveUser("Vasya", "Pupkin", (byte) 16);
        userService.removeUserById(1L);
        System.out.println(userService.getAllUsers());
//        userService.cleanUsersTable();
    }
}
