package cn.zhu4wp.seckill.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @Author zjm
 * @Date 2020/5/23
 * @Description TODO
 * @Version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;
}
