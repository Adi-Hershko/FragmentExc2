package com.example.fragmentapp.Data;

import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {
    private final MyData myData = new MyData();

    public boolean registerUser(String username, String password, String phoneNumber) {
        return myData.addUser(username, password, phoneNumber);
    }

    public boolean validateUser(String username, String password) {
        // Assuming a method to check user credentials is added to MyData
        return myData.checkUserCredentials(username, password);
    }
}
