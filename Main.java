import java.util.List;

public class Main {

    public static void main(String[] args) {
        RNG rng = new RNG(544);

        long chunkX = 0;
        long chunkZ = 0;
        
        long blockX = chunkX << 4;
        long blockZ = chunkZ << 4;

        rng.setPopSeed(blockX, blockZ);

        List<SpawnEntry> list = BiomeSpawnLists.DESERT.spawnEntries;
        if (list.isEmpty()) return;

        float biomeMaxSpawnLimit = 0.1f; // 0.07f for IceSpickes, SnowyTundra and SnowyMountain
        
        while(rng.nextFloat() < biomeMaxSpawnLimit) {
            
            WeightedPicker.getRandom(rng, list);

            int x = (int)blockX + rng.nextInt(16);
            int z = (int)blockZ + rng.nextInt(16);
            
        }

    }
    
}