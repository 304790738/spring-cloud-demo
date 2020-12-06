package cn.bjtu.edu.sso;

import cn.bjtu.edu.common.dao.impl.MongoBaseDao;
import cn.bjtu.edu.sso.entity.User;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends MongoBaseDao<User> {
    /**
     * 更新所有的字段，除了指定字段
     * @param t
     */
    public long updateAll(User t) {
        Update update = new Update();
        setClassFieldToUpdate(t,entityClass,update);
        return mongoTemplate.updateFirst(Query.query(Criteria.where(User._ID).is(t.get_id())),update,entityClass).getModifiedCount();
    }
}
