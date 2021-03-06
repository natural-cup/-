package com.qsh.miaosha.controller;

import com.qsh.miaosha.redis.RedisService;
import com.qsh.miaosha.result.Result;
import com.qsh.miaosha.service.MiaoshaUserService;
import com.qsh.miaosha.vo.LoginVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger log= LoggerFactory.getLogger(LoginController.class);

    @Autowired
    MiaoshaUserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/do_login")//异步
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginVo){
        log.info(loginVo.toString());
        //参数校验
//        String password = loginVo.getPassword();
//        String mobile = loginVo.getMobile();
//        if (StringUtils.isEmpty(password)){
//            return Result.error(CodeMsg.PASSWORD_EMPTY);
//        }if (StringUtils.isEmpty(mobile)){
//            return Result.error(CodeMsg.MOBILE_EMPTY);
//        }if (!ValidatorUtil.isMobile(mobile)){
//            return Result.error(CodeMsg.MOBILE_ERROR);
//        }
        //登录
        boolean ret=userService.login(response,loginVo);
        return Result.success(true);
//        if (cm.getCode()==0) {
//            return Result.success(true);
//        } else{
//            return Result.error(cm);
//        }

    }


}
