package ru.practicum.dinner;

import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class DinnerConstructor {
    public HashMap<String, ArrayList<String>> menu; // Меню

    public DinnerConstructor() {
        menu = new HashMap<String, ArrayList<String>>();
    }

    public void addDish(String dishType, String dishName) {
        for (String type : menu.keySet()) {
            if (menu.get(type).contains(dishName)) {
                System.out.println("Блюдо " + dishName + " уже присутствует в категории " + type + ".");
                return;
            }
        }
        if (!menu.containsKey(dishType))
            menu.put(dishType, new ArrayList<>(List.of(dishName)));
        else
            menu.get(dishType).add(dishName);
        System.out.println("Блюдо " + dishName + " добавлено в категорию " + dishType + ".");
    }

    public boolean checkType(String type) {
        return menu.containsKey(type);
    }

    private ArrayList<String> getNewCombination(@NotNull List<String> types) {
        ArrayList<String> newCombination = new ArrayList<>();
        for (String type : types) {
            val rand = new Random().nextInt(menu.get(type).size());
            newCombination.add(menu.get(type).get(rand));
        }
        return newCombination;
    }

    private boolean checkUnique(@NotNull Map<Integer, ArrayList<String>> combinations,
                                @NotNull List<String> combination) {
        for (ArrayList<String> combo : combinations.values()) {
            if (combo.equals(combination))
                return false;
        }
        return true;
    }

    public boolean checkMaxCombinations(int numberOfCombos, @NotNull List<String> types) {
        int maxCombinations = 1;

        for (var type : types)
            maxCombinations *= menu.get(type).size();

        if (numberOfCombos > maxCombinations) {
            System.out.println("Количество комбинаций больше возможного: " + maxCombinations + ".");
            return false; // Требуется заново ввести число комбинаций
        }
        return true;
    }

    public void createMenuCombinations(int numberOfCombos, @NotNull List<String> types) {
        Map<Integer, ArrayList<String>> combinations = new HashMap<>();


        for (int i = 0; i < numberOfCombos; i++) {
            ArrayList<String> newCombination = getNewCombination(types);
            while (!checkUnique(combinations, newCombination)) {
                newCombination = getNewCombination(types);
            }
            combinations.put(i, newCombination);
        }

        for (var combo : combinations.entrySet()) {
            System.out.println("Комбо " + (combo.getKey() + 1));
            System.out.println(combo.getValue());
        }

        types.clear();
    }
}