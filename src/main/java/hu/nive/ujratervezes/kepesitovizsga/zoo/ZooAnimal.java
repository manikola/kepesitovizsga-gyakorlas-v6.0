package hu.nive.ujratervezes.kepesitovizsga.zoo;

import java.util.Objects;

public class ZooAnimal {

    protected String name;
    protected int length;
    protected long weight;
    protected AnimalType type;

    public ZooAnimal(String name) {
        this.name = name;
    }

    public ZooAnimal(String name, int length, long weight, AnimalType type) {
        this.name = name;
        this.length = length;
        this.weight = weight;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public long getWeight() {
        return weight;
    }

    public AnimalType getType() {
        return type;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public void setType(AnimalType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ZooAnimal{" +
                "name='" + name + '\'' +
                ", length=" + length +
                ", weight=" + weight +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ZooAnimal)) return false;
        ZooAnimal zooAnimal = (ZooAnimal) o;
        return getLength() == zooAnimal.getLength() && getWeight() == zooAnimal.getWeight() && Objects.equals(getName(), zooAnimal.getName()) && Objects.equals(getType(), zooAnimal.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLength(), getWeight(), getType());
    }
}
