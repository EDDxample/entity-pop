
/**
 * Thx to Matthew Bolan for the hints <3
 */

public class SeedTest {
    public static void main(String[] args) {

        long seed  = 0x0000_0000_0000_0000L; // nextFloat() = 0.0
        long seed1 = 0xFFFF_1999_99FF_FFFFL; // nextFloat() = 0.099999964
        
        SimplerJavaRandom.test(seed);
        SimplerJavaRandom.test(seed1);

        RealJavaRandom.test(seed);
        RealJavaRandom.test(seed1);
    }
}

/**
 * NOTES:
 * 
 * The lcg seed for nextFloat() < 0.1f
 * has its 25th to 48th bits in the
 * range [0, 0x199999]
 * 
 * EXAMPLES:
 * 0x_0000_0000_0000_0000
 * 0x_0000_1999_9900_0000
 * 0x_FFFF_1999_9900_FFFF
 * 
 * EXPLANATION:
 * 
 * - the seed gets masked to 48bits (so it ignores the top 16 bits)
 * - then next() does seed >>> 24 (destroying the bottom 24 bits)
 * - finally, the remaining 24 bits are divided by 2^24
 *   so to get 0.1f we need them to be lower than 2^24 / 10 (24bits < 0x199999)
 */
class SimplerJavaRandom {

    static void test(long seed) {
        System.out.println(nextFloat(seed));
    }

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

    static long 
    A = 0x5DEECE66DL,
    B = 0xBL,
    C = 0xFFFF_FFFF_FFFFL;

    static void test(long seed) {
        
        long Ai = 0xDFE05BCB1365L; // modular inverse of java random's multiplier

        seed = (seed - B) * Ai & C; // reverse the seed one LCG step
        seed ^= A;                  // reverse the XOR
        
        System.out.println(nextFloat(seed));                        // dissected java random
        System.out.println(new java.util.Random(seed).nextFloat()); // real java random
    }

    static long setSeed(long seed) {
        return (seed ^ A) & C;
    }

    static int next(long seed, int bits) {
        seed = seed * A + B & C;
        return (int) (seed >>> (48 - bits));
    }

    static float nextFloat(long seed) {
        seed = setSeed(seed);
        return next(seed, 24) / (float) (1 << 24);
    }
}