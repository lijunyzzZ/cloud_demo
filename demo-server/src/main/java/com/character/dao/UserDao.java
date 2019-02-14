package com.character.dao;

import com.character.instance.User;

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
     * @param id
     * @param user
     */
    public void update(User user);

    /**
     * 根据id查找用户的信息。
     * @param id
     * @return
     */
    public User select(int id);
}
