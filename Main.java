
public class Main {

    public static void main(String[] args) {
        RNG rng = new RNG(544);

        long chunkX = 0;
        long chunkZ = 0;
        
        long blockX = chunkX << 4;
        long blockZ = chunkZ << 4;

        rng.setPopSeed(blockX, blockZ);

        // list = biome.getEntitySpawnList(CREATURE);
        // if list not empty:

        float biomeMaxSpawnLimit = 0.1f; // varies per biome
        
        while(rng.nextFloat() < biomeMaxSpawnLimit) {
            
            WeightedPicker.getRandom(rng, list);

            int x = (int)blockX + rng.nextInt(16);
            int z = (int)blockZ + rng.nextInt(16);
            
        }

    }
    
}