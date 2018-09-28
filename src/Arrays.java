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
		System.out.println(permutation1("gab", "bag "));  //true

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
	
}
