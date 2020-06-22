/**
 * 版权声明： 版权所有 违者必究 2020
 */
package com.xnpool.scheduler.stock.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xnpool.scheduler.stock.entity.StockAvgScreen;

/**
 * 表说明：平均价选股
 *
 * @author gaog
 * @since 2020-05-29 05:58:47
 */
@Mapper
public interface StockAvgScreenMapper extends BaseMapper<StockAvgScreen> {

}