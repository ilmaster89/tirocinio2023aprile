package f2.tirocinio.redisTWO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import redis.clients.jedis.JedisPooled;

@SpringBootApplication
public class RedisTwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisTwoApplication.class, args);
	}

	@Bean
	JedisPooled jedisPooled() {
		return new JedisPooled("localhost", 6379);
	}

}
