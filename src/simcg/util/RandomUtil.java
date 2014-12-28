package simcg.util;

public class RandomUtil {
	public static double nextDouble(double min, double max) {
		double diff = max - min;

		return Math.random() * diff + min;
	}

}
