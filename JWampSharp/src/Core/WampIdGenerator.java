package Core;

import java.util.Random;

/**
 * Created by Elad on 16/04/2014.
 */
public class WampIdGenerator {
    private final Random random = new Random();
    private final long limit = (long)Math.pow(2, 50);

    public long generate() {
        return nextLong(this.random, this.limit);
    }

    private long nextLong(Random rng, long n) {
        // error checking and 2^x checking removed for simplicity.
        long bits, val;
        do {
            bits = (rng.nextLong() << 1) >>> 1;
            val = bits % n;
        } while (bits - val + (n - 1) < 0L);
        return val;
    }
}