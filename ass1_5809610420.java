package HW1_5809610420;
// 5809610420 Kanlayanee Dumkasem 

import java.util.Scanner;

public class ass1_5809610420 {
	public static void main(String[] args) {
		String input;
		char[] inputkey = new char[16];
		Scanner sc = new Scanner(System.in);
	
		//Input Key 
		System.out.print("Input Key 16 Characters : "); 
		input = sc.nextLine();
		sc.close();
		try{
			for(int i =0; i<inputkey.length;i++) {
				char c=  input.charAt(i);
				inputkey[i] = c;
			}
		
			if(input.length() > inputkey.length)System.out.println("Please Input 16 Characters.");
			else KeyExpansion(inputkey);
			
		}catch (IndexOutOfBoundsException e) {
			System.out.println("Please Input 16 Characters.");
		}	
	}
	
	private static String[] AsciiToHexa(char[] chars){
		String[] hexa = new String[chars.length]; 
		for (int i = 0; i < chars.length; i++)
		{
		  hexa[i] = (Integer.toHexString((int) chars[i]));
		}
		return hexa;
	}
	
	private static String RotWord(String word) {
		String[] wordarr = new String[word.length()/2];
		String[] rot = new String[word.length()/2];

		for(int i = 0; i< word.length()/2; i++ )
		{
			wordarr[i] = word.substring(2*i, 2*i+2);
		}

		for(int i = 1 ; i < wordarr.length; i++) {
			rot[i-1] = wordarr[i];
		}
			rot[3] = wordarr[0];
		
		String rots = rot[0]+rot[1]+rot[2]+rot[3];
		return rots;
	}

	private static String SubWord(String rotword){
		String[] subword = new String[rotword.length()];
		String[] rotword2 = new String[rotword.length()/2];
		String[] sBox = 
			{
			   "63", "7C", "77", "7B", "F2", "6B", "6F", "C5", "30", "01", "67", "2B", "FE", "D7", "AB", "76",
			   "CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4", "A2", "AF", "9C", "A4", "72", "C0",
			   "B7", "FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5", "E5", "F1", "71", "D8", "31", "15",
			   "04", "C7", "23", "C3", "18", "96", "05", "9A", "07", "12", "80", "E2", "EB", "27", "B2", "75",
			   "09", "83", "2C", "1A", "1B", "6E", "5A", "A0", "52", "3B", "D6", "B3", "29", "E3", "2F", "84",
			   "53", "D1", "00", "ED", "20", "FC", "B1", "5B", "6A", "CB", "BE", "39", "4A", "4C", "58", "CF",
			   "D0", "EF", "AA", "FB", "43", "4D", "33", "85", "45", "F9", "02", "7F", "50", "3C", "9F", "A8",
			   "51", "A3", "40", "8F", "92", "9D", "38", "F5", "BC", "B6", "DA", "21", "10", "FF", "F3", "D2",
			   "CD", "0C", "13", "EC", "5F", "97", "44", "17", "C4", "A7", "7E", "3D", "64", "5D", "19", "73",
			   "60", "81", "4F", "DC", "22", "2A", "90", "88", "46", "EE", "B8", "14", "DE", "5E", "0B", "DB",
			   "E0", "32", "3A", "0A", "49", "06", "24", "5C", "C2", "D3", "AC", "62", "91", "95", "E4", "79",
			   "E7", "C8", "37", "6D", "8D", "D5", "4E", "A9", "6C", "56", "F4", "EA", "65", "7A", "AE", "08",
			   "BA", "78", "25", "2E", "1C", "A6", "B4", "C6", "E8", "DD", "74", "1F", "4B", "BD", "8B", "8A",
			   "70", "3E", "B5", "66", "48", "03", "F6", "0E", "61", "35", "57", "B9", "86", "C1", "1D", "9E",
			   "E1", "F8", "98", "11", "69", "D9", "8E", "94", "9B", "1E", "87", "E9", "CE", "55", "28", "DF",
			   "8C", "A1", "89", "0D", "BF", "E6", "42", "68", "41", "99", "2D", "0F", "B0", "54", "BB", "16"
			};
		
		for(int i = 0; i< rotword2.length; i++ )
		{
			rotword2[i] = rotword.substring(2*i, 2*i+2);
		}
		
		String sub = "";
		for(int i=0; i<subword.length/2;i++) {
			int a = (int)rotword2[i].charAt(0);
			int b = (int)rotword2[i].charAt(1);
			int x = Character.getNumericValue(a);
			int y = Character.getNumericValue(b);
			
			subword[i] = sBox[(x*16)+y];
			sub += subword[i];
		}
		return sub.toLowerCase();
	}
	
	private static String XOR(String a,String b){
		char[] hexa = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		String result = "";
		
		for(int i=0; i<a.length(); i++){
			result += hexa[Integer.parseInt(a.substring(i,i+1),16) ^ Integer.parseInt(b.substring(i,i+1),16)];
		}
		return result;
	}
	
	private static void KeyExpansion(char[] inputkey){
		String[] Hexa = AsciiToHexa(inputkey);
		String[] Word = new String[44];
		String[] Rcon ={"8D000000", "01000000", "02000000", "04000000" ,"08000000" , "10000000", "20000000", "40000000" ,"80000000" , "1B000000", "36000000"};
		
		for(int i =0; i<4; i++){
			Word[i] = Hexa[4*i]+Hexa[(4*i)+1]+Hexa[(4*i)+2]+Hexa[(4*i)+3];
		}     
		for(int i=4; i<44; i++){
			if(i%4!=0){
				Word[i] = XOR(Word[i-1],Word[i-4]);
			}else{
				String t = XOR(SubWord(RotWord(Word[i-1])), Rcon[i/4]);
				Word[i] = XOR(t, Word[i-4]);
			}
		}

		///Print Generate Word
        System.out.println("\n-- Generate Word 0-43 --\n");
        for(int j=0;j<Word.length;j++){
            System.out.println("Word "+j+" = "+Word[j].toUpperCase().substring(0,2)+" "+Word[j].toUpperCase().substring(2,4)+" "
            		+Word[j].toUpperCase().substring(4,6)+" "+Word[j].toUpperCase().substring(6)+" ");
        }       
	}
}