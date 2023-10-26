package by.clevertec.comporators;

import by.clevertec.model.Animal;

import java.util.Comparator;

public class AnimalAgeComparator implements Comparator<Animal> {
        public int compare(Animal a, Animal b){

            return Integer.compare(a.getAge(), b.getAge());
        }
}
