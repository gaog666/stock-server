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
 * <p>Table: s_trans_flow - 交易流水</p>
 *
 * @author gaog
 * @since 2020-06-27 11:01:26
 */
@Data
public class STransFlow{

    /** id -  */
	@TableId(type = IdType.AUTO)
    private Long id;

    /** uid - 用户id */
    private Long uid;

    /** f57 - 股票代码 */
    private String f57;

    /** f58 - 股票名称 */
    private String f58;

    /** f43 - 成交价格 */
    private BigDecimal price;

    /** trans_type - 买卖类型：1买入，2卖出 */
    private Integer transType;

    /** deal_num - 成交数量 */
    private Integer dealNum;

    /** 手续费+印花税 */
    private BigDecimal tax;

    /** deal_money - 成交金额 */
    private BigDecimal dealMoney;

    /** before_amount_s - 个股总账前 */
    private BigDecimal beforeAmountS;

    /** after_amount_s - 个股总账后 */
    private BigDecimal afterAmountS;

    /** before_amount_u - 用户总账前 */
    private BigDecimal beforeAmountU;

    /** after_amount_u - 用户总账后 */
    private BigDecimal afterAmountU;

    /** ut -  */
    private Date ut;

}