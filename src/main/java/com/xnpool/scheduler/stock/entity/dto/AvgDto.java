package com.xnpool.scheduler.stock.entity.dto;

import com.xnpool.scheduler.stock.entity.StockBase;
import lombok.Data;

import java.util.*;

@Data
public class AvgDto {
    private String f57;

    private String f58;

    private int numb;

    private String sumf137;

    private Date updateTime;

    private StockBase stockBase;
}
