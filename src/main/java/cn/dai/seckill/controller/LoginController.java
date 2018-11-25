package cn.dai.seckill.controller;

import cn.dai.seckill.entity.User;
import cn.dai.seckill.result.CodeMsg;
import cn.dai.seckill.result.Results;
import cn.dai.seckill.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author adrian
 * @date 2018/10/27 14:01
 **/
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/to_login")
    public String toLogin(){
        return "/login";
    }

    @PostMapping("/doLogin")
    @ResponseBody
    public Results<String> login(HttpServletResponse response,@Valid User user){
        boolean login = loginService.login(response, user);
        if (login){
            return Results.success(CodeMsg.LOGIN_SUCCESS.getMsg());
        }
        return Results.faild(CodeMsg.LOGIN_FAIL.getMsg());
    }

}
