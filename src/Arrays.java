//practice and notes for coding interview questions
//topic: arrays and hash tables
//main only calls the methods
//see each method for the question and the notes

public class Arrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Q1
		System.out.println("All unique characters?");
		System.out.println(isUniqueChars("gabrielle"));  //false
		System.out.println(isUniqueChars("abcdefg"));  //true
		System.out.println();
		
		//Q2
		System.out.println("Permutation:");
		System.out.println(permutation1("Gab", "bag"));  //false
		System.out.println(permutation1("gab", "bag"));  //true
		System.out.println(permutation1("gab", "bag "));  //false
		System.out.println();

		
		//Q3
		//when you feel like it, write a way to call the method and get a result
		
		//Q4
		System.out.println("Permutation of palindrome:");
		System.out.println(isPermutationOfPalindrome("wobbly jogger passerby")); //false
		System.out.println(isPermutationOfPalindrome("aabbccddeeffgg")); //true
		System.out.println();
		
		//Q5
		System.out.println("One away:");
		System.out.println(oneEditAway("rare", "rarer")); //true
		System.out.println(oneEditAway("rare", "rar")); //true
		System.out.println(oneEditAway("rare", "rore")); //true
		System.out.println(oneEditAway("rare", "pink")); //false
		System.out.println();
		
		//Q6
		System.out.println("Compress string:");
		System.out.println(compressFirst("aaabbbccdddd")); //a3b3c2d4
		System.out.println(compressFirst("abcdefg")); //abcdefg (because it will get longer if we "compress")
		System.out.println(compressWithSB("aaabbbccdddd")); //a3b3c2d4
		System.out.println(compressWithSB("abcdefg")); //abcdefg (because it will get longer if we "compress")


	}
	
	
	 //Q1 does a string have all unique characters?
	
	//pre-problem tips: is the base alphabet limited to a # of characters?
	//if so, string.length > alphabet.length = false
	//if we don't know base, compare each char to each other char
	
	static boolean isUniqueChars(String str) {
		if (str.length() > 128) return false;
		//that's if we know length of underlying alphabet/char set is 128
		//the # will change depending on what you're working with
		
		boolean[] char_set = new boolean[128];
		for (int i=0; i < str.length(); i++) {
			int val = str.charAt(i);
			if (char_set[val]) {
				return false;
			}
			char_set[val] = true;
		}
		return true;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////
	
	//Q2: Is one string a permutation of another?
	
	//pre-problem tips: learn if capitalization and spaces count.
	//ie, Gab = bag?
	//if spaces matter, str1.length != str2.length = false
	
	//two main ways to solve: (1)sort then compare, or
	//(2) RE-READ THIS IN YOUR TEXTBOOK because you don't understand it.
	
	//solution 1:
	
	static String sort1(String s) {
		char[] content = s.toCharArray();
		java.util.Arrays.sort(content);
		return new String(content);
	}
	
	static boolean permutation1(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		return sort1(s).equals(sort1(t));
	}
	
	//solution 2:
	//TO DO
	
	///////////////////////////////////////////////////////////////////////////////////////////

	
	//Q3: URLify: replace all spaces in a string with "%20"
	
	//tips: this is good to work from the end to the start
	//treat the string as an array of chars
	//go through array twice: once to count spaces (so you know total length of new string)
	//next to replace characters
		
	//fyi "true length" of a string means ignoring EXTRA while space
	//like "Mr John Smith     " is 13. pass that as trueLength
	
	public void replaceSpaces (char[] str, int trueLength) {
		//int spaceCount = 0, index, i = 0;
		int spaceCount = 0;
		int index;
		int i = 0;
		
		//count spaces:
		for (i = 0; i < trueLength; i++) {
			if (str[i] == ' ') {
				spaceCount++;
			}
		}
		
		index = trueLength + spaceCount * 2; //adds true length plus extra room for new chars
		
		if (trueLength <str.length) {
			str[trueLength] = '\0'; //that will end the array
		}
		
		//this part will go backwards through the now-shortened string
		//starts with last char and loops
		//it decrements i while counting the original string
		//and index when "creating" the new string with %20 inserted
		//with each char, it replaces (or not) and converts from str[i] to str[index]
		for (i = trueLength - 1; i>= 0; i--) {
			if (str[i] == ' ') {
				str[index - 1] = '0';
				str[index - 2] = '2';
				str[index - 3] = '%';
				index = index -3;
			}
			else {
				str[index - 1] = str[i];
				index--;
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////

	
	//Q4 check whether a string is a permutation of a palindrome
	
	//tips: understand both of those terms.
	//palindrome: contains pairs of matching letters (with possibly ONE nonmatch)
	//inefficient: creating all possible permutations and checking if palindromes. don't do it.
	//hash table: imagine you had to count a whole paragraph's worth of letters
	//you'd do something like write all the letters then make hashmarks for each instance.
	//that's basically what it is.
	
	//solution 1:
	
	//build the hash table:
	public static boolean isPermutationOfPalindrome(String phrase) {
		int table[] = buildCharFrequencyTable(phrase);
		return checkMaxOneOdd(table);
	}
	
	//this checks that zero or one chars have an odd count
	//it allows for up to one instance of %2 == 1 to be true
	public static boolean checkMaxOneOdd(int[] table) {
		boolean foundOdd = false;
		for (int count: table) {
			if (count % 2 == 1) {
				if (foundOdd) {
					return false;
				}
				foundOdd=true;
			}
		}
		return true;
	}
	
	//this maps each character to a number; (ie, a is 0, b is 1)
	//-1 means a non-letter character
	//Character.getNumbericValue is a java method that knows what to do.
	public static int getCharNumber(Character c) {
		int a = Character.getNumericValue('a');
		int z = Character.getNumericValue('z');
		int val = Character.getNumericValue(c);
		
		if (a <=val && val <= z) {
			return val - a;
		}
		return -1;
	}
	
	//count how many times each character appears
	//basically, it loops through each char in the table and ++ each time
	public static int[] buildCharFrequencyTable(String phrase) {
		int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') +1];
		for (char c: phrase.toCharArray()) {
			int x = getCharNumber(c);
			if (x != -1) {
				table[x]++;
			}
		}
		return table;
	}
	
	//solution 2 is not much different but more efficient
	//solution 3 involves bitvectors, which I'm gonna skip for a while
	
	///////////////////////////////////////////////////////////////////////////////////////////

	
	//Q5: "One away": Is one string just one alteration away from being another string?
	//ie, will inserting or removing or changing a character make one string into the other string?
	
	//the inefficient way would be to compare the second string with every permutation of
	//inserting, removing or replacing in the first string. too long. don't do it.
	
	//tip for thinking about it: in all 3 ways, the strings are different by only one.
	//either outright one thing is different, or two string are the same but shifted by one.
	
	//checking if lengths are the same or differ by only one:
	//if so, sends the strings to the next method
	public static boolean oneEditAway(String first, String second) {
		if(first.length() == second.length()) {
			return oneEditReplace(first, second);
		}
		else if(first.length() +1 == second.length()) {
			return oneEditInsert(first, second);
		}
		else if(first.length() -1 == second.length()) {
			return oneEditInsert(second, first);
			//see how that will save us writing?
			//it's the same method for insert or delete
			//with the arguments reversed.
		}
		return false;
	}
	
	//if strings are same length:
	public static boolean oneEditReplace(String s1, String s2) {
		boolean foundDifference = false;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				if(foundDifference) {
					return false;
				}
				foundDifference = true;
			}
		}
		return true;
	}
	
	//can inserting one character make s1 one away from s2?
	public static boolean oneEditInsert(String s1, String s2) {
		int index1 = 0;
		int index2 = 0;
		while (index2 < s2.length() && index1 < s1.length()) {
			if (s1.charAt(index1) != s2.charAt(index2)) {
				if (index1 != index2) {
					return false;
				}
				index2++;
			}
			else {
				index1++;
				index2++;
			}
		}
		return true;
	}
	
	//those two "oneEditX" methods can be merged into one
	//maybe try it sometime when you want to tinker.
	
	///////////////////////////////////////////////////////////////////////////////////////////

	
	//Q6: Compress a string using counts of repeating chars
	//Ie: aaabbccccdddd --> a3b2c4c4
	
	//Tips: The logic is pretty straightforward, but there are multiple
	//ways to do it, and change O.
	
	//one way, if you don't know StringBuilder. This is slow because string concat is slow.
	public static String compressFirst(String str) {
		String compressedString = ""; //this is where the counted chars go
		int countConsecutive = 0;
		for (int i = 0; i < str.length(); i++) {
			countConsecutive++; //this will always rise to at least one per letter
			
			//check if next letter if new. if so, add last letter and its number to end of new string
			if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
				compressedString += "" + str.charAt(i) + countConsecutive;
				countConsecutive = 0; //resets char counter
			}
		}
		return compressedString.length() < str.length() ? compressedString : str;
	}
	
	//another way, using StringBuilder.
	public static String compressWithSB(String str) {
		StringBuilder compressed = new StringBuilder(); //create new SB instead of new string
		int countConsecutive = 0;
		for (int i = 0; i < str.length(); i++) {
			countConsecutive++;
			
			//check if next letter if new. if so, add last letter and its number to end of new string
			//works much like last one, except building the string with SB
			//instead of concat'ing the new string with each new part bit
			if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
				compressed.append(str.charAt(i));
				compressed.append(countConsecutive);
				countConsecutive = 0;
			}
		}
		return compressed.length() < str.length() ? compressed.toString() : str;
		//gab -- look up the syntax in this line, you've forgotten it.
	}
	
}
