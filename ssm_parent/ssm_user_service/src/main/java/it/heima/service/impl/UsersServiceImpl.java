package it.heima.service.impl;

import it.heima.dao.UsersDao;
import it.heima.domain.Users;
import it.heima.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UsersServiceImpl implements UsersService {
    //注入dao
    @Autowired
    private UsersDao usersDao;

    //添加
    @Override
    public void save(Users users) {
        usersDao.save(users);
    }

    @Override
    public Users findByUsername(String username) {
        return usersDao.findByUsersName(username);
    }

    /*
           此方法由SpringSecurity框架调用，最终实现登陆认证
           参数：username 用户输入的用户名
        */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //根据用户名,查询
        Users sysUser = usersDao.findByUsersName(s);
        //每个的所有角色权限(写死)
        List<GrantedAuthority> authorities = new ArrayList<>();
        //每个用户都有ROLE_USER角色,与spring-security.xml中配置一致
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));


        // 返回结果
        //import org.springframework.security.core.userdetails.User;
        User user = new User(sysUser.getUsername(),"{noop}"+sysUser.getPassword(),authorities);

        return user;
    }


}
