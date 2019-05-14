package com.tensquare.friend.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description:
 * @author: Arnold
 * @since: 2019/5/13 10:43
 * @version: v1.0.0
 */
@Entity
@Data
@Table(name = "tb_friend")
@IdClass(Friend.class)
public class Friend  implements Serializable {
    @Id
    private String userid;
    @Id
    private String friendid;
    private String islike;
}
