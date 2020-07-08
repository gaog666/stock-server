/**
 * 版权声明： 版权所有 违者必究 2020
*/
package com.xnpool.scheduler.stock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xnpool.scheduler.stock.entity.StockDiffChange;

import java.util.List;

/**
 * <p>业务接口类</p>
 * <p>异动</p>
 *
 * @author gaog
 * @since 2020-06-24 05:06:42
 */
public interface StockDiffChangeService extends IService<StockDiffChange> {

    void insertDiffChange(List<StockDiffChange> aList);
}