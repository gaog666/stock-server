/**
 * 版权声明： 版权所有 违者必究 2020
*/
package com.xnpool.scheduler.stock.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;
import java.math.BigDecimal;

/**
 * <p>Table: stock_user - 用户总账</p>
 *
 * @author gaog
 * @since 2020-06-27 11:01:11
 */
@Data
public class StockUser{

    /** id -  */
	@TableId(type = IdType.AUTO)
    private Long id;

    /** username - 用户名 */
    private String username;

    /** (利息总账)手续费+印花税 */
    private BigDecimal tax;
    /** amount - 总账 */
    private BigDecimal amount;

    /** ut -  */
    private Date ut;

}