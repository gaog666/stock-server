/**
 * 版权声明： 版权所有 违者必究 2020
 */
package com.xnpool.scheduler.stock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xnpool.scheduler.stock.entity.StockBaseHistory;
import com.xnpool.scheduler.stock.entity.dto.AvgDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 表说明：基础表
 *
 * @author gaog
 * @since 2020-05-16 03:10:19
 */
@Mapper
public interface StockBaseHistoryMapper extends BaseMapper<StockBaseHistory> {
    List<AvgDto> selectAvg();
}