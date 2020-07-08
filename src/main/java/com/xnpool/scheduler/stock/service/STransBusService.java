/**
 * 版权声明： 版权所有 违者必究 2020
*/
package com.xnpool.scheduler.stock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xnpool.scheduler.stock.entity.STransBus;

import java.util.List;

/**
 * <p>业务接口类</p>
 * <p>个股交易</p>
 *
 * @author gaog
 * @since 2020-06-28 10:36:16
 */
public interface STransBusService extends IService<STransBus> {

    void updateTransType(List<Long> busIds, int transType);
}