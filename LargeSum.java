/*******************************************************************************
 * Name        : LargeSum.java
 * Author      : Michael Curry, Daniel Heyman, and Justin Tsang
 * Version     : 1.0
 * Date        : 2/8/16
 * Description : Get first ten digits of sum of numbers from text file
 * Pledge      : I pledge my honor that I have abided by the Stevens Honor System
 ******************************************************************************/
import java.io.*;

public class LargeSum {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		int sum = 0;
		try {
			String line = br.readLine();
			StringBuilder sb = new StringBuilder();

			while (line != null) {
				line = br.readLine();
			}
			System.out.println(sb.toString());
		} finally {
			br.close();
		}
	}
}
