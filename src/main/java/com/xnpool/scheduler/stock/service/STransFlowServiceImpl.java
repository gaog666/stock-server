/**
 * 版权声明： 版权所有 违者必究 2020
*/
package com.xnpool.scheduler.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xnpool.scheduler.stock.entity.STransFlow;
import com.xnpool.scheduler.stock.mapper.STransFlowMapper;


/**
 * <p>业务接口实现类</p>
 * <p>交易流水</p>
 *
 * @author gaog
 * @since 2020-06-27 11:01:26
 */
@Service
@Transactional
public class STransFlowServiceImpl extends ServiceImpl<STransFlowMapper, STransFlow> implements STransFlowService {

}