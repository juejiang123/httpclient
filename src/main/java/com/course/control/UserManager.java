package com.course.control;

import com.course.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Log4j
@RestController
@Api(value = "v1",description = "青霞调试")
@RequestMapping("v1")
public class UserManager {

    @Autowired
    private SqlSessionTemplate template;

    @ApiOperation(value = "登录接口",httpMethod = "POST")
    @RequestMapping(value = "/login",method = RequestMethod.POST,consumes = "application/json")
    public Boolean login(HttpServletResponse response,@RequestBody User user){
        int i = template.selectOne("login", user);
        log.info(i);
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        if(i==1){
            log.info("查询到结果了");
            return true;
        }
        return false;


    }
}
