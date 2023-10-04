package com.g12.faunalencyclopedia.SIGN_UP;

import java.util.Scanner;

public class userRegistration {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your first name");
        String first_name = sc.nextLine();
        System.out.println("Enter your last name");
        String last_name = sc.nextLine();
        System.out.println("Enter your desired username");
        String username = sc.nextLine();
        System.out.println("Choose your password");
        String password = sc.nextLine();

        UserFactory userfactory = new UserFactory();
        User user = userfactory.createUser(username);
        user.signup();
    }
}
