package f2.tirocinio.redisONE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import redis.clients.jedis.JedisPooled;

@SpringBootApplication
public class RedisOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisOneApplication.class, args);
	}

	@Bean
	JedisPooled jedisPooled() {
		return new JedisPooled("localhost", 6379);
	}

}
