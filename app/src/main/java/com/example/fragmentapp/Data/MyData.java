package com.example.fragmentapp.Data;

import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class MyData {
    private final DatabaseReference databaseReference;

    public MyData() {
        // Initialize Firebase Realtime Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
    }

    public void addUser(String username, String password, String phoneNumber, UserAdditionCallback callback) {
        databaseReference.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Check if user already exists
                if (!dataSnapshot.exists()) {
                    // User does not exist, add user
                    UserInfo user = new UserInfo(password, phoneNumber); // Consider hashing the password
                    databaseReference.child(username).setValue(user, (databaseError, databaseReference) -> {
                        if (databaseError == null) {
                            // Successfully added user
                            callback.onUserAddition(true);
                        } else {
                            // Failed to add user
                            callback.onUserAddition(false);
                        }
                    });
                } else {
                    // User already exists
                    callback.onUserAddition(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Database error
                callback.onUserAddition(false);
            }
        });
    }

    public void checkUserCredentials(String username, String password, UserValidationCallback callback) {
        databaseReference.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    UserInfo user = dataSnapshot.getValue(UserInfo.class);
                    if (user != null && user.password.equals(password)) {
                        // Username and password are correct
                        callback.onUserValidation(true);
                    } else {
                        // Password is incorrect
                        callback.onUserValidation(false);
                    }
                } else {
                    // Username does not exist
                    callback.onUserValidation(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors
                callback.onUserValidation(false);
            }
        });
    }

    private static class UserInfo {
        public String password; // Storing passwords in plain text is not secure
        public String phoneNumber;

        public UserInfo() {
            // Default constructor required for calls to DataSnapshot.getValue(UserInfo.class)
        }

        UserInfo(String password, String phoneNumber) {
            this.password = password;
            this.phoneNumber = phoneNumber;
        }
    }

    // Callback interface for adding users
    public interface UserAdditionCallback {
        void onUserAddition(boolean isSuccess);
    }

    // Callback interface for validating users
    public interface UserValidationCallback {
        void onUserValidation(boolean isValidUser);
    }
}
