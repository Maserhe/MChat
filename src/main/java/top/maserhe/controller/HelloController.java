package top.maserhe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-26 1:04
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello,mchat";
    }

}
