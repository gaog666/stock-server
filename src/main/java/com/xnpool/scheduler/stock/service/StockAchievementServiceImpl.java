/**
 * 版权声明： 版权所有 违者必究 2020
*/
package com.xnpool.scheduler.stock.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xnpool.scheduler.stock.entity.StockAchievement;
import com.xnpool.scheduler.stock.mapper.StockAchievementMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>业务接口实现类</p>
 * <p>券商业绩月报</p>
 *
 * @author gaog
 * @since 2020-06-23 10:22:49
 */
@Service
@Transactional
public class StockAchievementServiceImpl extends ServiceImpl<StockAchievementMapper, StockAchievement> implements StockAchievementService {

    @Override
    @Transactional(noRollbackFor = Exception.class)
    public int insertAchievement(List<StockAchievement> list) {
        return baseMapper.insertAchievement(list);
    }
}