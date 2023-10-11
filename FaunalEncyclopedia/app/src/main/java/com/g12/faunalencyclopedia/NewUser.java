package com.g12.faunalencyclopedia;

public class NewUser implements UserFactory{

    public User createUser(String username, String email, String password){
        return new User(username, email, password);
    }
}
