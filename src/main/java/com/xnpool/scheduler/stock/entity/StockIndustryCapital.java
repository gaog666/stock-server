/**
 * 版权声明： 版权所有 违者必究 2020
*/
package com.xnpool.scheduler.stock.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.text.Bidi;
import java.util.Date;
import java.math.BigDecimal;

/**
 * <p>Table: stock_industry_capital - 行业资金</p>
 *
 * @author gaog
 * @since 2020-06-26 10:17:52
 */
@Data
public class StockIndustryCapital{

    /** id -  */
	@TableId(type = IdType.AUTO)
    private Long id;

    /** f12 - 行业代码 */
    private String f12;

    /** f14 - 行业 */
    private String f14;

    /** f3 - 今日涨跌幅% */
    private String f3;

    /** f62 - 今日主力净流入 */
    private BigDecimal f62;

    /** f184 - 净占比% */
    private String f184;

    /** f66 - 超大单净流入 */
    private BigDecimal f66;

    /** f69 - 超大单净占比 */
    private String f69;

    /** f204 - 今日主力净流入最大股 */
    private String f204;

    /** f205 - 今日主力净流入最大股代码 */
    private String f205;

    /** ut -  */
    private Date ut;

}