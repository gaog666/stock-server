/**
 * 版权声明： 版权所有 违者必究 2020
 */
package com.xnpool.scheduler.stock.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xnpool.scheduler.stock.entity.StockBaseHistory;
import com.xnpool.scheduler.stock.entity.dto.AvgDto;
import com.xnpool.scheduler.stock.mapper.StockBaseHistoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * <p>业务接口实现类</p>
 * <p>基础表</p>
 *
 * @author gaog
 * @since 2020-05-16 03:10:19
 */
@Service
@Transactional
public class StockBaseHistoryServiceImpl extends ServiceImpl<StockBaseHistoryMapper, StockBaseHistory> implements StockBaseHistoryService {

    @Override
    public List<AvgDto> selectAvg() {
        return baseMapper.selectAvg();
    }
}