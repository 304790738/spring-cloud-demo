package cn.bjtu.edu.sso.service;

import cn.bjtu.edu.common.dao.common.PageList;
import cn.bjtu.edu.sso.entity.User;
import org.springframework.data.mongodb.core.query.Query;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author
 * @since 2020-06-01
 */
public interface UserService {

    User getOne(Query query);

    User saveUser(User user);

    User getOneById(String id);

    User getOneByUserId(int id);

    PageList<User> getUserPage(int pageSize, int currentPage, String field, int min, int max);

    long deleteUser(String id);

    long updateUser(User user);
}
