package cn.zhu4wp.seckill.mapper;

import cn.zhu4wp.seckill.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Author zjm
 * @Date 2020/5/24
 * @Description TODO
 * @Version 1.0
 */
@Mapper
public interface UserMapper {
    /**
     * 根据id查找user
     * @param id user的id
     * @return
     */
    @Select("SELECT * FROM sk_user WHERE id = #{id}")
    User getById(long id);

    /**
     * 更新密码
     * @param newUser
     */
    @Update("UPDATE sk_user SET password = #{password} WHERE id = #{id}")
    void update(User newUser);
}
