# PasswordCracker
Java MD5 hash brute-force password cracker
A VERY Impractical Cracker

If password contains any word from the dictionary, and any 4 designated characters or numbers, in any order, this will crack the assoociated password via matching MD5 hashes.

User must provide dictionary.txt 

To start: Create user with User_Login.java: creates username:MD5'd password file
User_Login is just a simple hash comparison to saved file and entered password
Cracker takes the MD5 from 


Extreme time-complexity here. Im talking like O(n!^4) if that's even a thing. Will take hours if the dictionary word starts with a C or below. Not meant to be practical!
