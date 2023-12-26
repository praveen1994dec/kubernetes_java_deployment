 package uk.co.danielbryant.djshopping.stockmanager;
 
 import java.util.Date;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.RestController;
 
 @RestController
 public class SimpleRestController {
     @GetMapping("/example")
     public String example() {
        return "Hello User !! " + new Date();
     }
 }
