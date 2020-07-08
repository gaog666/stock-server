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
 * <p>Table: s_trans - 个股总账</p>
 *
 * @author gaog
 * @since 2020-06-27 11:01:21
 */
@Data
public class STrans{

    /** id -  */
	@TableId(type = IdType.AUTO)
    private Long id;

    /** 用户id -  */
    private Long uid;

    /** f57 - 股票代码 */
    private String f57;

    /** f58 - 股票名称 */
    private String f58;

    /** hold - 持股数量 */
    private Integer hold;

    /** tax 手续费+印花税 总账 */
    private BigDecimal tax;

    /** amount - 个股总账 */
    private BigDecimal amount;

    /** ut -  */
    private Date ut;

}