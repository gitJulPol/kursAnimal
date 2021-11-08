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
        this.random = new Random();
    }

    private Vector2D getRandomPosition(){
        return new Vector2D(random.nextInt(getWidth()), random.nextInt(getHeight()) );
    }

    @Override
    public void run() {
        for (Animal animal : animals) {
            animal.move(MapDirection.values()[this.random.nextInt(MapDirection.values().length)], width, height);
        }
    }
}