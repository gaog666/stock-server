/**
 * 版权声明： 版权所有 违者必究 2020
*/
package com.xnpool.scheduler.stock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xnpool.scheduler.stock.entity.STrans;

/**
 * <p>业务接口类</p>
 * <p>个股总账</p>
 *
 * @author gaog
 * @since 2020-06-27 11:01:21
 */
public interface STransService extends IService<STrans> {

    STrans getTrans(Long userId, String code);
}