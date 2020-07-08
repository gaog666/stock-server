/**
 * 版权声明： 版权所有 违者必究 2020
*/
package com.xnpool.scheduler.stock.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xnpool.scheduler.stock.entity.STransBus;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;

import java.util.List;

/**
 * 表说明：个股交易
 *
 * @author gaog
 * @since 2020-06-28 10:36:16
 */
@Mapper
public interface STransBusMapper extends BaseMapper<STransBus> {

    void updateTransType(@Param("transType")int transType, @Param("busIds") List<Long> busIds);
}