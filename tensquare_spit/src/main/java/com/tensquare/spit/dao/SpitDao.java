package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Description:
 * @author: Arnold
 * @since: 2019/5/10 8:11
 * @version: v1.0.0
 */
public interface SpitDao extends MongoRepository<Spit, String> {
    Page<Spit> findByParentid(String parentId, Pageable pageable);

/*//    @Query("db.spit.update({'_id':?0},{$inc:{'thumbup':NumberInt(1)}})")
    void thumbup(String id);
//    @Query("db.spit.find()")
    List<Spit> testMongo();*/
}
