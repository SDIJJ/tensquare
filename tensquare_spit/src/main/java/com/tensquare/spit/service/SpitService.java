package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import util.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @author: Arnold
 * @since: 2019/5/10 8:12
 * @version: v1.0.0
 */
@Service
@Transactional
public class SpitService {

    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(Spit spit) {
        spit.set_id(idWorker.nextId() + "");
        spit.setPublishtime(new Date()); //发布日期
        spit.setVisits(0);
        spit.setThumbup(0);
        spit.setComment(0);
        spit.setShare(0);
        spit.setState("1");  //是否可见
        if (!StringUtils.isEmpty(spit.getParentid())) {
            Update update = new Update();
            update.inc("comment", 1);
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            mongoTemplate.updateFirst(query, update, "spit");
        }
        spitDao.save(spit);
    }

    public void update(Spit spit) {
        spitDao.save(spit);
    }

    public Spit findById(String id) {
        return spitDao.findById(id).get();
    }

    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    public void delete(String id) {
        spitDao.deleteById(id);
    }

    /**
     * 根据上级ID查询吐槽数据（分页）
     *
     * @param parentId
     * @param page
     * @param size
     * @return
     */
    public Page<Spit> findByParentid(String parentId, int page, int size) {
        return spitDao.findByParentid(parentId, PageRequest.of(page - 1, size));
    }

    public void thumbup(String spitId) {
       /* Spit spit = spitDao.findById(spitId).get();
        spit.setThumbup(spit.getThumbup() == null ? 1 : (spit.getThumbup() + 1));
        spitDao.save(spit);*/
        /**
         * 优化效率，两次操作数据库改一次
         */
        // db.spit.update({_id:"1"},{$inc:{thumbup:NumberInt(1)}});
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        Update update = new Update();
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "spit");
    }
}
