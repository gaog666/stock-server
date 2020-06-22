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
 * <p>Table: stock_avg_screen - 平均价选股</p>
 *
 * @author gaog
 * @since 2020-05-29 05:58:47
 */
@Data
@TableName("stock_avg_screen")
public class StockAvgScreen {

    /** id -  */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** f57 - 股票代码 */
    private String f57;

    /** f58 - 股票名称 */
    private String f58;

    /** f43 - 当前价格 */
    private String f43;
    private String f168;

    private int numb;

    private String sumf137;

    /** update_time -  */
    private Date updateTime;

}