/**
 * 版权声明： 版权所有 违者必究 2020
*/
package com.xnpool.scheduler.stock.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xnpool.scheduler.stock.entity.STrans;

/**
 * 表说明：个股总账
 *
 * @author gaog
 * @since 2020-06-27 11:01:21
 */
@Mapper
public interface STransMapper extends BaseMapper<STrans> {
    
}