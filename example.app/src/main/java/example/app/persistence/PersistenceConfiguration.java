package example.app.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import static java.lang.String.format;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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
	
	/*@Bean
    public SessionFactory sessionFactory() {
		StandardServiceRegistry registry = null;
		
		try {
            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

            Map<String, String> settings = new HashMap<>();
            settings.put(Environment.DRIVER, "org.postgresql.Driver");
            settings.put(Environment.URL, format("jdbc:postgresql://%s/%s", getPostgresInstance().getHost(), databaseName));
            settings.put(Environment.USER, "postgres");
            settings.put(Environment.PASS, "password");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect");

            registryBuilder.applySettings(settings);
            
            registry = registryBuilder.build();

            MetadataSources sources = new MetadataSources(registry);

            Metadata metadata = sources.getMetadataBuilder().build();

            return metadata.getSessionFactoryBuilder().build();
         } catch (Exception e) {            
            if (registry != null) {
               StandardServiceRegistryBuilder.destroy(registry);
            }
            
            throw e;
         }
    }*/
    
    private ServiceInstance getPostgresInstance() {    	
    	return discoveryClient.getInstances("postgres")
    	.stream()
        .findFirst()
        .orElseThrow(() -> new IllegalStateException("Unable to discover a Postgres instance"));
    }
}
