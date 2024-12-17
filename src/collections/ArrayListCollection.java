package collections;

import model.Animal;
import model.Dog;

import java.util.ArrayList;

public class ArrayListCollection {

    public static void main(String[] args) {

        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Dog> dogs = new ArrayList<>();

        animals.add(new Animal());
        animals.add(new Dog());
        dogs.add(new Dog());

        printList(animals);
        printList(dogs);
    }

    private static void printList(ArrayList<? extends Animal> animals) {
        animals.forEach(Animal::sound);
    }
}
