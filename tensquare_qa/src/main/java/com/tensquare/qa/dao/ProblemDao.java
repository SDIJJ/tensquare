package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 *
 * @author Administrator
 */
public interface ProblemDao extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {
    @Query(value = "select * from tb_problem,tb_pl where id = problemid and labelid = ? order by createtime desc", nativeQuery = true)
    Page<Problem> newlist(String labelId, Pageable pageable);

    @Query(value = "select * from tb_problem,tb_pl where id=problemid and labelid =? order by reply desc ", nativeQuery = true)
    Page<Problem> hotlist(String labelId, Pageable pageable);

    @Query(value = "select * from tb_problem,tb_pl where id=problemid and labelid =? and reply=0 order by createtime desc ", nativeQuery = true)
    Page<Problem> waitlist(String labelId, Pageable pageable);
}
