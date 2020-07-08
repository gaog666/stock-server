/**
 * 版权声明： 版权所有 违者必究 2020
*/
package com.xnpool.scheduler.stock.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xnpool.scheduler.stock.entity.StockUser;

/**
 * 表说明：用户总账
 *
 * @author gaog
 * @since 2020-06-27 11:01:11
 */
@Mapper
public interface StockUserMapper extends BaseMapper<StockUser> {
    
}