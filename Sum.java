public class Sum {

	public static final short SPLIT = 17;

	public static String addLargeNumbers(String first, String second) {
		String result = "";
		if(first == "0")
			return second;
		if(second == "0")
			return first;
	    short carry = 0, firstLength = (short) first.length(), secondLength = (short) second.length();
	    short max = (short) Math.max(firstLength, secondLength);
	    short min = (short) Math.min(firstLength, secondLength);
	    for(int i = 0; i < Math.ceil((float) min / SPLIT); ++i) {
      		int pos = i * SPLIT, pos2 = (i + 1) * SPLIT;
	      	String firstpart = first.substring(Math.max(0, firstLength - pos2), firstLength - pos);
	      	String secondpart = second.substring(Math.max(0, secondLength - pos2), secondLength - pos);
	      	long sum = Long.parseLong(firstpart) + Long.parseLong(secondpart) + carry;
	      	String part = String.valueOf(sum);
	      	if(part.length() > SPLIT) {
	      		carry = Short.parseShort(part.substring(0, 1));
	      		part = part.substring(1, SPLIT);
	      	}
	      	result += part;
	    }
	    if(max / SPLIT != min / SPLIT) {
	    	int end = (int) (Math.ceil((float) min / SPLIT) * SPLIT);
	    	if(firstLength > secondLength)
	    		result = addLargeNumbers(String.valueOf(carry), first.substring(0, firstLength - end)) + result;
	    	else
	    		result = addLargeNumbers(String.valueOf(carry), second.substring(0, secondLength - end)) + result;
	    }
	    return result;
	}

	public static void main(String [ ] args) {
	    String first = "37107287533902102798797998220837590246510135740250";
	    String second = "46376937677490009712648124896970078050417018260538";
	      
	    System.out.println(addLargeNumbers(first, second));
	}
}