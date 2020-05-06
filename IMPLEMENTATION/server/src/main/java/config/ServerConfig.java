package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import service.UserService;
import service.UserServiceImpl;

@Configuration
public class ServerConfig {
    @Bean
    RmiServiceExporter rmiServiceExporter() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("UserService");
        rmiServiceExporter.setServiceInterface(UserService.class);
        rmiServiceExporter.setService(userService());
        return rmiServiceExporter;
    }

    @Bean
    UserService userService() {
        UserRepository userRepository = new UserRepositoryImpl();
        return new UserServiceImpl(userRepository);
    }
}