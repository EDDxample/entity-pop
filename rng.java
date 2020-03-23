import java.util.Random;

public class RNG extends Random {
    long worldSeed;

    public RNG(final long worldSeed) {
        this.worldSeed = worldSeed;
    }

    public void setPopSeed(final long blockX, final long blockZ) {
        this.setSeed(this.worldSeed);
        final long a = this.nextLong() | 1L;
        final long b = this.nextLong() | 1L;
        this.setSeed(blockX * a + blockZ * b ^ this.worldSeed);
     }
}

