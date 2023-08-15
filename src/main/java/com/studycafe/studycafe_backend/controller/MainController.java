package com.studycafe.studycafe_backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class MainController {
    @GetMapping("/try")
    public Map<String, Object> root() {
        Map<String, Object> rtnMap = new LinkedHashMap<String, Object>();
        rtnMap.put("msg", "connected!");
        return rtnMap;
    }
}
