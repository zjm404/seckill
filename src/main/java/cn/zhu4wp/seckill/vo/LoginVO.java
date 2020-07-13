package cn.zhu4wp.seckill.vo;

import lombok.*;

import javax.validation.constraints.NotNull;

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
@ToString
public class LoginVO {
    @NotNull
    private String mobile;
    @NotNull
    private String password;

}
