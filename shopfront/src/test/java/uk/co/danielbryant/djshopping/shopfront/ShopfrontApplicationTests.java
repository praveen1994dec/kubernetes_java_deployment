package uk.co.danielbryant.djshopping.shopfront;
import java.util.Date;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthcheck {
@GetMapping("/actuator/health")
public String healthcheck() {
return "Hello User !! " + new Date();
}
}
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ShopfrontApplication.class)
public class ShopfrontApplicationTests {

    @Test
    public void contextLoads() {
    }

}
