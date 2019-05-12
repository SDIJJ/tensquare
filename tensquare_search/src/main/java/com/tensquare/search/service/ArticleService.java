package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @author: Arnold
 * @since: 2019/5/10 22:35
 * @version: v1.0.0
 */
@Service
@Transactional
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;

    public void save(Article article) {
        articleDao.save(article);
    }

    public Page<Article> findByKey(String key, int page, int size) {
        return articleDao.findByTitleOrContentLike(key, key,PageRequest.of(page-1, size));
    }
}
