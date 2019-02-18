package com.character.impl;

import com.character.dao.UserDao;
import com.character.dao.UserDao1;
import com.character.instance.User;
import com.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    @Override
    public void add(User user) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        sqlSession.getMapper(UserDao.class);
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

    @Override
    public List<User> pagination(int start, int end) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            Map<String,Object> map = new HashMap<>();
            map.put("start",start);
            map.put("end",end);
            return sqlSession.selectList("UserId.pagination",map);
        }catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally {
            MybatisUtils.closeSqlSession();
        }
    }

    @Override
    public List<User> findByCondition(String name, Double sal) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("name", name);
            map.put("sal", sal);
            return sqlSession.selectList("UserId.findByCondition", map);
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        } finally {
            MybatisUtils.closeSqlSession();
        }
    }

    @Override
    public void updateByCondition(int id, String name, Double sal) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try{
            Map<String,Object> map = new HashMap<>();
            map.put("id",id);
            map.put("name",name);
            map.put("sal",sal);
            sqlSession.update("UserId.updateByCondition",map);

        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally {
            MybatisUtils.closeSqlSession();
        }
    }

    @Override
    public void deleteByCondition(int... ids) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            sqlSession.delete("UserId.deleteByConditions",ids);
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
    public void insertByCondition(User user) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            sqlSession.insert("UserId.insertByCondition",user);
            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;

        }finally {
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
    @Test
    public void testPagination() throws Exception {
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        List<User> users = userDaoImpl.pagination(0,1);
        for (User user:users){
            System.out.println(user.getName());
        }
    }
    @Test
    public void testFindByCondition()throws Exception{
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        List<User> users = userDaoImpl.findByCondition(null,8000D);
        for (User student : users ) {
            System.out.println(student.getId() + "---" + student.getName() + "----" + student.getSal());
        }
    }
    @Test
    public void testUpdateByCondition()throws Exception{
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        userDaoImpl.updateByCondition(1,"ljy",8000D);
    }
    @Test
    public void testDeleteByCondition()throws Exception{
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        userDaoImpl.deleteByCondition(1,2);
    }
    @Test
    public void testInsertByCondition()throws Exception{
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        userDaoImpl.insertByCondition(new User(55, null, null));//name和sal为空

        userDaoImpl.insertByCondition(new User(66, "haxi", null));//sal为空
        userDaoImpl.insertByCondition(new User(77, null, 3999d));//name为空
    }
}
