package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @author: Arnold
 * @since: 2019/5/10 8:18
 * @version: v1.0.0
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping
    public Result save(@RequestBody Spit spit) {
        spitService.save(spit);
        return new Result("添加成功");
    }

    @GetMapping
    public Result findAll() {
        List<Spit> list = spitService.findAll();
        return new Result("查询全部成功", list);
    }

    @GetMapping("{spitId}")
    public Result findById(@PathVariable String spitId) {
        return new Result("查询成功", spitService.findById(spitId));
    }

    @PutMapping("/{spitId}")
    public Result update(@PathVariable String spitId, @RequestBody Spit spit) {
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result("更新成功");
    }

    @DeleteMapping("/{spitId}")
    public Result delete(@PathVariable String spitId) {
        spitService.delete(spitId);
        return new Result("删除成功");
    }

    @GetMapping("/comment/{parentid}/{page}/{size}")
    public Result findByparentId(@PathVariable String parentid, @PathVariable int page, @PathVariable int size) {
        Page<Spit> pages = spitService.findByParentid(parentid, page, size);
        return new Result("查询成功", new PageResult<>(pages.getTotalElements(), pages.getContent()));
    }

    @PutMapping("/thumbup/{spitId}")
    public Result thumbup(@PathVariable String spitId) {
        //TODO 当前登陆用户userid
        String userid = "123";
        if (redisTemplate.opsForValue().get("thumbup_" + userid + "_" + spitId) != null)
            return new Result(false, StatusCode.REPERROR, "点赞重复");
        spitService.thumbup(spitId);
        redisTemplate.opsForValue().set("thumbup_" + userid + "_" + spitId, true);
        return new Result("点赞成功");
    }

}
