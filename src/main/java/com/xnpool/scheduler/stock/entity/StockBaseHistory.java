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
 * <p>Table: stock_base_history - 基础表</p>
 *
 * @author gaog
 * @since 2020-05-16 03:10:19
 */
@Data
@TableName("stock_base_history")
public class StockBaseHistory {

    /** id -  */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** f57 - 股票代码 */
    private String f57;

    /** f58 - 股票名称 */
    private String f58;

    /** f43 - 当前价格 */
    private String f43;

    /** f71 - 均价 */
    private String f71;

    /** f170 - 涨幅% */
    private String f170;

    /** f169 - 涨跌 */
    private String f169;

    /** f60 - 昨日收盘价 */
    private String f60;

    /** f46 - 今日开盘价 */
    private String f46;

    /** f44 - 今日最高价 */
    private String f44;

    /** f45 - 今日最低价 */
    private String f45;

    /** f47 - 成交的股票数 */
    private String f47;

    /** f50 - 量比 */
    private String f50;

    /** f168 - 换手率 */
    private String f168;

    /** f162 - 市盈(动) */
    private String f162;

    /** f116 - 总市值 */
    private String f116;

    /** f117 - 流通市值 */
    private String f117;

    /** f167 - 市净 */
    private String f167;

    /** f135 - 主力流入 */
    private String f135;

    /** f136 - 主力流出 */
    private String f136;

    /** f137 - 主力净流入 */
    private String f137;

    /** f49 - 外盘 */
    private String f49;

    /** f161 - 内盘 */
    private String f161;

    /** update_time -  */
    private Date updateTime;

}