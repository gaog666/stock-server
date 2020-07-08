/**
 * 版权声明： 版权所有 违者必究 2020
*/
package com.xnpool.scheduler.stock.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xnpool.scheduler.stock.entity.StockAchievement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 表说明：券商业绩月报
 *
 * @author gaog
 * @since 2020-06-23 10:22:49
 */
@Mapper
public interface StockAchievementMapper extends BaseMapper<StockAchievement> {

    int insertAchievement(List<StockAchievement> list);
}