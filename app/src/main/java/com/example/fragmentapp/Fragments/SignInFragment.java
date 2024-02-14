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

public class SignInFragment extends Fragment {

    private UserViewModel userViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        // Initialize UserViewModel here
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

            boolean isValid = userViewModel.validateUser(username, password);
            if (isValid) {
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).navigateToMainPageFragment();
                }
            } else {
                Toast.makeText(getActivity(), "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });

        // Implement navigation to SignUpFragment
        buttonRegister.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_signInFragment_to_fragmentSignUp);
        });

        return view;
    }
}
