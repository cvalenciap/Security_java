package pe.com.sedapal.seguridad.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class RedisConfiguration {
	
	@Value("#{configuration['spring.redis.host']}")
	private String hostName;
	
	@Value("#{configuration['spring.redis.port']}")
	private int port;
	
	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
		jedisConFactory.setHostName(hostName);
		jedisConFactory.setPort(port);
		jedisConFactory.setUsePool(true);
		return jedisConFactory;
	}
}
