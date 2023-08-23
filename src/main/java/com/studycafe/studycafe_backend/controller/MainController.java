package com.studycafe.studycafe_backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class MainController {
    @GetMapping("/try")
    public Map<String, Object> root(@RequestParam String data) {
        Map<String, Object> rtnMap = new LinkedHashMap<String, Object>();
        rtnMap.put("msg", "connected!");
        rtnMap.put("requestMsg", data);
        return rtnMap;
    }
}
