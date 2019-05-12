package com.tensquare.base.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description:
 * @author: Arnold
 * @since: 2019/5/8 8:40
 * @version: v1.0.0
 */
@Entity
@Table(name = "tb_label")
@Data
public class Label implements Serializable {
    @Id
    private String id;
    private String labelname;   //标签名字
    private String state;   //状态
    private int count;  //使用数量
    private String recommend;   //是否推荐
    private int fans;   //粉丝数
}
