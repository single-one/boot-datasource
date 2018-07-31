package com.springboot.datasource.servlet;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/druid/*", initParams = {@WebInitParam(name = "allow", value = "127.0.0.1"),
    @WebInitParam(name = "deny", value = "192.168.1.73"),
    @WebInitParam(name = "loginUsername",value = "springboot"),
    @WebInitParam(name = "loginPassword", value = "springboot"),
    @WebInitParam(name = "resetEnable", value = "false")})
public class DruidStatServlet extends StatViewServlet {
    private static final long serialVersionUID = 1L;
}
