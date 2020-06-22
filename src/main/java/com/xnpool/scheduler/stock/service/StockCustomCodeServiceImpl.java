/**
 * 版权声明： 版权所有 违者必究 2020
*/
package com.xnpool.scheduler.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xnpool.scheduler.stock.entity.StockCustomCode;
import com.xnpool.scheduler.stock.mapper.StockCustomCodeMapper;


/**
 * <p>业务接口实现类</p>
 * <p>自选股</p>
 *
 * @author gaog
 * @since 2020-06-19 09:21:10
 */
@Service
@Transactional
public class StockCustomCodeServiceImpl extends ServiceImpl<StockCustomCodeMapper, StockCustomCode> implements StockCustomCodeService {

}