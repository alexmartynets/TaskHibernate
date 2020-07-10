package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        final UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Vasya", "Pupkin", (byte) 16);
        userService.saveUser("Petr", "Petrov", (byte) 32);
        userService.saveUser("Sashka", "Borodach", (byte) 64);
        userService.saveUser("Superman", "RealAccount", (byte) 127);
        System.out.println(userService.getAllUsers());
        userService.removeUserById(1L);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}