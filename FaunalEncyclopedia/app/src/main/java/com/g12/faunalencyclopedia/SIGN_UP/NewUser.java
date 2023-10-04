package com.g12.faunalencyclopedia.SIGN_UP;

public class NewUser implements User{
    private String username;
    public NewUser(String username){

        this.username = username;
    }

    @Override
    public void signup() {
        System.out.println("Welcome "+username+"!!");
    }
}
