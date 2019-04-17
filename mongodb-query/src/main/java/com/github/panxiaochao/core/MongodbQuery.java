package com.github.panxiaochao.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @Title MongodbQuery.java
 * @Description TODO()
 * @Author Lypxc
 * @Date 2019年4月14日
 * @Version 1.0
 */
public class MongodbQuery {
	private Map<String, Object> operatorMap;
	private LinkedHashMap<String, Object> linkAndMap;
	private LinkedHashMap<String, Object> linkOrMap;
	private LinkedHashMap<String, Object> linkOrderMap;
	private int serialnum;

	public MongodbQuery() {
		this.operatorMap = new HashMap<String, Object>();
		this.linkAndMap = new LinkedHashMap<String, Object>();
		this.linkOrMap = new LinkedHashMap<String, Object>();
		this.linkOrderMap = new LinkedHashMap<String, Object>();
		this.serialnum = 0;
	}

	public MongodbQuery(Map<String, Object> operatorMap, LinkedHashMap<String, Object> linkAndMap,
			LinkedHashMap<String, Object> linkOrMap, LinkedHashMap<String, Object> linkOrderMap) {
		this.operatorMap = operatorMap;
		this.linkAndMap = linkAndMap;
		this.linkOrMap = linkOrMap;
		this.linkOrderMap = linkOrderMap;
		this.serialnum = 0;
	}

	/**
	 * 
	 * @Description （id=""）
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public void idEq(Object value) {
		linkAndMap.put("_id" + "#" + incSerialnum(), getRuleJson("$eq", value));
	}

	/**
	 * 
	 * @Description （key=value）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public void andEq(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$eq", value));
	}

	/**
	 * 
	 * @Description （key!=value）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public void andNEq(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$ne", value));
	}

	/**
	 * 
	 * @Description （key>value）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public void andGt(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$gt", value));
	}

	/**
	 * 
	 * @Description （key>=value）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public void andGte(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$gte", value));
	}

	/**
	 * 
	 * @Description （key<value）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public void andLt(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$lt", value));
	}

	/**
	 * 
	 * @Description （key<=value）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public void andLte(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$lte", value));
	}

	/**
	 * 
	 * @Description （key in [value1, value2, ... valueN]）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月19日
	 */
	public void andIn(String field, Object... o) {
		if (o.length > 1 && o[1] instanceof Collection) {
			try {
				throw new Exception("You can only pass in one argument of type " + o[1].getClass().getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		andIn(field, Arrays.asList(o));
	}

	/**
	 * 
	 * @Description （key in [value1, value2, ... valueN]）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月19日
	 */
	public void andIn(String field, Collection<?> c) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$in", c));
	}

	/**
	 * 
	 * @Description （key not in [value1, value2, ... valueN]）
	 * @param o
	 * @return
	 * @date 2019年3月19日
	 */
	public void andNIn(String field, Object... o) {
		if (o.length > 1 && o[1] instanceof Collection) {
			try {
				throw new Exception("You can only pass in one argument of type " + o[1].getClass().getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		andNIn(field, Arrays.asList(o));
	}

	/**
	 * 
	 * @Description （key not in [value1,<value2, ... valueN]）
	 * @param o
	 * @return
	 * @date 2019年3月19日
	 */
	public void andNIn(String field, Collection<?> c) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$nin", c));
	}

	/**
	 * 
	 * @Description （判断字段是否存在）
	 * @param field
	 * @param b
	 * @return
	 * @date 2019年3月19日
	 */
	public void andExists(String field, boolean b) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$exists", b));
	}
	
	/**
	 * 
	 * @Description （betwwen: kev >= val1 and key <= val2）
	 * @param field
	 * @param gteVal
	 *            大于等于的值
	 * @param lteVal
	 *            小于等于的值
	 * @return
	 * @date 2019年4月16日
	 */
	public void andBetween(String field, Object gteVal, Object lteVal) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$gte", gteVal));
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$lte", lteVal));
	}

	/**
	 * 
	 * @Description （key=value）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public void orEq(String field, Object value) {
		linkOrMap.put(field + "#" + incSerialnum(), getRuleJson("$eq", value));
	}

	/**
	 * 
	 * @Description （key!=value）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public void orNEq(String field, Object value) {
		linkOrMap.put(field + "#" + incSerialnum(), getRuleJson("$ne", value));
	}

	/**
	 * 
	 * @Description （key>value）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public void orGt(String field, Object value) {
		linkOrMap.put(field + "#" + incSerialnum(), getRuleJson("$gt", value));
	}

	/**
	 * 
	 * @Description （key>=value）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public void orGte(String field, Object value) {
		linkOrMap.put(field + "#" + incSerialnum(), getRuleJson("$gte", value));
	}

	/**
	 * 
	 * @Description （key<value）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public void orLt(String field, Object value) {
		linkOrMap.put(field + "#" + incSerialnum(), getRuleJson("$lt", value));
	}

	/**
	 * 
	 * @Description （key<=value）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public void orLte(String field, Object value) {
		linkOrMap.put(field + "#" + incSerialnum(), getRuleJson("$lte", value));
	}

	/**
	 * 
	 * @Description （升序=ASC，1代表升序）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月26日
	 */
	public void orderAsc(String field) {
		linkOrderMap.put(field + "#" + incSerialnum(), 1);
	}

	/**
	 * 
	 * @Description （降序=DESC，-1代表降序）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月26日
	 */
	public void orderDesc(String field) {
		linkOrderMap.put(field + "#" + incSerialnum(), -1);
	}

	public String toString() {
		if (!linkAndMap.isEmpty()) {
			operatorMap.put("$and", linkAndMap);
		}
		if (!linkOrMap.isEmpty()) {
			operatorMap.put("$or", linkOrMap);
		}
		if (!linkOrderMap.isEmpty()) {
			operatorMap.put("$order", linkOrderMap);
		}
		return JSON.toJSONString(operatorMap);
	}

	private Map<String, Object> getRuleJson(String symbol, Object value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(symbol, value);
		return map;
	}

	private int incSerialnum() {
		serialnum += 1;
		return serialnum;
	}

}
