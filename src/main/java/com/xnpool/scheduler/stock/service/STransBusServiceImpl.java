/**
 * 版权声明： 版权所有 违者必究 2020
*/
package com.xnpool.scheduler.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xnpool.scheduler.stock.entity.STransBus;
import com.xnpool.scheduler.stock.mapper.STransBusMapper;

import java.util.List;


/**
 * <p>业务接口实现类</p>
 * <p>个股交易</p>
 *
 * @author gaog
 * @since 2020-06-28 10:36:16
 */
@Service
public class STransBusServiceImpl extends ServiceImpl<STransBusMapper, STransBus> implements STransBusService {

    @Override
    @Transactional
    public void updateTransType(List<Long> busIds, int transType) {
        baseMapper.updateTransType(transType, busIds);
    }
}