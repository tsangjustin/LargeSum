/*******************************************************************************
 * Name        : LargeSum.java
 * Author      : Michael Curry, Daniel Heyman, and Justin Tsang
 * Version     : 1.0
 * Date        : 2/8/16
 * Description : Get first ten digits of sum of numbers from text file
 * Pledge      : I pledge my honor that I have abided by the Stevens Honor System
 ******************************************************************************/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;


public class LargeSum {

    public static Byte byteToByte(byte b){  // convert ASCII byte values into a Java Byte
        return (byte)(b-48);
    }

    public static void main(String[] args) throws IOException {
        Stack<Byte> digitStack = new Stack<Byte>();     // byte stack to hold all digits of the final answer
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        ArrayList<Byte[]> ByteArrays = new ArrayList<Byte[]>();     // holds an array of Bytes for each line

        String line;
        byte longest = 0;
        while ((line = br.readLine()) != null) {
            byte[] byteArray = line.getBytes();
            Byte[] ByteArray = new Byte[line.length()];
            for (int i = 0; i < byteArray.length; i++) {
                ByteArray[i] = byteToByte(byteArray[byteArray.length - (i+1)]);     // populate array with Byte for each number in line
            }
            ByteArrays.add(ByteArray);  // reversed array of bytes for the number
            if (line.length() > longest)
                longest = (byte)line.length();  // keep track of the longest line in the file
        }

        short carry = 0;
        short currDigit = 0;
        for (int i = 0; i < longest; i++){      // loop to iterate over numbers in the reversed line arrays
            currDigit = carry;
            for (Byte[] ByteArray : ByteArrays) {   // for each array in the ArrayList
                try {
                    currDigit += ByteArray[i];
                } catch (Exception e) {
                    // index out of bounds, do nothing
                }
            }
            // now we have a sum of all the digits in the 'i'th place (from right-to-left)
            carry = (short) (currDigit / 10);
            digitStack.push((byte) (currDigit %= 10));

        }
        while (carry != 0){ // take care of any leftover remainders
            currDigit = carry;
            if (carry < 10){
                digitStack.push((byte) (currDigit));
                carry = 0;
            }else {
                carry = (short) (currDigit / 10);
                digitStack.push((byte) (currDigit %= 10));
            }
        }
        System.out.print("Full sum: ");
        for (int i = digitStack.size()-1; i >= 0; i--){
            System.out.print(digitStack.get(i));
        }
        System.out.print("\nFirst 10 digits: ");
        for (int i = digitStack.size()-1; i >= digitStack.size()-10; i--){
            System.out.print(digitStack.get(i));
        }
    }

}
