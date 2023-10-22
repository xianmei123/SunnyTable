package com.evigel.sunnytable.controller;

import com.evigel.sunnytable.dto.BarChartTemplateDto;
import com.evigel.sunnytable.service.IUserService;
import com.evigel.sunnytable.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

//    @GetMapping("/login/{id}")
//    public ResponseEntity<Long> login(@PathVariable("id") String id) {
//        long fid = userService.login(id);
//        return ResponseEntity.ok().body(fid);
//    }
//
//    @GetMapping("/logout/{id}")
//    public void logout(@PathVariable("id") String id) {
//        userService.logout(id);
//    }

}
