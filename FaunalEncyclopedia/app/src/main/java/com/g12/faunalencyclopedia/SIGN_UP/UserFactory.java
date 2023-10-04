package com.g12.faunalencyclopedia.SIGN_UP;

public class UserFactory {
    public User createUser(String username){
        return  new NewUser(username);
    }
}
