package simulation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class WorldMap extends AbstractWorldMap {
    private static final int ANIMALS_NR  = 15, PLANTS_NR = 100;
    private ArrayList <Animal> animals = new ArrayList<>();
    private LinkedList <Plant> plants = new LinkedList<>();
    private Random random;

    public WorldMap(int width, int height) {
        super(width, height);
        random = new Random();
        for(int i=0; i<ANIMALS_NR; i++){
            animals.add(new Animal(getRandomPosition()));
        }
        for(int i=0; i<PLANTS_NR; i++){
            placePlantOnMap();
        }

    }

    private Vector2D getRandomPosition(){
        return new Vector2D(random.nextInt(getWidth()), random.nextInt(getHeight()) );
    }

    private void placePlantOnMap(){
        Vector2D positon = getRandomVector();
        while(isOccupiedByPlant(positon)) positon = getRandomVector();
        plants.add(new Plant(positon));
    }

    private Vector2D getRandomVector(){
        return new Vector2D(random.nextInt(getWidth()), random.nextInt(getHeight()) );
    }

    private boolean isOccupiedByPlant(Vector2D position){
        return getPlantAtPosition(position) != null;
    }

    private Plant getPlantAtPosition(Vector2D position){
        for(Plant plant : plants){
            if(plant.getPosition().equals(position)) return plant;
        }
        return null;
    }

    @Override
    public void run() {
        for (Animal animal : animals) {
            animal.move(MapDirection.values()[this.random.nextInt(MapDirection.values().length)], width, height);
        }
    }

    public void eat(){
        for(Animal animal : animals){
            Plant plant = getPlantAtPosition(animal.getPosition());
            if(plant != null){
                System.out.println("animal eat plant");
                plants.remove(plant);
                placePlantOnMap();
            }
        }
    }
}