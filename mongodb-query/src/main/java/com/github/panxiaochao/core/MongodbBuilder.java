package com.github.panxiaochao.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 
 * @Title MongodbBuilder.java
 * @Description TODO()
 * @Author Lypxc
 * @Date 2019年4月14日
 * @Version 1.0
 */
public class MongodbBuilder {
	private Map<String, Object> operatorMap;
	private LinkedHashMap<String, Object> linkAndMap;
	private LinkedHashMap<String, Object> linkOrMap;
	private LinkedHashMap<String, Object> linkOrderMap;
	private int serialnum;

	public MongodbBuilder() {
		this.operatorMap = new HashMap<String, Object>();
		this.linkAndMap = new LinkedHashMap<String, Object>();
		this.linkOrMap = new LinkedHashMap<String, Object>();
		this.linkOrderMap = new LinkedHashMap<String, Object>();
		this.serialnum = 0;
	}

	/**
	 * 
	 * @Description （_id=""，这个id是mongodb中的id，如果需要项目id，请使用andEq）
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public MongodbBuilder idEq(Object value) {
		linkAndMap.put("_id" + "#" + incSerialnum(), getRuleJson("$eq", value));
		return this;
	}

	/**
	 * 
	 * @Description （key=value）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public MongodbBuilder andEq(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$eq", value));
		return this;
	}

	/**
	 * 
	 * @Description （key!=value）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public MongodbBuilder andNEq(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$ne", value));
		return this;
	}

	/**
	 * 
	 * @Description （key>value）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public MongodbBuilder andGt(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$gt", value));
		return this;
	}

	/**
	 * 
	 * @Description （key>=value）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public MongodbBuilder andGte(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$gte", value));
		return this;
	}

	/**
	 * 
	 * @Description （key<value）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public MongodbBuilder andLt(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$lt", value));
		return this;
	}

	/**
	 * 
	 * @Description （key<=value）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public MongodbBuilder andLte(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$lte", value));
		return this;
	}

	/**
	 * 
	 * @Description （key in [value1, value2, ... valueN]）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月19日
	 */
	public MongodbBuilder andIn(String field, Object... o) {
		if (o.length > 1 && o[1] instanceof Collection) {
			try {
				throw new Exception("You can only pass in one argument of type " + o[1].getClass().getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		andIn(field, Arrays.asList(o));
		return this;
	}

	/**
	 * 
	 * @Description （key in [value1, value2, ... valueN]）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月19日
	 */
	public MongodbBuilder andIn(String field, Collection<?> c) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$in", c));
		return this;
	}

	/**
	 * 
	 * @Description （key not in [value1, value2, ... valueN]）
	 * @param o
	 * @return
	 * @date 2019年3月19日
	 */
	public MongodbBuilder andNIn(String field, Object... o) {
		if (o.length > 1 && o[1] instanceof Collection) {
			try {
				throw new Exception("You can only pass in one argument of type " + o[1].getClass().getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return andNIn(field, Arrays.asList(o));
	}

	/**
	 * 
	 * @Description （key not in [value1,<value2, ... valueN]）
	 * @param o
	 * @return
	 * @date 2019年3月19日
	 */
	public MongodbBuilder andNIn(String field, Collection<?> c) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$nin", c));
		return this;
	}

	/**
	 * 
	 * @Description （判断字段是否存在）
	 * @param field
	 * @param b
	 * @return
	 * @date 2019年3月19日
	 */
	public MongodbBuilder andExists(String field, boolean b) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$exists", b));
		return this;
	}

	/**
	 * 
	 * @Description （betwwen: kev >= gteVal and key <= lteVal）
	 * @param field
	 *            字段
	 * @param gteVal
	 *            大于等于的值
	 * @param lteVal
	 *            小于等于的值
	 * @return
	 * @date 2019年4月16日
	 */
	public MongodbBuilder andBetween(String field, Object gteVal, Object lteVal) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$gte", gteVal));
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$lte", lteVal));
		return this;
	}

	public MongodbBuilder andRegex(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$regex", value));
		return this;
	}
	
	

	/**
	 * 
	 * @Description （key=value）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public MongodbBuilder orEq(String field, Object value) {
		linkOrMap.put(field + "#" + incSerialnum(), getRuleJson("$eq", value));
		return this;
	}

	/**
	 * 
	 * @Description （key!=value）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public MongodbBuilder orNEq(String field, Object value) {
		linkOrMap.put(field + "#" + incSerialnum(), getRuleJson("$ne", value));
		return this;
	}

	/**
	 * 
	 * @Description （key>value）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public MongodbBuilder orGt(String field, Object value) {
		linkOrMap.put(field + "#" + incSerialnum(), getRuleJson("$gt", value));
		return this;
	}

	/**
	 * 
	 * @Description （key>=value）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public MongodbBuilder orGte(String field, Object value) {
		linkOrMap.put(field + "#" + incSerialnum(), getRuleJson("$gte", value));
		return this;
	}

	/**
	 * 
	 * @Description （key<value）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public MongodbBuilder orLt(String field, Object value) {
		linkOrMap.put(field + "#" + incSerialnum(), getRuleJson("$lt", value));
		return this;
	}

	/**
	 * 
	 * @Description （key<=value）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public MongodbBuilder orLte(String field, Object value) {
		linkOrMap.put(field + "#" + incSerialnum(), getRuleJson("$lte", value));
		return this;
	}

	/**
	 * 
	 * @Description （升序=ASC，1代表升序）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月26日
	 */
	public MongodbBuilder orderAsc(String field) {
		linkOrderMap.put(field + "#" + incSerialnum(), 1);
		return this;
	}

	/**
	 * 
	 * @Description （降序=DESC，-1代表降序）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月26日
	 */
	public MongodbBuilder orderDesc(String field) {
		linkOrderMap.put(field + "#" + incSerialnum(), -1);
		return this;
	}

	/**
	 * 
	 * @Description （往and里加or）
	 * @param field
	 * @return
	 * @date 2019年4月14日
	 */
	public MongodbBuilder addAndOr(String field) {

		return this;
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

	public MongodbQuery build() {
		return new MongodbQuery(operatorMap, linkAndMap, linkOrMap, linkOrderMap);
	}

	public static void main(String[] args) {
		MongodbQuery m = new MongodbBuilder().andEq("city", "hz").andRegex("city1", "tz").andBetween("age", 11, 20)
				.build();
		System.out.println(m.toString());

		MongodbQuery m1 = new MongodbBuilder().andEq("city", "hz").andEq("city1", "tz").andBetween("age", 11, 20)
				.build();
		System.out.println(m1.toString());
	}
}
