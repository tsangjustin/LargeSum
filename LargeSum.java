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
		String[] myArr = {"0", "0", "0", "0"};
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		try {
			String line;
			while ((line = br.readLine()) != null) {
				int lenStr = line.length();
				int index = 3;
				String carry = "0";
				while ((lenStr - 14) >= 0) {
					String subStr = String.valueOf(Long.parseLong(myArr[index]) + 
									Long.parseLong(line.substring(lenStr - 14 , lenStr)) + 
									Integer.parseInt(carry));
					//System.out.println(myArr[index] + " " + line.substring(lenStr - 14 , lenStr) + " " + carry);
					if (index > 0) {
						//int lenSubStr = subStr.length();
						if (subStr.length() > 14) {
							//System.out.println(subStr.length());
							carry = "1";
							myArr[index] = subStr.substring(1, 15);
						} else {
							carry = "0";
							myArr[index] = subStr;
							//System.out.println(subStr.length());
						}
						index--;
					} else {
						myArr[index] = subStr;
					}
					lenStr -= 14;
					//TODO: Do memory size check for carry over?
				}
				if (lenStr > 0) {
					myArr[index] = String.valueOf(Long.parseLong(myArr[index]) + 
								   Long.parseLong(line.substring(0, lenStr)) + 
								   Integer.parseInt(carry));
					//System.out.println(myArr[index] + " " + line.substring(0, lenStr) + " " + carry);
				}
				//System.out.println(myArr[0] + " " + myArr[1] + " " + myArr[2] + " " + myArr[3]);
			}
		} finally {
			br.close();
		}
		String sumStr = "";
		for (int i = 0; i < 4; i++) {
			if (myArr[i] != "0") {
				sumStr += myArr[i];
			}
		}
		System.out.println("Full sum: " + sumStr);
		int lenSumStr = sumStr.length();
		if (lenSumStr > 10) {	
			System.out.println("First 10 digits: " + sumStr.substring(0, 10));
		} else {
			System.out.println("First " + lenSumStr + " digits: " + 
								sumStr.substring(0, lenSumStr));
		}
	}
}