package Default;

import java.util.Scanner;
import java.util.stream.Stream;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.file.Files;
import java.io.IOException;
import java.math.BigInteger;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


public class Cracker {
	
	public static void main(String args[]){

			
			System.out.print("Enter User to hack: ");
	        Scanner scan = new Scanner(System.in);
	        String user = scan.nextLine();
			//String userline = "";
	        long startTime = System.currentTimeMillis();
			String log = "/Users/Scott/eclipse-workspace/Final/userlog.txt";
			String dict = "/Users/Scott/eclipse-workspace/Final/dictionary.txt";
			String lines = "";
			//String guess = "";
			String[] nums = {"0","1","2","3","4","5","6","7","8","9",""};
			String[] chars = { "@","#","$","%","&",""};
									
		//	List<String> list = new ArrayList<>();
			
	        try (Stream<String> stream = Files.lines(Paths.get(log))) {
	        	
	        	//searches user log for line of info of desired username
	        	String userlist = stream.filter(line -> line.contains(user + ":"))
	        			.map(n -> n.toString()) 
	        			.collect(Collectors.joining(":"));
	     	        	
	        	//isolates stored password hash from the user:password format 
	        	String cut = user + ":";
	        	String userpass = userlist.replaceAll(cut, "");	        		        	
	        	

	        	//runs through dictionary once to check for weak type password 

	        		Scanner scanner2 = new Scanner(Paths.get(dict));	
	        		String dictword = "";
	        		while(scanner2.hasNextLine()) {
	        			 dictword = scanner2.nextLine();
	        			
	        			 if (md5(dictword).contentEquals(userpass)) {
	        				 System.out.println("Password is: "+dictword);
	        				 System.exit(0);
	        			 }
	        		}
	        			    
	        	// scans for type 2 moderate passwords
	        	Scanner scanner = new Scanner(Paths.get(dict));
	        	boolean found = false;
	        	String[] isFound = {"Notfound"};
	        	while((scanner.hasNextLine())&&(!(isFound[0].contentEquals("found")))) {
	        			 	 	        	
	        	    lines = scanner.nextLine();
	        	    //System.out.println("Testing Combinations for: " +lines);
	        	    
	        	    
	        	    //iterates through each combination of characters and integers, stores combo as list
	        	    
	        	    //recursive call permutes through each order combination for the characters, intigers, and base dictionary word

	        			for (int j = 0; j< chars.length; j++){
	        			for (int k = j; k< chars.length; k++){
	        				for (int l = 0; l< nums.length; l++){
	        				for (int m = l; m< nums.length; m++) {
	        									

	        					ArrayList order = new ArrayList<>(Arrays.asList(lines, chars[j], chars[k], nums[l], nums[m]));
	        					
	        					
	        					
	        					// removes blank spaces from recursive call
	        					
	        					for (int z =0 ; z < order.size(); z++) {
	        						
	        						if (order.get(z) == ""){
	        							
	        							order.remove(z);
	        							z--;
	        							
	        						}
	        					}
	        					
	        					
	        					
	        					recursion(order,"",userpass, isFound);   	
	        					//if (isFound[0].contentEquals("found")){
	        						
	        						
	        					//}
	        					
	        					
	        				}
	        				}
	        			}
	        			}	        	    	        	    
	        	 

	        	}
	        	long end = System.currentTimeMillis();
	        	float time = ((end-startTime)/1000F);
	        	System.out.println("Took "+time +" seconds.");
	        	
	        } catch (IOException e) {
	            e.printStackTrace();
	        }			
}
		
	public static String md5(String input) {

   	 String md5 = null;

   	 if(null == input) return null;

   	 try {
   	 //Create MessageDigest object for MD5
   	 MessageDigest digest = MessageDigest.getInstance("MD5");

   	 //Update input string in message digest
   	 digest.update(input.getBytes(), 0, input.length());
   	 //Converts message digest value in base 16 (hex)
   	 md5 = new BigInteger(1, digest.digest()).toString(16);
   	 } catch (NoSuchAlgorithmException e) {
   	 e.printStackTrace();
   	 }
   	 return md5; 

}
	public static void recursion(ArrayList<String> strings, String guessString, String userpass,String[] isFound){
		if (strings.size() == 0){
			//guess(guessString);
			
			if (md5(guessString).contentEquals(userpass)) {				
				System.out.println("Password is "+guessString);
				isFound[0] = "found";
								
			}
		}
		for (int i=0; i< strings.size(); i++){
			
																									
			ArrayList<String> newStrings = new ArrayList<String>(strings);
	newStrings.remove(i);	
		
			recursion(newStrings, guessString + strings.get(i), userpass, isFound);
			if ((i <= strings.size()-2) && (strings.get(i) == strings.get(i+1) )) {
				i++;
			}
	}	
}
}	
	


