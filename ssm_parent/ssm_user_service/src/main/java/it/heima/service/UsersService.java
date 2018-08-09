package it.heima.service;

import it.heima.domain.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {

 //添加
    void save(Users users);

    //查询
    Users findByUsername(String username);
}
