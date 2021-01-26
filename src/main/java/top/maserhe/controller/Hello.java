package top.maserhe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述:
 *
 * @author Maserhe
 * @create 2021-01-26 21:45
 */
@Controller
public class Hello {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "dfadf";
    }
}
