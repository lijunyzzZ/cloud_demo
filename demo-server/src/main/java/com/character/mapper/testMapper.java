package com.character.mapper;

import com.character.instance.User;
import com.utils.MybatisUtils;
import org.apache.catalina.mapper.Mapper;
import org.apache.ibatis.session.SqlSession;

public class testMapper {
    public static void main(String[] args) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper =  sqlSession.getMapper(UserMapper.class);
        mapper.add(new User(12,"asdad",11d));
    }
}
