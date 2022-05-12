package de.mathisneunzig.kryptologieVL;

public class GGT {
	
	public static void main(String[] args) {
		
		System.out.println(ggt(237,27));
		System.out.println(ggt(31,103));
		System.out.println(ggt(47,1));
		
	}
	
	public static int ggt(int a, int b) {
		if(a<b) {
			return ggt(b, a);
		} else if(a%b==0) {
			return b;
		} else {
			return ggt(a%b, b);
		}
		
	}
	
}
