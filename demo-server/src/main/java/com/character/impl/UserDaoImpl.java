package com.character.impl;

import com.character.dao.UserDao;
import com.character.dao.UserDao1;
import com.character.instance.User;
import com.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

public class UserDaoImpl implements UserDao {
    @Override
    public void add(User user) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            sqlSession.insert("UserId.add", user);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        } finally {
            MybatisUtils.closeSqlSession();
        }
    }

    @Override
    public void delete(int id) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            sqlSession.selectOne("UserId.delete",id);
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally {
            MybatisUtils.closeSqlSession();
        }
    }

    @Override
    public void update(User user) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            sqlSession.update("UserId.update",user);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally {
            MybatisUtils.closeSqlSession();
        }
    }

    @Override
    public User select(int id) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            return sqlSession.selectOne("UserId.findById", id);
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        } finally {
            MybatisUtils.closeSqlSession();
        }
    }


    @Test
    public void testAdd() throws Exception {
        UserDaoImpl userDao1 = new UserDaoImpl();
        User user = new User(1, "sadasdasd", 1111D);
        userDao1.add(user);
    }
    @Test
    public void testSelect() throws Exception {
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        User user = userDaoImpl.select(1);
        System.out.println(user.getName());
    }
    @Test
    public void testDelete() throws Exception {
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        userDaoImpl.delete(1);
    }
    @Test
    public void testUpdate() throws Exception {
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        User user = new User(1, "111", 1111D);
        userDaoImpl.update(user);
        System.out.println(userDaoImpl.select(1).getName());
    }
}
