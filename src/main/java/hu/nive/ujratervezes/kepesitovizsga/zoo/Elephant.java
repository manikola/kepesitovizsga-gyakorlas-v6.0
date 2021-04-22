package hu.nive.ujratervezes.kepesitovizsga.zoo;

public class Elephant extends ZooAnimal{

    public Elephant(String name, int length, long weight) {
        super(name);
        setType(AnimalType.ELEPHANT);
        setLength(length);
        setWeight(weight);
    }
}
