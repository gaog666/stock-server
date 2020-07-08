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
 * <p>Table: stock_custom_code - 自选股</p>
 *
 * @author gaog
 * @since 2020-06-19 09:21:10
 */
@Data
public class StockCustomCode{

    /** id -  */
	@TableId(type = IdType.AUTO)
    private Long id;

    /** f57 - 股票代码 */
    private String f57;

    private BigDecimal price;

    private BigDecimal presale;

    /** update_time -  */
    private Date updateTime;

}