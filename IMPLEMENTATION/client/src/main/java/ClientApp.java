import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.UserService;

public class ClientApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("config");

        UserService userService = context.getBean(UserService.class);

        userService.getAllUsers().forEach(System.out::println);
    }

}
