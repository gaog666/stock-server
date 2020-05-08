package com.xnpool.scheduler.config.sysParam;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 常量类
 * @author gaog
 * @date 2019-12-10  19:01
 */
@Component
@ConfigurationProperties(prefix="param")
public class ParamConstant {
	
	private String val;
	private String stockUrl0;
	private String stockUrl6;
	private UserTest bean;
	private String[] arr1;
	private String[] arr2;
	private List<String> list;
	private Map<String, String> map;
	private List<Map<String, String>> listmap;
	
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	public String getStockUrl0() {
		return stockUrl0;
	}
	public void setStockUrl0(String stockUrl0) {
		this.stockUrl0 = stockUrl0;
	}
	public String getStockUrl6() {
		return stockUrl6;
	}
	public void setStockUrl6(String stockUrl6) {
		this.stockUrl6 = stockUrl6;
	}
	public UserTest getBean() {
		return bean;
	}
	public void setBean(UserTest bean) {
		this.bean = bean;
	}
	public String[] getArr1() {
		return arr1;
	}
	public void setArr1(String[] arr1) {
		this.arr1 = arr1;
	}
	public String[] getArr2() {
		return arr2;
	}
	public void setArr2(String[] arr2) {
		this.arr2 = arr2;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	public List<Map<String, String>> getListmap() {
		return listmap;
	}
	public void setListmap(List<Map<String, String>> listmap) {
		this.listmap = listmap;
	}
	@Override
	public String toString() {
		return "CustomParam [val=" + val + ", bean=" + bean + ", arr1=" + Arrays.toString(arr1) + ", arr2="
				+ Arrays.toString(arr2) + ", list=" + list + ", map=" + map + ", listmap=" + listmap + "]";
	}
}