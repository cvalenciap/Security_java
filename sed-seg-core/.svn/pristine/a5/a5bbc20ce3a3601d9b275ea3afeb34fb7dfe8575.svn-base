package pe.com.sedapal.seguridad.core.util;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import pe.com.sedapal.seguridad.core.bean.UsuarioBean;
import pe.com.sedapal.seguridad.core.commons.Constants;


@Component
@Service
public class RedisUtil {

	@Resource(name = "redisTemplate")
	private RedisTemplate<String, Object> userTemplate;
	
	@Value("#{configuration['parametro.token.jwtExpiration']}")
	private int jwtExpiration;

	public ValueOperations<String, Object> getValueOperations() {
		try {

			return userTemplate.opsForValue();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;

	}

	public String getRedisKey(final String userId) {
		return Constants.REDIS_PREFIX_USERS + Constants.REDIS_KEYS_SEPARATOR + userId;
	}

	public Object findByToken(String token) throws NotFoundException {

		Object user = (Object) getValueOperations().get(getRedisKey(token));
		
		System.out.println("FIND:" + getRedisKey(token));
		
		if (user == null) {
			System.out.println("Usuario no existe en BD Redis");
		}
		return user;
	}

	public void saveToken(String token, UsuarioBean usuario) {

		getValueOperations().set(token, usuario);
	}

	public void save(String key, Object usuario) {
		
		getValueOperations().set(getRedisKey(key), usuario, jwtExpiration, TimeUnit.MINUTES);
		String var = getRedisKey(key);
		System.out.println("SAVE:" + var);

	}

	public Object FindById(String key) {

		Object user = (Object) getValueOperations().get(key);

		return user;
	}

	public void deleteKey(String key) {
		userTemplate.delete(getRedisKey(key));
	}
	
	public void deleteKeyToken(String token) {
		
		String key = getRedisKey(token);
		userTemplate.delete(key);
		System.out.println("DELETE:"+key);
	}
	
}


