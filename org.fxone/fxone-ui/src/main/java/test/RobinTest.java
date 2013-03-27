package test;

import java.io.IOException;
import java.math.BigInteger;

public class RobinTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BigInteger zahl = new BigInteger("1");
 		BigInteger zwei = new BigInteger("2");
 		int i=1;
		while (true) {
			System.out.println(zahl);
			System.out.println("2^"+i++);
			System.out.println("-------------------------------------------");
			zahl = zahl.multiply(zwei);
			try{
				Thread.sleep(500L);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
