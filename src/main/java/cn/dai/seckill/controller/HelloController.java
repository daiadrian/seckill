package cn.dai.seckill.controller;

import cn.dai.seckill.entity.Hello;
import cn.dai.seckill.mapper.HelloMapper;
import cn.dai.seckill.result.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;

/**
 * @author adrian
 * @date 2018/10/27 11:24
 **/
@Controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private HelloMapper helloMapper;

    @GetMapping("/{id}")
    @ResponseBody
    public Results<Hello> findHelloById(@PathVariable("id") Integer id){
        Hello hello = helloMapper.findHelloById(id);
        return Results.success(hello);
    }

}
