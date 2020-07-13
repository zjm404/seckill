package cn.zhu4wp.seckill.mapper;

import cn.zhu4wp.seckill.bean.Goods;
import cn.zhu4wp.seckill.bean.SeckillGoods;
import cn.zhu4wp.seckill.vo.GoodsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Author zjm
 * @Date 2020/5/23
 * @Description TODO
 * @Version 1.0
 */
@Mapper
public interface GoodsMapper {
    @Select("SELECT * FROM sk_goods WHERE id = #{id}")
    public Goods selectGoodsById(long id);
    @Select("select g.*, sg.stock_count, sg.start_date, sg.end_date, sg.seckill_price, sg.version  from sk_goods_seckill sg left join sk_goods g  on sg.goods_id = g.id where g.id = #{goodsId}")
    public GoodsVO getGoodsVoByGoodsId(@Param("goodsId") long goodsId);
    @Update("UPDATE sk_goods SET stock_count = stock_count-1 WHERE goods_id = #{goodsId}")
    public int reduceStock(SeckillGoods sg);
}
