package cn.bjtu.edu.common.dao.impl;

import cn.bjtu.edu.common.dao.BaseDao;
import cn.bjtu.edu.common.dao.common.Fixed;
import cn.bjtu.edu.common.dao.common.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.data.mongodb.core.query.Query;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by ryan 20200822
 */
public class MongoBaseDao<T> implements BaseDao<T> {

    @Autowired
    protected MongoTemplate mongoTemplate;
    protected Class<T> entityClass = (Class<T>) ((java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @Override
    public T findOne(Query query) {
        return mongoTemplate.findOne(query, entityClass);
    }

    @Override
    public java.util.List<T> find(Query query) {
        return mongoTemplate.find(query, entityClass);
    }

    @Override
    public PageList<T> find(Query query, Pageable pageable) {
        PageList<T> pageList = new PageList<>();
        if (pageable != null) {
            long totalCount = count(query);
            int pageCount = (int) (totalCount / pageable.getPageSize());
            if (totalCount % pageable.getPageSize() != 0) {
                pageCount += 1;
            }
            query.with(pageable);
            pageList.makePageList(null, pageable.getPageSize(), totalCount, pageable.getPageNumber(), pageCount);
        }
        pageList.setPage(mongoTemplate.find(query, entityClass));
        return pageList;
    }

    /**
     * 通过反射将对象的值设置到update中
     * @param obj
     * @param cur_class
     * @param update
     */
    public void setClassFieldToUpdate(Object obj, Class cur_class, Update update) {
        Field[] obj_fields = cur_class.getDeclaredFields();
        try {
            for (Field field : obj_fields) {
                if (Modifier.isFinal(field.getModifiers()) || Modifier.isPublic(field.getModifiers()) || Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                field.setAccessible(true);
                boolean isFixed = false;
                Annotation annotations[] = field.getAnnotations();
                for (Annotation annotation:annotations){
                    if(annotation.getClass()== Fixed.class){
                        isFixed = true;
                    }
                }
                if(isFixed){//如果字段是不变的则不添加在update中
                    continue;
                }
                update.set(field.getName(), field.get(obj));
            }
            if (cur_class.getSuperclass() != null) {
                setClassFieldToUpdate(obj, cur_class.getSuperclass(), update);//递归获取父类的字段值
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public long update(Query query, Update update) {
        return mongoTemplate.updateFirst(query,update,entityClass).getModifiedCount();
    }

    @Override
    public long updateMulti(Query query, Update update) {
        return mongoTemplate.updateMulti(query,update,entityClass).getModifiedCount();
    }

    @Override
    public T findAndModify(Query query, Update update, boolean isNew) {
        FindAndModifyOptions findAndModifyOptions = new FindAndModifyOptions();
        findAndModifyOptions.returnNew(isNew);
        findAndModifyOptions.upsert(true);
        return mongoTemplate.findAndModify(query,update,findAndModifyOptions,entityClass);
    }

    @Override
    public <T> T insert(T t) {
        return mongoTemplate.insert(t);
    }

    @Override
    public long delete(Query query) {
        return mongoTemplate.remove(query,entityClass).getDeletedCount();
    }

    @Override
    public void insert(java.util.List<T> tList) {
        mongoTemplate.insertAll(tList);
    }

    @Override
    public long count(Query query) {
        return mongoTemplate.count(query,entityClass);
    }

    @Override
    public boolean isExist(Query query) {
        return mongoTemplate.exists(query,entityClass);
    }
}
