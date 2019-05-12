package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Description:
 * @author: Arnold
 * @since: 2019/5/8 8:58
 * @version: v1.0.0
 */
public interface LabelDao extends JpaRepository<Label,String> , JpaSpecificationExecutor<Label> {
}
