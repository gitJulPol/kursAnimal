package simulation;

public record SimulationStatistics (
        int noOfAnimals,
        int noOfPlants,
        double meanLifeLength,
        double meanNumberOfChildren,
        double meanEnergy,
        int dayNumber
) {
    @Override
    public String toString(){
        return "Number of Animals: " + noOfAnimals +
                "\nNumber of Plants: " + noOfPlants +
                "\nMeanAge: " + formatNumber(meanLifeLength) +
                "\nMean number of Children: " + formatNumber(meanNumberOfChildren) +
                "\nMean Energy: " + formatNumber(meanEnergy) +
                "\nDay Number: " + dayNumber + "\n";
    }

    private String formatNumber(double number){
        return String.format("%.2f", number);
    }
}