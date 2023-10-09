package com.g12.faunalencyclopedia.SIGN_UP;

public class NewUser implements UserFactory{

    public User createUser(String username, String email, String password){
        return new User(username, email, password);
    }
}
