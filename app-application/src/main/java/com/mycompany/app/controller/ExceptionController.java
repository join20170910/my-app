package com.mycompany.app.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author john
 */

@RestController
@RequestMapping("w")
public class ExceptionController {

    @ApiOperation(value = "测试", notes = "测试")
    @ApiImplicitParam(name = "user", value = "用户进行测试", required = true,dataType = "user")
    @RequestMapping("/exce")
    public Object showInfo(){
        System.err.println("dddddddddddddd");
        String info ="你好";
        int a = 1/0;
        return info;
    }
}
