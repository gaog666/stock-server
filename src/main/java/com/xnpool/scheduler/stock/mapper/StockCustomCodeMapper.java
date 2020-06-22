/**
 * 版权声明： 版权所有 违者必究 2020
*/
package com.xnpool.scheduler.stock.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xnpool.scheduler.stock.entity.StockCustomCode;

/**
 * 表说明：自选股
 *
 * @author gaog
 * @since 2020-06-19 09:21:10
 */
@Mapper
public interface StockCustomCodeMapper extends BaseMapper<StockCustomCode> {
    
}