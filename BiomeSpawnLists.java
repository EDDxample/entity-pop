import java.util.List;
import java.util.Arrays;

enum BiomeSpawnLists {

    DESERT(Arrays.asList(new SpawnEntry("RABBIT", 4, 2, 3)));

    public List<SpawnEntry> spawnEntries;

    private BiomeSpawnLists(List<SpawnEntry> entries) { 
        this.spawnEntries = entries; 
    }
}


class SpawnEntry extends WeightedPicker.Entry {
    public final String type;
    public final int minGroupSize;
    public final int maxGroupSize;

    public SpawnEntry(String type, int weight, int minGroupSize, int maxGroupSize) {
        super(weight);
        this.type = type;
        this.minGroupSize = minGroupSize;
        this.maxGroupSize = maxGroupSize;
    }
}