package com.example.fragmentapp.Data;

import android.util.Log;

import com.example.fragmentapp.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class Groceries {
    private Integer imageUrl; // URL as a String
    private String name;

    // No-args constructor for Firestore
    public Groceries() {}

    public Groceries(Integer imageUrl, String name) {
        this.imageUrl = imageUrl;
        this.name = name;
    }

    // Getters and Setters
    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public void addGroceriesToFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Integer[] imageUrls = {
            R.drawable.bleach, R.drawable.butter, R.drawable.chicken, R.drawable.cola,
            R.drawable.cola_zero, R.drawable.corn, R.drawable.cottege, R.drawable.cucumber,
            R.drawable.eggplant, R.drawable.espresso, R.drawable.fuze_tea, R.drawable.ground_beef,
            R.drawable.ground_coffee, R.drawable.ice_coffee, R.drawable.milk, R.drawable.milky,
            R.drawable.mushroom, R.drawable.onion, R.drawable.red_onion, R.drawable.salami,
            R.drawable.shampoo, R.drawable.soap, R.drawable.sprite, R.drawable.steak,
            R.drawable.tomato, R.drawable.toothbrush, R.drawable.toothpaste, R.drawable.turkish_coffee,
            R.drawable.yellow_cheese
    };

    String[] names = {
            "Bleach", "Butter", "Chicken", "Cola", "Cola Zero", "Corn", "Cottage", "Cucumber",
            "Eggplant", "Espresso", "Fuze Tea", "Ground Beef", "Ground Coffee", "Ice Coffee", "Milk",
            "Milky", "Mushroom", "Onion", "Red Onion", "Salami", "Shampoo", "Soap", "Sprite", "Steak",
            "Tomato", "Toothbrush", "Toothpaste", "Turkish Coffee", "Yellow Cheese"
    };


        for (int i = 0; i < names.length; i++) {
            Groceries grocery = new Groceries(imageUrls[i], names[i]);
            db.collection("groceries").add(grocery)
                    .addOnSuccessListener(documentReference -> Log.d("Firestore", "DocumentSnapshot added with ID: " + documentReference.getId()))
                    .addOnFailureListener(e -> Log.w("Firestore", "Error adding document", e));
        }
    }

    public void fetchGroceriesFromFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("groceries").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Groceries> groceriesList = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    groceriesList.add(document.toObject(Groceries.class));
                }
                // Use your groceriesList as needed, e.g., update your UI
            } else {
                Log.w("Firestore", "Error getting documents.", task.getException());
            }
        });
    }
    public void deleteGroceryFromFirestore(String documentId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("groceries").document(documentId).delete()
                .addOnSuccessListener(aVoid -> Log.d("Firestore", "DocumentSnapshot successfully deleted!"))
                .addOnFailureListener(e -> Log.w("Firestore", "Error deleting document", e));
    }
}



















//package com.example.fragmentapp.Data;
//
//import com.example.fragmentapp.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Groceries {
//    private Integer image;
//    private String name;
//
//    static Integer[] images = {
//            R.drawable.bleach, R.drawable.butter, R.drawable.chicken, R.drawable.cola,
//            R.drawable.cola_zero, R.drawable.corn, R.drawable.cottege, R.drawable.cucumber,
//            R.drawable.eggplant, R.drawable.espresso, R.drawable.fuze_tea, R.drawable.ground_beef,
//            R.drawable.ground_coffee, R.drawable.ice_coffee, R.drawable.milk, R.drawable.milky,
//            R.drawable.mushroom, R.drawable.onion, R.drawable.red_onion, R.drawable.salami,
//            R.drawable.shampoo, R.drawable.soap, R.drawable.sprite, R.drawable.steak,
//            R.drawable.tomato, R.drawable.toothbrush, R.drawable.toothpaste, R.drawable.turkish_coffee,
//            R.drawable.yellow_cheese
//    };
//
//    static String[] names = {
//            "Bleach", "Butter", "Chicken", "Cola", "Cola Zero", "Corn", "Cottage", "Cucumber",
//            "Eggplant", "Espresso", "Fuze Tea", "Ground Beef", "Ground Coffee", "Ice Coffee", "Milk",
//            "Milky", "Mushroom", "Onion", "Red Onion", "Salami", "Shampoo", "Soap", "Sprite", "Steak",
//            "Tomato", "Toothbrush", "Toothpaste", "Turkish Coffee", "Yellow Cheese"
//    };
//
//    // Static list of groceries
//    private static List<Groceries> groceryList = new ArrayList<>();
//
//    // Static initializer block to populate the grocery list
//    static {
//        for (int i = 0; i < images.length; i++) {
//            groceryList.add(new Groceries(images[i], names[i]));
//        }
//    }
//
//    // Constructor
//    public Groceries(Integer image, String name) {
//        this.image = image;
//        this.name = name;
//    }
//
//    // Getter methods
//    public Integer getImage() {
//        return image;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    // Method to get the static list of groceries
//    public static List<Groceries> getGroceryList() {
//        return groceryList;
//    }
//}
