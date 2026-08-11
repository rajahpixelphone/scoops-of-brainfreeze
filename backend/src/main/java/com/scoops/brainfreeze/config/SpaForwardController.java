package com.scoops.brainfreeze.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Forwards non-API routes to index.html so React Router works
 * when the frontend is embedded in Spring Boot (Option A / Catalyst).
 */
@Controller
public class SpaForwardController {

    @GetMapping(value = {
            "/",
            "/search",
            "/reviews",
            "/orders",
            "/login",
            "/{path:[^\.]*}"
    })
    public String forward() {
        return "forward:/index.html";
    }
}
