package com.example.fragmentapp.Data;

import com.example.fragmentapp.R;

import java.util.ArrayList;
import java.util.List;

public class Groceries {
    private Integer image;
    private String name;

    static Integer[] images = {
            R.drawable.bleach, R.drawable.butter, R.drawable.chicken, R.drawable.cola,
            R.drawable.cola_zero, R.drawable.corn, R.drawable.cottege, R.drawable.cucumber,
            R.drawable.eggplant, R.drawable.espresso, R.drawable.fuze_tea, R.drawable.ground_beef,
            R.drawable.ground_coffee, R.drawable.ice_coffee, R.drawable.milk, R.drawable.milky,
            R.drawable.mushroom, R.drawable.onion, R.drawable.red_onion, R.drawable.salami,
            R.drawable.shampoo, R.drawable.soap, R.drawable.sprite, R.drawable.steak,
            R.drawable.tomato, R.drawable.toothbrush, R.drawable.toothpaste, R.drawable.turkish_coffee,
            R.drawable.yellow_cheese
    };

    static String[] names = {
            "Bleach", "Butter", "Chicken", "Cola", "Cola Zero", "Corn", "Cottage", "Cucumber",
            "Eggplant", "Espresso", "Fuze Tea", "Ground Beef", "Ground Coffee", "Ice Coffee", "Milk",
            "Milky", "Mushroom", "Onion", "Red Onion", "Salami", "Shampoo", "Soap", "Sprite", "Steak",
            "Tomato", "Toothbrush", "Toothpaste", "Turkish Coffee", "Yellow Cheese"
    };

    // Static list of groceries
    private static List<Groceries> groceryList = new ArrayList<>();

    // Static initializer block to populate the grocery list
    static {
        for (int i = 0; i < images.length; i++) {
            groceryList.add(new Groceries(images[i], names[i]));
        }
    }

    // Constructor
    public Groceries(Integer image, String name) {
        this.image = image;
        this.name = name;
    }

    // Getter methods
    public Integer getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    // Method to get the static list of groceries
    public static List<Groceries> getGroceryList() {
        return groceryList;
    }
}
