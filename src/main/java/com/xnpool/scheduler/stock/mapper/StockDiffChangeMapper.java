/**
 * 版权声明： 版权所有 违者必究 2020
*/
package com.xnpool.scheduler.stock.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xnpool.scheduler.stock.entity.StockDiffChange;

import java.util.List;

/**
 * 表说明：异动
 *
 * @author gaog
 * @since 2020-06-24 05:06:42
 */
@Mapper
public interface StockDiffChangeMapper extends BaseMapper<StockDiffChange> {

    void insertDiffChange(List<StockDiffChange> aList);
}