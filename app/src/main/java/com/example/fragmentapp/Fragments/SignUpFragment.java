package com.example.fragmentapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.fragmentapp.Activities.MainActivity;
import com.example.fragmentapp.Data.UserViewModel;
import com.example.fragmentapp.R;

public class SignUpFragment extends Fragment {

    private UserViewModel userViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        // Initialize the UserViewModel here, safely after the Fragment is attached to the Activity
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        EditText editTextRegisterUsername = view.findViewById(R.id.editTextRegisterUsername);
        EditText editTextRegisterPassword = view.findViewById(R.id.editTextRegisterPassword);
        EditText editTextRegisterPhone = view.findViewById(R.id.editTextRegisterPhone);
        Button buttonSubmitRegister = view.findViewById(R.id.buttonSubmitRegister);

        // Handle registration action
        // Inside SignUpFragment's buttonSubmitRegister.setOnClickListener
        buttonSubmitRegister.setOnClickListener(v -> {
            // Extract user input
            String username = editTextRegisterUsername.getText().toString().trim();
            String password = editTextRegisterPassword.getText().toString().trim();
            String phoneNumber = editTextRegisterPhone.getText().toString().trim();

            // Validate input
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(getActivity(), "Username and password cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            // Attempt registration
            boolean success = userViewModel.registerUser(username, password, phoneNumber);
            if (success) {
                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                // Navigate on success
                Navigation.findNavController(v).navigate(R.id.action_fragmentSignUp_to_fragmentMainPage, bundle);
            } else {
                Toast.makeText(getActivity(), "Username already taken", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}
