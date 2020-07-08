/**
 * 版权声明： 版权所有 违者必究 2020
*/
package com.xnpool.scheduler.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xnpool.scheduler.stock.entity.StockDiffChange;
import com.xnpool.scheduler.stock.mapper.StockDiffChangeMapper;

import java.util.List;


/**
 * <p>业务接口实现类</p>
 * <p>异动</p>
 *
 * @author gaog
 * @since 2020-06-24 05:06:42
 */
@Service
@Transactional
public class StockDiffChangeServiceImpl extends ServiceImpl<StockDiffChangeMapper, StockDiffChange> implements StockDiffChangeService {

    @Override
    public void insertDiffChange(List<StockDiffChange> aList) {
        baseMapper.insertDiffChange(aList);
    }
}