package pe.com.sedapal.seguridad.core.util;

import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	@Value("#{configuration['parametro.token.jwtExpiration']}")
	private int jwtExpiration;

	private static String jwtSecret;
	
	@Value("#{configuration['parametro.token.jwtSecret']}")
	public void setJwtSecret(String jwtSecret) {
		JwtUtil.jwtSecret = jwtSecret;
	}

	@Autowired
	RedisUtil redis;
	
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

	public String generateJwtToken(String username) {
		

		String token = Jwts.builder()
					.setSubject(username)
					.setIssuedAt(new Date())
//					.setExpiration(new Date(System.currentTimeMillis() + jwtExpiration*60000 ))  //jwtExpiration * 1000
					.setExpiration(new Date(System.currentTimeMillis() + 20000 ))
					.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

		return token;

	}	
	
	public static boolean validaJwtToken(String token) {
		
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
		} catch (Exception e) {
			 logger.error("Invalid Error -> Message: {} ", e);
		}
		return false;
		
	}
	
	
	 public static String getUserToken(String token) {
	        return Jwts.parser()
				                .setSigningKey(jwtSecret)
				                .parseClaimsJws(token)
				                .getBody().getSubject();
	    }
	
}
