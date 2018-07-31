package com.springboot.datasource.controller;

import com.springboot.datasource.properties.UserProperties;
import com.springboot.datasource.request.UserReqeust;
import com.springboot.datasource.service.ExampleService;
import com.springboot.datasource.util.Result;
import com.springboot.datasource.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ExampleController {

    @Autowired
    private UserProperties userProperties;
    @Autowired
    private ExampleService exampleService;

    @RequestMapping(value = "/properties")
    public Result<UserProperties> properties() {
        return ResultUtil.success(userProperties);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/valid")
    public Result<UserReqeust> sayHelloPost(@Valid UserReqeust user, BindingResult bindingResult) {
        Result<UserReqeust> result = null;
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            result = ResultUtil.error("0000001", bindingResult.getFieldError().getDefaultMessage());
            return result;
        }
        result = ResultUtil.success(user);
        System.out.println(user.toString());
        return result;
    }
    @RequestMapping(value = "/exception/{age}")
    public Result<Object> sayExceptionPost(@PathVariable("age") int age) {
        return exampleService.sayExceptionPost(age);
    }


}
