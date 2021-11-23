package simulation;

import java.util.*;
import java.util.stream.Collectors;

public class WorldMap extends AbstractWorldMap {
    private static final int ANIMALS_NR  = 20, PLANTS_NR = 100;
    private static final int ANIMAL_ENERGY = 20;
    private static final int PLANT_ENERGY = 10;

    private List <Animal> animals = new ArrayList<>();
    private Map <Vector2D, List<Animal>> animalsPositions = new HashMap<>();
    private Map <Vector2D, Plant> plants = new HashMap<>();
    private Random random = new Random();
    private int dayNumber = 1;

    public WorldMap(int width, int height) {
        super(width, height);
        for(int i=0; i<ANIMALS_NR; i++){
            Animal animal = new Animal(getRandomVector(), ANIMAL_ENERGY);
            addNewAnimal(animal);
        }
        for(int i=0; i<PLANTS_NR; i++){
            addNewPlant();
        }

    }



    private Vector2D getRandomPosition(){
        return new Vector2D(random.nextInt(width), random.nextInt(height) );
    }

    private void placePlantOnMap(){
        Vector2D positon = getRandomVector();
        while(isOccupiedByPlant(positon)) positon = getRandomVector();
        plants.put(positon, new Plant(positon));
    }

    private Vector2D getRandomVector(){
        return new Vector2D(random.nextInt(getWidth()), random.nextInt(getHeight()) );
    }

    private boolean isOccupiedByPlant(Vector2D position){
        return getPlantAtPosition(position) != null;
    }

    private Plant getPlantAtPosition(Vector2D position){
        return plants.get(position);
    }

    @Override
    public void run() {
        System.out.println("Today is day number: " + dayNumber);
        animalsPositions.clear();
        animals.forEach(animal -> {
            animal.moveBasedOnGenome();
            placeAnimalOnMap(animal);
        }) ;
    }

    private void placeAnimalOnMap(Animal animal){
        animalsPositions.computeIfAbsent(animal.getPosition(), k->new LinkedList<>()).add(animal);
    }

    public void eat(){
            animalsPositions.forEach((position, animals) -> {
                if(isOccupiedByPlant(position)){
                    animals.stream().max(Animal::compareTo).ifPresent(this::eatPlant);

                }
            });
    }

    private  void eatPlant(Animal animal){

        plants.remove(animal.getPosition());
        animal.setEnergy(animal.getEnergy() + PLANT_ENERGY);
        placePlantOnMap();
    }

    private void eatPlantAtPosition(Vector2D position) {
        Plant plant = getPlantAtPosition(position);
        if (plant != null) {
            this.plants.remove(plant.getPosition());
            System.out.println("Animal ate plant at position " + plant.getPosition());
            addNewPlant();
        }
    }
    private void addNewPlant() {
        Vector2D pos = getRandomVector();
        while (isOccupiedByPlant(pos)) pos = getRandomVector();
        plants.put(pos, new Plant(pos));
    }
    @Override
    public void atTheEndOfDay(){
        dayNumber ++;
        animals = animals.stream()
                .map(Animal::aging)
                .map(animal -> animal.setEnergy(animal.getEnergy() - ANIMAL_ENERGY /2))
                .filter(animal -> animal.getEnergy() > 0)
                .collect(Collectors.toList());


    }
    @Override
    public void reproduce(){
        List<Animal> children = new LinkedList<>();
        animalsPositions.forEach((position, animals) -> {
           List<Animal> parents = animals.stream()
                    .filter(a -> a.getEnergy() > ANIMAL_ENERGY /2)
                    .sorted(Collections.reverseOrder())
                    .limit(2)
                    .collect(Collectors.toList());
           if (parents.size() == 2){
               Animal child = new Animal(parents.get(0), parents.get(1));
               System.out.println("Animal" + child.getAnimalID() + "was born on position: " + position );
               children.add(child);
           }
        });
        children.forEach(this::addNewAnimal);

    }

    private void addNewAnimal(Animal animal){
        animals.add(animal);
        placeAnimalOnMap(animal);
    }
}