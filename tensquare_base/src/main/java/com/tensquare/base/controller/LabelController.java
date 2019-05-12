package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @Description:
 * @author: Arnold
 * @since: 2019/5/8 8:25
 * @version: v1.0.0
 */
@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    @GetMapping("")
    public Result findAll() {
        List<Label> list = labelService.findAll();
        return new Result("查询全部成功", list);
    }

    @GetMapping("/{labelId}")
    public Result findById(@PathVariable String labelId) {
        Label label = labelService.findById(labelId);
        return new Result("查询成功", label);
    }

    @PostMapping("")
    public Result save(@RequestBody Label label) {
        labelService.save(label);
        return new Result("添加成功");
    }

    @PutMapping("/{labelId}")
    public Result update(@PathVariable String labelId, @RequestBody Label label) {
        label.setId(labelId);
        labelService.update(label);
        return new Result("更新成功");
    }

    @DeleteMapping("/{labelId}")
    public Result delete(@PathVariable String labelId) {
        labelService.deleteById(labelId);
        return new Result("删除成功");
    }

    @PostMapping("/search")
    public Result findSearch(@RequestBody Label label) {
        List<Label> list = labelService.findSearch(label);
        return new Result("条件查询成功", list);
    }

    @PostMapping("/search/{page}/{size}")
    public Result pageQuery(@RequestBody Label label, @PathVariable int page, @PathVariable int size) {
        Page<Label> pages = labelService.pageQuery(label, page, size);
        return new Result("分页查询成功", new PageResult<Label>(pages.getTotalElements(), pages.getContent()));
    }

}
