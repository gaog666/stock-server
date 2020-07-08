/**
 * 版权声明： 版权所有 违者必究 2020
*/
package com.xnpool.scheduler.stock.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;
import java.math.BigDecimal;

/**
 * <p>Table: s_trans_bus - 个股交易</p>
 *
 * @author gaog
 * @since 2020-06-28 10:36:16
 */
@Data
@TableName("s_trans_bus")
public class STransBus{

    /** id -  */
	@TableId(type = IdType.AUTO)
    private Long id;

    /** uid - 用户id */
    private Long uid;

    /** f57 - 股票代码 */
    private String f57;

    /** f58 - 股票名称 */
    private String f58;

    /** price - 成交价格 */
    private BigDecimal price;

    /** presale - 预售价格 */
    private BigDecimal presale;

    /** trans_type - 买卖类型：1买入，2卖出 */
    private Integer transType;

    /** deal_num - 成交数量 */
    private Integer dealNum;

    /** ut -  */
    private Date ut;

}