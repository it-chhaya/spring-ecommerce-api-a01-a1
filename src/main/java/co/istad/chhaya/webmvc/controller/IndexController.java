package co.istad.chhaya.webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {


    //@RequestMapping(path = "/index", method = RequestMethod.GET)
    @GetMapping("/index/{page}/category/{number}")
    public String viewIndex(@PathVariable String page,
                            @PathVariable Integer number,
                            @RequestParam(required = false, defaultValue = "") String query) {
        System.out.println("Page: " + page);
        System.out.println("Number: " + number);
        System.out.println("Query: " + query);
        return "index";
    }

}
