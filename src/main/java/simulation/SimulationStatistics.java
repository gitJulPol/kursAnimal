package simulation;

public record SimulationStatistics(
        int dayNumber,
        double meanLifeLength,
        double meanNumberOfChildren,
        double meanEnergy,
        int noOfAnimals,
        int noOfPlants
) { }
