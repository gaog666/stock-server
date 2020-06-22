/**
 * 版权声明： 版权所有 违者必究 2020
 */
package com.xnpool.scheduler.stock.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xnpool.scheduler.stock.entity.StockBase;

/**
 * <p>业务接口类</p>
 * <p>基础表</p>
 *
 * @author gaog
 * @since 2020-04-20 06:39:03
 */
public interface StockBaseService extends IService<StockBase> {
    String readStockBase();

    String readStock(String codeKey);
}