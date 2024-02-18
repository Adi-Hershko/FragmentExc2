package com.example.fragmentapp.Data;

import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {
    private final MyData myData = new MyData();

    // Adjust the method to accept callback parameters for handling asynchronous operation results
    public void registerUser(String username, String password, String phoneNumber, MyData.UserAdditionCallback callback) {
        // Pass the callback to the method in MyData
        myData.addUser(username, password, phoneNumber, callback);
    }

    public void validateUser(String username, String password, MyData.UserValidationCallback callback) {
        // Pass the callback to the method in MyData
        myData.checkUserCredentials(username, password, callback);
    }
}
