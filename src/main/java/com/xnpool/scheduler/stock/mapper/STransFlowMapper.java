/**
 * 版权声明： 版权所有 违者必究 2020
*/
package com.xnpool.scheduler.stock.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xnpool.scheduler.stock.entity.STransFlow;

/**
 * 表说明：交易流水
 *
 * @author gaog
 * @since 2020-06-27 11:01:26
 */
@Mapper
public interface STransFlowMapper extends BaseMapper<STransFlow> {
    
}