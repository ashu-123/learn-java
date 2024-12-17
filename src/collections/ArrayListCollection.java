package collections;

import model.Animal;
import model.Dog;

import java.util.ArrayList;
import java.util.List;

public class ArrayListCollection {

    public static void main(String[] args) {

        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Dog> dogs = new ArrayList<>();

        animals.add(new Animal());
        animals.add(new Dog());
        dogs.add(new Dog());

        printList(animals);
        printList(dogs);

        Animal shanky = new Animal();
        shanky.setName("shanky");
        var animals2 = List.of(shanky);

        animals2.stream().map(Animal::getName).forEach(System.out::println);
        shanky.setName("sneaky");
        animals2.stream().map(Animal::getName).forEach(System.out::println);

    }

    private static void printList(ArrayList<? extends Animal> animals) {
        animals.forEach(Animal::sound);
    }
}
