package com.example.curpspring;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/curp")
public class CurpControler {
    @GetMapping("/test")
    String test(){
        return "hello";
    }

    @GetMapping("/test2")
    String test2(@RequestParam String s1, @RequestParam String s2){
        return "s3 "+ s1 + " " + s2;
    }

    @GetMapping("/test3/{param}")
    String test3(@PathVariable String param){
        return "s3 "+ param;
    }
}
