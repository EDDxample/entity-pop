
public class SeedTest {
    public static void main(String[] args) {

        long seed  = 0x0000_0000_0000_0000L; // nextFloat() = 0.0
        long seed1 = 0xFFFF_1999_99FF_FFFFL; // nextFloat() = 0.099999964

        System.out.println(SimplerJavaRandom.nextFloat(seed));
        System.out.println(SimplerJavaRandom.nextFloat(seed1));
    }
}

/**
 * NOTES:
 * 
 * The lcg seed for nextFloat() < 0.1
 * has its 25th to 48th bits in the
 * range [0, 0x199999)
 * 
 * EXAMPLES:
 * 0x_0000_0000_0000_0000
 * 0x_0000_1999_9900_0000
 * 
 * EXPLANATION:
 * 
 * - the seed gets masked to 48bits (so it ignores the top 16 bits)
 * - then next() does seed >>> 24 (destroying the bottom 24 bits)
 * - finally, the remaining 24 bits are divided by 2^24
 *   so to get 0.1f we need them to be lower than  2^24 / 10
 */
class SimplerJavaRandom {
    static int next(long seed, int bits) {
        //seed = seed * a + b & mask;
        seed &= 0xFFFF_FFFF_FFFFL; // 48 bit mask
        return (int) (seed >>> (48 - bits));
    }
    
    static float nextFloat(long seed) {
        return next(seed, 24) / (float) (1 << 24);
    }
}

class RealJavaRandom {
    
}