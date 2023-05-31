package bo.edu.ucb.tallerdedesarollo.backend;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;

import bo.edu.ucb.tallerdedesarollo.backend.BL.FileSystemStorageService;

import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication
@MapperScan("bo.edu.ucb.tallerdedesarollo.backend.DAO")
public class BackendApplication {

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		return factoryBean.getObject();
	}
	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("vicente.navia.rene@gmail.com");
		mailSender.setPassword("tgsamegomsvcqudd");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner init(FileSystemStorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}

	// @Bean
	// public RestTemplate restTemplate() {
	// 	return new RestTemplate();
	// }

}
