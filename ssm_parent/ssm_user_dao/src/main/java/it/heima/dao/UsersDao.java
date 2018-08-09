package it.heima.dao;

import it.heima.domain.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UsersDao {

    //添加
    @Insert("INSERT INTO users (username,password,telephone)VALUES (#{username},#{password},#{telephone})")
    void save(Users users);

    //登录验证
   @Select("select * from users where username=#{username}")
   Users findByUsersName(String username);

}