package com.g12.faunalencyclopedia;

public interface UserFactory {
    User createUser(String username, String email, String password);
}
