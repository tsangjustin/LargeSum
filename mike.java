import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;


public class Main {

    public static Byte byteToByte(byte b){  // convert ASCII byte values into a Java Byte
        return (byte)(b-48);
    }

    public static void main(String[] args) throws IOException {

        Stack<Byte> digitStack = new Stack<Byte>();
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        ArrayList<String> lines = new ArrayList<String>();
        ArrayList<Byte[]> ByteArrays = new ArrayList<Byte[]>();

        String line;
        byte longest = 0;
        while ((line = br.readLine()) != null) {
            byte[] byteArray = line.getBytes();
            Byte[] ByteArray = new Byte[line.length()];
            for (int i = 0; i < byteArray.length; i++) {
                ByteArray[i] = byteToByte(byteArray[byteArray.length - (i+1)]);
            }
            ByteArrays.add(ByteArray);  // reversed array of bytes for the number
            if (line.length() > longest)
                longest = (byte)line.length();
        }

        short carry = 0;
        short currDigit = 0;
        for (int i = 0; i < longest; i++){
            currDigit = carry;
            for (Byte[] ByteArray : ByteArrays) {
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
        while (carry != 0){ // carry any leftover remainder
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
