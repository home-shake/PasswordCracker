package Default;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.File;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Create_User {

	    public static void main(String args[]){
	   
	    try {
	        File File = new File("userlog.txt");
	          FileWriter fw = new FileWriter(File,true);
	          BufferedWriter br = new BufferedWriter(fw);
	          br.write("");
	        System.out.print("Enter New Username: ");
	        Scanner scan = new Scanner(System.in);
	        String user = scan.nextLine();
	        FileWriter fWriter = null;
	        BufferedWriter writer = null;
	        
	        Scanner scanner = new Scanner(new File("userlog.txt"));
	        String lines = "";
	        boolean checkExists = false;
	        
        	while(scanner.hasNextLine()) {
        		
        		lines = scanner.nextLine();
        		String[] userCompare = lines.split(":");
       		
        		if (userCompare[0].contentEquals(user)) {
        			checkExists = true;        			
        		}
        	}
	        
	        
	        //writes username to new file 'userlog.txt' with format 'user:password'

	         // File File = new File("userlog.txt");
	         // FileWriter fw = new FileWriter(File,true);
	         // BufferedWriter br = new BufferedWriter(fw);
	          
	          
	          if (!checkExists) {
	          
	          br.write(user);
	          br.write(":");
	        
	         // writes corresponding password converted to md5
	          
	        System.out.print("Enter Password: ");
	        String pass = scan.nextLine();        

	          br.write(md5(pass));

	          
	          
	          Scanner scanner2 = new Scanner(new File("dictionary.txt"));
	          Scanner scanner3 = new Scanner(new File("dictionary.txt"));
	          String passMatch ="";
	          String baseword = "";
	          String strength = "";
	          
	          while(scanner2.hasNextLine()) {
	        	  
	        	  passMatch = scanner2.nextLine();
	        	  
	        	  if (passMatch.contentEquals(pass)) {
	        		  
	        		  strength = "weak";	 
	        		  
	        	  }  
	        		  
	          }	  
	          scanner2.close();
	        if(strength.contentEquals("")) {
	        	
	        		  while(scanner3.hasNextLine()) {	        			  
	        			  baseword = scanner3.nextLine();
	        			  //System.out.println(baseword);
	        			  //System.out.println(pass);
	        			  if (pass.contains(baseword)) {	        			  	        			  
	        			  strength = "moderate";	        			  
	        		  } else if (strength.contentEquals("")){
	        			 
	        			  strength = "strong";
	        		  }
	        		  }
	        	
	        		  
	        	    
	        	  }
	        
	        
	        

	          br.newLine();
              
	          br.close();
	          fw.close();
	        	  System.out.println("Password strength: "+ strength);	          
	          
	          } else {
	        	  
	        	  System.out.println("user already exists!");
	        	  
	          }
	        } catch (Exception e) {
	            System.out.println("Error!");
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
}
	
	

