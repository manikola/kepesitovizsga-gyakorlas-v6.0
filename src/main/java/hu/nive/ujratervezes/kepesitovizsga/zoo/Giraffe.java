package hu.nive.ujratervezes.kepesitovizsga.zoo;

public class Giraffe extends ZooAnimal{

    public Giraffe(String name,int length) {
        super(name);
        setLength(length);
        setType(AnimalType.GIRAFFE);
    }
}
