//practice and notes for coding interview questions
//main only calls the methods
//see each method for the question and the notes

public class Arrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Q1
		System.out.println(isUniqueChars("gabrielle"));  //false
		System.out.println(isUniqueChars("abcdefg"));  //true
		
		//Q2
		System.out.println(permutation1("Gab", "bag"));  //false
		System.out.println(permutation1("gab", "bag"));  //true
		System.out.println(permutation1("gab", "bag "));  //false
		
		//Q3
		//when you feel like it, write a way to call the method and get a result
		
		//Q4

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
	
	
	//Q4 check whether a string is a permutation of a palindrome
	
	//tips: understand both of those terms.
	//palindrome: contains pairs of matching letters (with possibly ONE nonmatch)
	//inefficient: creating all possible permutations and checking if palindromes. don't do it.
	
	//solution 1:
	
	
	
}
