package com.character.dao;

import com.character.instance.User;

import java.util.List;

public interface UserDao {
    /**
     * 新增用户。
     * @param user
     */
    public void add(User user);

    /**
     * 根据id删除用户。
     * @param id
     */
    public void delete(int id);

    /**
     * 修改用户信息。
     * @param user
     */
    public void update(User user);

    /**
     * 根据id查找用户的信息。
     * @param id
     * @return
     */
    public User select(int id);

    /**
     * 分页。
     * @param start
     * @param end
     * @return
     */
    public List<User> pagination(int start,int end);


    /**
     * 动态查找。
     * @param name
     * @param sal
     * @return
     */
    public List<User> findByCondition(String name,Double sal);

    /**
     * 动态更新。
     * @param id
     * @param name
     * @param sal
     */
    public void updateByCondition(int id,String name,Double sal);

    /**
     * 批量删除。
     * @param ids
     */
    public void deleteByCondition(int... ids);

    /**
     * 动态插入。
     * @param user
     */
    public void insertByCondition(User user);
}
