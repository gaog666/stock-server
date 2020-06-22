/**
 * 版权声明： 版权所有 违者必究 2020
 */
package com.xnpool.scheduler.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xnpool.scheduler.stock.entity.StockAvgScreen;
import com.xnpool.scheduler.stock.mapper.StockAvgScreenMapper;


/**
 * <p>业务接口实现类</p>
 * <p>平均价选股</p>
 *
 * @author gaog
 * @since 2020-05-29 05:58:47
 */
@Service
@Transactional
public class StockAvgScreenServiceImpl extends ServiceImpl<StockAvgScreenMapper, StockAvgScreen> implements StockAvgScreenService {

}