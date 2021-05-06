package pe.com.sedapal.seguridad.web.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {

	
	
	
	public static void main(String[] args) {

		//int i = 0;
		//while (i < 10) {
			String password = "123456";
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);
			System.out.println(hashedPassword);
			boolean isMatch = passwordEncoder.matches("3qktZuhah", "$2a$10$03lHiqOVW9Ka1qGn.vdVIeH8LqoMvz57ULJ.BnbD0pFzQpqfNr/Va");
			
			if (isMatch) {
				System.out.println("match");
			} else {
				System.out.println("no match");
			}
			//i++;
		//}

	  }
}
