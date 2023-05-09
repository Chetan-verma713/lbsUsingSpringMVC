package org.naehas.model.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping(value = "/*")
    public ResponseEntity<?> pageNotFound() {
        return new ResponseEntity<>("Invalid URL, Page Not Found", HttpStatus.NOT_FOUND);
    }

}
