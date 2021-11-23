package simulation;

public class SimulationStatistics {
    private final int dayNumber;
    private final double meanLifeLength;
    private final double meanChildrenNumber;
    private  final double meanEnergy;
    private final int AnimalsNo;
    private final int PlantsNo;

    public SimulationStatistics(
            int dayNumber,
            double meanLifeLength,
            double meanChildrenNumber,
            double meanEnergy,
            int AnimalsNo,
            int PlantsNo)
    {   this.dayNumber = dayNumber;
        this.meanLifeLength = meanLifeLength;
        this.meanChildrenNumber = meanChildrenNumber;
        this.meanEnergy = meanEnergy;
        this.AnimalsNo = AnimalsNo ;
        this.PlantsNo = PlantsNo;

    }
}
