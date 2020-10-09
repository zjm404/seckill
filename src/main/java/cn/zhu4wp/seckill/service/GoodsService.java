package cn.zhu4wp.seckill.service;

import cn.zhu4wp.seckill.mybatis.dao.GoodsMapper;
import cn.zhu4wp.seckill.entity.SeckillGoods;
import cn.zhu4wp.seckill.vo.GoodsVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZJM
 */
@Service("goodsService")
public class GoodsService {
    /**
     * CAS最大重试次数
     */
    private static final int DEFAULT_MAX_RETRIES = 5;
    @Resource
    GoodsMapper goodsMapper;

    /**
     * 查询商品列表
     * @return
     */
    public List<GoodsVO> listGoodsVO(){
        return goodsMapper.listGoodsVo();
    }

    public GoodsVO getGoodsVOByGoodsId(long goodsId){
        return goodsMapper.getGoodsVoByGoodsId(goodsId);
    }

    /**
     * 使用 CAS + 乐观锁 保证线程安全
     * @param goodsVO
     * @return
     */
    public boolean reduceStock(GoodsVO goodsVO){
        int numAttempts = 0;
        int ret = 0;
        SeckillGoods sg = new SeckillGoods();
        sg.setGoodsId(goodsVO.getId());
        do {
            numAttempts++;
            try{
                sg.setVersion(goodsMapper.getVersionByGoodsId(goodsVO.getId()));
                //CAS 减少库存，返回影响条数，当版本号改变，则修改失败
                ret = goodsMapper.reduceStockByVersion(sg);
            }catch (Exception e){
                e.printStackTrace();
            }
            if(ret != 0){
                break;
            }
        }while(numAttempts < DEFAULT_MAX_RETRIES);
        return ret > 0;
    }

    public long getSeckillStockNum(long goodsId){
        return goodsMapper.selectSeckillGoodsStockCountByGoodsId(goodsId);
    }
}
