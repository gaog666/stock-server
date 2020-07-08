/**
 * 版权声明： 版权所有 违者必究 2020
*/
package com.xnpool.scheduler.stock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xnpool.scheduler.stock.entity.StockAchievement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>业务接口类</p>
 * <p>券商业绩月报</p>
 *
 * @author gaog
 * @since 2020-06-23 10:22:49
 */
public interface StockAchievementService extends IService<StockAchievement> {

    @Transactional(noRollbackFor = Exception.class)
    public int insertAchievement(List<StockAchievement> list);

}