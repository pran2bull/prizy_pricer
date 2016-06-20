package org.prizy.pricer.util;

import java.util.Random;

public class CommonUtil {

	public static String generateRandomNumber() {
        Random rand = new Random();
        long accumulator = 1 + rand.nextInt(9);
        for (int i = 0; i < 5; i++) {
            accumulator *= 10L;
            accumulator += rand.nextInt(10);
        }
        return accumulator + "";
    }
}
