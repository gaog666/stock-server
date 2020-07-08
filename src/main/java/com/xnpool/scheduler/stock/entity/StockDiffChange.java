/**
 * 版权声明： 版权所有 违者必究 2020
*/
package com.xnpool.scheduler.stock.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Table: stock_diff_change - 异动</p>
 *
 * @author gaog
 * @since 2020-06-24 05:06:42
 */
@Data
public class StockDiffChange{

    /** id -  */
	@TableId(type = IdType.AUTO)
    private Long id;

    /** f57 - 股票代码 */
    private String f57;

    /** f58 - 股票名称 */
    private String f58;

    /** dt - 日期 */
    private String dt;

    /** tm - 时间 */
    private String tm;

    /** d1 - 类型 */
    private String d1;

    /** d2 - 类型名称 */
    private String d2;

    /** d3 - 详细信息 */
    private String d3;

    /** d4 - 价格 */
    private String d4;

    /** d5 - 涨幅 */
    private String d5;

    /** d6 - 成交量（手） */
    private String d6;

    private Date ut;

}