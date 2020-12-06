package cn.bjtu.edu.sso.service.impl;

import cn.bjtu.edu.common.constants.Constants;
import cn.bjtu.edu.common.dao.common.PageList;
import cn.bjtu.edu.common.utils.BlankUtil;
import cn.bjtu.edu.common.vo.UserColumn;
import cn.bjtu.edu.common.vo.UserQueryField;
import cn.bjtu.edu.sso.UserDao;
import cn.bjtu.edu.sso.entity.User;
import cn.bjtu.edu.sso.service.UserService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.Instant;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author
 * @since 2020-08-01
 */
@Slf4j
@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    String METHOD_GET_User_PAGE = "查询用户列表";
    /**
     * 日志Id
     */
    private String logId;
    @Resource
    private HttpServletRequest request;

    @Autowired
    private UserDao userDao;

    @Override
    public User getOne(Query query) {
        return userDao.findOne(query);
    }

    @Override
    public User saveUser(User user) {
        return userDao.insert(user);
    }

    @Override
    public User getOneById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return userDao.findOne(query);
    }

    @Override
    public User getOneByUserId(int id) {
        Query query = new Query(Criteria.where("user_id").is(id));
        return userDao.findOne(query);
    }

    @Override
    public PageList<User> getUserPage(int pageSize, int currentPage, String field, int min, int max) {
        Instant start = this.methodStart();
        PageList<User> userPage;

        log.info(String.format("[%s][%s] 查询数据库user开始...", METHOD_GET_User_PAGE, logId));

        // 组装page分页查询条件
        PageRequest pageable = PageRequest.of(currentPage, pageSize);

        Query query = new Query(new Criteria());
        Criteria criteria = null;
        // 判断平台名称是否为空
        if (BlankUtil.isNotBlank(field)) {
            // 不为空 通过关键字查询
            // 查询
            String queryField = UserQueryField.valueOf(field).getField();
            criteria = Criteria.where(queryField).gte(min).lte(max);
            query = new Query(criteria);
        }
        // 为空 直接分页查询
        userPage = userDao.find(query, pageable);

        log.info(String.format("[%s][%s] 查询数据库user结束，耗时：%d ms.", METHOD_GET_User_PAGE, logId,
                Duration.between(start, Instant.now()).toMillis()));

        return userPage;
    }

    @Override
    public long deleteUser(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return userDao.delete(query);
    }

    @Override
    public long updateUser(User user) {
        Query query = new Query(Criteria.where("_id").is(user.get_id()));
        Update update = new Update();
        update.set(UserColumn.USERID.getColumnName(),user.getUser_id());
        update.set(UserColumn.BIRTHDAY.getColumnName(),user.getBirthday());
        update.set(UserColumn.SEX.getColumnName(),user.getSex());
        update.set(UserColumn.TIME.getColumnName(),user.getTime());
        update.set(UserColumn.DISTANCE.getColumnName(),user.getDistance());

        return userDao.update(query, update);
    }


    /**
     * 方法执行
     *
     * @return
     */
    private Instant methodStart() {
        Instant start = Instant.now();
        this.logId = (String) request.getAttribute(Constants.Log.LOG_ID.getCode());
        return start;
    }
}
