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
	public static void main(String[] args) throws IOException{
		int sum = 0;
		String[] myArr = {"0", "0", "0", "0"};
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		try {
			String line;
			int lenStr;

			while ((line = br.readLine()) != null) {
				int index = 3;
				String carry = "0";
				lenStr = line.length();
				while ((lenStr - 16) >= 0) {
					String subStr = String.valueOf(Long.parseLong(myArr[index]) + 
									Long.parseLong(line.substring(lenStr - 16, lenStr)) +
									Long.parseLong(carry));
					int lenSubStr = subStr.length();
					if (lenSubStr > 16) {
						carry = subStr.substring(0, 1);
						myArr[index--] = subStr.substring(1, lenSubStr);
					} else {
						myArr[index--] = subStr;
					}
					lenStr -= 16;
					//TODO: Do memory size check for carry over?
				}
				if (lenStr > 0) {
					myArr[index] = String.valueOf(Long.parseLong(myArr[index]) + 
									 Long.parseLong(line.substring(0, lenStr)));
				}
				System.out.println(myArr[0] + " " + myArr[1] + " " + myArr[2] + " " + myArr[3]);
			}
		} finally {
			br.close();
		}
		System.out.println(myArr[3]);
	}
}
