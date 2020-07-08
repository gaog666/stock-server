/**
 * 版权声明： 版权所有 违者必究 2020
*/
package com.xnpool.scheduler.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xnpool.scheduler.stock.entity.StockIndustryCapital;
import com.xnpool.scheduler.stock.mapper.StockIndustryCapitalMapper;


/**
 * <p>业务接口实现类</p>
 * <p>行业资金</p>
 *
 * @author gaog
 * @since 2020-06-26 10:17:52
 */
@Service
@Transactional
public class StockIndustryCapitalServiceImpl extends ServiceImpl<StockIndustryCapitalMapper, StockIndustryCapital> implements StockIndustryCapitalService {

}