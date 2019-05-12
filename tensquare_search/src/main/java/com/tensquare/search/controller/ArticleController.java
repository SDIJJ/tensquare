package com.tensquare.search.controller;

import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleService;
import entity.PageResult;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @author: Arnold
 * @since: 2019/5/10 22:37
 * @version: v1.0.0
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result save(@RequestBody Article article) {
        articleService.save(article);
        return new Result("添加成功");
    }

    /**
     * 根据关键字查询
     *
     * @return
     */
    @GetMapping("/{key}/{page}/{size}")
    public Result findByKey(@PathVariable String key, @PathVariable int page, @PathVariable int size) {
        Page<Article> pages = articleService.findByKey(key, page, size);
        return new Result("关键字插叙成功", new PageResult<>(pages.getTotalElements(), pages.getContent()));
    }
}
