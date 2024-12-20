package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import use_case.animalDatatAccess.AnimalDataAccessInteractor;

/**
 * The Animal Farm in the program.
 */
public class AnimalFarm {
    private List<Animal> currentAnimals;
    private List<Animal> selectedAnimals;
    private Random rand = new Random();
    private List<String> availableAnimals;

    public AnimalFarm(String[] availableAnimals) {
        this.availableAnimals = Arrays.asList(availableAnimals);
        this.selectedAnimals = new ArrayList<>();
        this.currentAnimals = new ArrayList<Animal>();

        for (String animalsSpecies : this.availableAnimals) {
            final Map<String, List> listOfAnimalSameSpecies =
                    AnimalDataAccessInteractor.getAnimal(animalsSpecies.toLowerCase());
            for (String animalName : listOfAnimalSameSpecies.keySet()) {
                if (animalName != null) {
                    final Animal tempAnimal = new Animal(animalName, animalsSpecies,
                            (String) listOfAnimalSameSpecies.get(animalName).get(2),
                            (List<String>) listOfAnimalSameSpecies.get(animalName).get(0),
                            (String) listOfAnimalSameSpecies.get(animalName).get(1));

                    selectedAnimals.add(tempAnimal);
                }
                else {
                    System.err.println("Animal not found or family does not match: " + animalName);
                }
            }
        }
    }

    /**
     * Add a random animal from the selected animals.
     */
    public void addAnimal() {
        if (!selectedAnimals.isEmpty()) {
            final int index = rand.nextInt(selectedAnimals.size());
            final Animal animalToAdd = selectedAnimals.get(index);
            currentAnimals.add(animalToAdd);
        }
    }

    /**
     * Remove specific animals from the farm.
     * @param removeCount the number of animals to remove
     */
    public void removeAnimal(int removeCount) {
        int i = 0;
        while (!currentAnimals.isEmpty() && i < removeCount) {
            currentAnimals.remove(0);
            i++;
        }
    }

    public List<Animal> getCurrentAnimals() {
        return currentAnimals;
    }
}
