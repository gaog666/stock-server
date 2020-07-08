package com.xnpool.scheduler.config.sysParam;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 常量类
 *
 * @author gaog
 * @date 2019-12-10  19:01
 */
@Data
@Component
@ConfigurationProperties(prefix = "param")
public class ParamConstant {

    private String val;
    private Integer increase;
    private Integer transNum;
    private String stockUrl0;
    private String stockUrl6;
    private String brokerAchievementUrl;
    private String industryCapitalUrl;
    private String diffChangeUrl;
    private UserTest bean;
    private String[] arr1;
    private String[] arr2;
    private List<String> list;
    private Map<String, String> map;
    private List<Map<String, String>> listmap;


    @Override
    public String toString() {
        return "CustomParam [val=" + val + ", bean=" + bean + ", arr1=" + Arrays.toString(arr1) + ", arr2="
                + Arrays.toString(arr2) + ", list=" + list + ", map=" + map + ", listmap=" + listmap + "]";
    }
}