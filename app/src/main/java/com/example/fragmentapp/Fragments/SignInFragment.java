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

import com.example.fragmentapp.Data.UserViewModel;
import com.example.fragmentapp.R;

public class SignInFragment extends Fragment {

    private UserViewModel userViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        EditText editTextUsername = view.findViewById(R.id.editTextUsername);
        EditText editTextPassword = view.findViewById(R.id.editTextPassword);
        Button buttonLogin = view.findViewById(R.id.buttonLogin);
        Button buttonRegister = view.findViewById(R.id.buttonRegister);

        buttonLogin.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(getActivity(), "Username and password cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            // Use validateUser with a callback
            userViewModel.validateUser(username, password, isValid -> {
                getActivity().runOnUiThread(() -> {
                    if (isValid) {
                        // Valid credentials
                        Bundle bundle = new Bundle();
                        bundle.putString("username", username);
                        Navigation.findNavController(v).navigate(R.id.action_signInFragment_to_fragmentMainPage, bundle);
                    } else {
                        // Invalid credentials
                        Toast.makeText(getActivity(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                });
            });
        });

        buttonRegister.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_signInFragment_to_fragmentSignUp));

        return view;
    }
}
