package Default;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.file.Files;
import java.io.IOException;
import java.math.BigInteger;

public class User_Login {
	
	public static void main(String args[]){

			
			System.out.print("Enter Username: ");
	        Scanner scan = new Scanner(System.in);
	        String user = scan.nextLine();
			String userline = "";
	        
			String file = "userlog.txt";
			
			List<String> list = new ArrayList<>();
			
	        try (Stream<String> stream = Files.lines(Paths.get(file))) {
	        	
	        	String userlist = stream.filter(line -> line.contains(user + ":"))
	        			.map(n -> n.toString()) 
	        			.collect(Collectors.joining(":"));
	        	
	        	String cut = user + ":";
	        	String userpass = userlist.replaceAll(cut, "");	        		        	
	        	
	        	
	        	System.out.print("Enter Password: ");
	        	String passin = scan.nextLine();
	        	
	        	passin = md5(passin);
	        	if (passin.contentEquals(userpass) == true) {
	        		
	        		System.out.println("Password Accepted");
	        			        		
	        	} else { 
	        		
	        		System.out.println("Password Denied");
	        	
	        	}	        	
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
}
