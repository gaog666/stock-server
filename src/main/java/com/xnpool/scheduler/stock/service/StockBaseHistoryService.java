/**
 * 版权声明： 版权所有 违者必究 2020
 */
package com.xnpool.scheduler.stock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xnpool.scheduler.stock.entity.StockBaseHistory;
import com.xnpool.scheduler.stock.entity.dto.AvgDto;

import java.util.List;
import java.util.Map;

/**
 * @author gaog
 * @since 2020-05-16 03:10:19
 */
public interface StockBaseHistoryService extends IService<StockBaseHistory> {

    List<AvgDto> selectAvg();
}