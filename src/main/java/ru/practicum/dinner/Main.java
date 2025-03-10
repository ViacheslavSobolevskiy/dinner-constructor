package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static DinnerConstructor dc;
    static Scanner scanner;

    public static void main(String[] args) {
        dc = new DinnerConstructor();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    if (dc.menu.isEmpty()) {
                        System.out.println("Добавьте хотя бы одно блюдо.");
                        break;
                    }
                    generateDishCombo();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Неверная команда.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();

        // добавьте новое блюдо
        dc.addDish(dishType, dishName);
    }

    private static void generateDishCombo() {
        var typeList = new ArrayList<String>();
        int numberOfCombos = 0;

        System.out.println("Начинаем конструировать обед...");
        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");
        String nextItem = scanner.nextLine();

        //реализуйте ввод типов блюд
        while (!nextItem.isEmpty()) {
            if (dc.checkType(nextItem)) {
                typeList.add(nextItem);
            } else {
                System.out.println("Нет такого типа блюда. Выберите из списка: " + dc.menu.keySet());
            }
            nextItem = scanner.nextLine();
        }

        while (true) {
            System.out.println("Введите количество наборов, которые нужно сгенерировать:");

            try {
                numberOfCombos = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Введите число!");
                continue;
            }

            if (numberOfCombos <= 0) {
                System.out.println("Введите целое число больше 0!");
                continue;
            }

            if (!dc.checkMaxCombinations(numberOfCombos, typeList)) {
                System.out.println("Введите меньшее количество наборов!");
                continue;
            }

            break;
        }
        scanner.nextLine();

        // сгенерируйте комбинации блюд и выведите на экран
        dc.createMenuCombinations(numberOfCombos, typeList);
    }
}
