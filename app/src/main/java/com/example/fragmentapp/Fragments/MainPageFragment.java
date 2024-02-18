package com.example.fragmentapp.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fragmentapp.Activities.GroceriesAdapter; // Make sure this import is correct
import com.example.fragmentapp.Data.Groceries;
import com.example.fragmentapp.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainPageFragment extends Fragment {

    private GroceriesAdapter adapter;
    private final List<Groceries> groceriesList = new ArrayList<>();

    public MainPageFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_page, container, false);

        String usernameBundle = getArguments() != null ? getArguments().getString("username", "User") : "User";

        TextView title = view.findViewById(R.id.TextViewUsername);
        title.setText("Hello " + usernameBundle + "!");

        RecyclerView recyclerView = view.findViewById(R.id.res);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new GroceriesAdapter(getActivity(), groceriesList); // Initialize adapter with empty list
        recyclerView.setAdapter(adapter);

        fetchGroceriesFromFirestore(); // Fetch groceries and update the adapter

        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    private void fetchGroceriesFromFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("groceries").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                groceriesList.clear(); // Clear existing data
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Groceries grocery = document.toObject(Groceries.class);
                    groceriesList.add(grocery);
                }
                adapter.notifyDataSetChanged(); // Notify the adapter of data changes
            } else {
                // Handle the error
            }
        });
    }
}
