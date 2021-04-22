package hu.nive.ujratervezes.kepesitovizsga.zoo;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class Zoo {

    DataSource dataSource;
    List<ZooAnimal> animals = new ArrayList<>();

    public Zoo(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<ZooAnimal> getAnimals() {
        List<ZooAnimal> animals = new ArrayList<>(loadAnimals());
        return animals;
    }

    public List<ZooAnimal> loadAnimals() {
        List<ZooAnimal> animals = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from animals")
        ) {
            while (rs.next()) {
                String name = rs.getString("animal_name");
                int length = rs.getInt("length_of_member");
                long weight = rs.getLong("weight");
                AnimalType type = AnimalType.valueOf(rs.getString("animal_type"));
                ZooAnimal item = null;
                if (type == AnimalType.ELEPHANT) {
                    item = new Elephant(name, length, weight);
                }
                if (type == AnimalType.LION) {
                    item = new Lion(name);
                }
                if (type == AnimalType.GIRAFFE) {
                    item = new Giraffe(name, length);
                }
                if (!animals.contains(item)) {
                    animals.add(item);
                }
            }
            return animals;
        } catch (SQLException se) {
            throw new IllegalStateException("Cannot select animals", se);
        }
    }

    public void addAnimal(ZooAnimal animal) {
        List<ZooAnimal> animals = new ArrayList<>(loadAnimals());
        if (!animals.contains(animal)) {
            try (
                    Connection conn = dataSource.getConnection();
                    PreparedStatement stmt =
                            conn.prepareStatement("INSERT INTO animals(animal_name, length_of_member, weight, animal_type) VALUES (?, ?, ?, ?)")) {
                stmt.setString(1, animal.getName());
                stmt.setLong(2, animal.getLength());
                stmt.setLong(3, animal.getWeight());
                stmt.setString(4, animal.getType().toString());
                stmt.executeUpdate();
            } catch (SQLException se) {
                throw new IllegalStateException("Cannot insert", se);
            }
        }
    }

    public ZooAnimal getHeaviestAnimalInTheZoo() {
        List<ZooAnimal> animals = new ArrayList<>(getAnimals());
        ZooAnimal max = animals.get(0);
        for (ZooAnimal item : animals) {
            if (item.getWeight() > max.getWeight()) {
                max = item;
            }
        }
        return max;
    }

    public int countWeights() {
        List<ZooAnimal> animals = new ArrayList<>(getAnimals());
        int sum = 0;
        for (ZooAnimal a : animals) {
            sum += a.getWeight();
        }
        return sum;
    }

    public ZooAnimal findExactAnimal(ZooAnimal animal) {
        List<ZooAnimal> animals = new ArrayList<>(getAnimals());
        for (ZooAnimal item : animals) {
            if (item.equals(animal)) {
                return item;
            }
        }
        throw new IllegalArgumentException("There is no such animal in the zoo!");


    }

    public ZooAnimal findExactAnimalByName(String name) {
        List<ZooAnimal> animals = new ArrayList<>(getAnimals());
        for (ZooAnimal item : animals) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        throw new IllegalArgumentException("There is no such animal in the zoo!");

    }

    public int getNumberOfAnimals() {
        List<ZooAnimal> animals = new ArrayList<>(getAnimals());
        return animals.size();
    }

    public List<ZooAnimal> getAnimalsOrderedByName() {
        List<ZooAnimal> animals = new ArrayList<>(getAnimals());
        animals.sort(Comparator.comparing(ZooAnimal::getName));
        return animals;
    }

    public Map<AnimalType, Integer> getAnimalStatistics() {
        Map<AnimalType, Integer> results = new HashMap<>();

        for (AnimalType item : AnimalType.values()) {
            int count = 0;
            for (ZooAnimal actual : getAnimals()) {
                if (actual.getType().toString().equals(item.toString())) {
                    count++;
                }
                results.put(item, count);
            }
        }
        return results;
    }



}


