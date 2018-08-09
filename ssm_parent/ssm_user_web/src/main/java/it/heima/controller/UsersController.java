package it.heima.controller;

import it.heima.domain.Users;
import it.heima.service.UsersService;
import org.apache.ibatis.annotations.Many;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
//@RolesAllowed("ROlE_USER")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("register")
    public String register(){
        return "register";
    }

    //注册
    @RequestMapping("doRegister")
    public String register(Users users){
      usersService.save(users);

        return "redirect:/user/login";
    }

    @RequestMapping("success")
    public ModelAndView success(){
        ModelAndView mv = new ModelAndView("success");
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User principal = (User) authentication.getPrincipal();
        String username = principal.getUsername();
        Users users = usersService.findByUsername(username);
        mv.addObject("user",users);

        return mv;
    }

}
