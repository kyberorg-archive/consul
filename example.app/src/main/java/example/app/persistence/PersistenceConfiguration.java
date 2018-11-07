package example.app.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

import static java.lang.String.format;

@Configuration
public class PersistenceConfiguration {
	
	@Value("${example.app.db.name}")
	private String databaseName;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Bean
	@Primary
	public DataSource dataSource() {
		var postgresInstance = getPostgresInstance();
		
	    return DataSourceBuilder
	        .create()
	        .username("postgres")
	        .password("password")
	        .url(format("jdbc:postgresql://%s:%s/%s", postgresInstance.getHost(), postgresInstance.getPort(), databaseName))
	        .driverClassName("org.postgresql.Driver")
	        .build();
	}
    
    private ServiceInstance getPostgresInstance() {    	
    	return discoveryClient.getInstances("postgres")
    	.stream()
        .findFirst()
        .orElseThrow(() -> new IllegalStateException("Unable to discover a Postgres instance"));
    }
}
