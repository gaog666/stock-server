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
 * <p>Table: stock_achievement - 券商业绩月报</p>
 *
 * @author gaog
 * @since 2020-06-23 10:22:49
 */
@Data
public class StockAchievement{

    /** id -  */
	@TableId(type = IdType.AUTO)
    private Long id;

    /** f57 - 股票代码 */
    private String f57;

    /** f58 - 股票名称 */
    private String f58;

    /** p1 - 月净利润 */
    private String p1;

    /** p2 - 同比增长 */
    private String p2;

    /** p3 - 环比增长 */
    private String p3;

    /** p4 - 年累计净利润 */
    private String p4;

    /** p5 - 年同比增长 */
    private String p5;

    /** p7 - 净利润 */
    private String p6;

    /** p8 - 同比增长 */
    private String p7;

    /** st -  */
    private Date st;

    /** et -  */
    private Date et;
    /** et -  */
    private Date ut;

}