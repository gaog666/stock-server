/**
 * 版权声明： 版权所有 违者必究 2020
 */
package com.xnpool.scheduler.stock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xnpool.scheduler.stock.entity.StockOpenScreen;
import com.xnpool.scheduler.stock.mapper.StockOpenScreenMapper;


/**
 * <p>业务接口实现类</p>
 * <p>开盘前</p>
 *
 * @author gaog
 * @since 2020-05-10 04:22:58
 */
@Service
@Transactional
public class StockOpenScreenServiceImpl extends ServiceImpl<StockOpenScreenMapper, StockOpenScreen> implements StockOpenScreenService {

}