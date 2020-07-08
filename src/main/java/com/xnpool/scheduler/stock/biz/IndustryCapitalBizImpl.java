package com.xnpool.scheduler.stock.biz;

import com.xnpool.scheduler.config.sysParam.ParamConstant;
import com.xnpool.scheduler.stock.entity.StockIndustryCapital;
import com.xnpool.scheduler.stock.service.StockIndustryCapitalService;
import com.xnpool.scheduler.stock.utils.IndustryCapitalBizUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class IndustryCapitalBizImpl implements IndustryCapitalBiz {


    @Autowired
    private ParamConstant paramConstant;
    @Autowired
    private StockIndustryCapitalService industryCapitalService;

    @Override
    public void IndustryCapital() {
        //替换完成url
        String url = paramConstant.getIndustryCapitalUrl().replace("@time", new Date().getTime()+"");
        List<StockIndustryCapital> capitals = IndustryCapitalBizUtil.industryCapital(url);
        industryCapitalService.saveBatch(capitals);
    }
}
