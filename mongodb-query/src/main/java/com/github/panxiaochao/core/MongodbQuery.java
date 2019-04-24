package com.github.panxiaochao.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

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

	public MongodbQuery(MongodbBuilder mongodbBuilder) {
		this.operatorMap = new HashMap<String, Object>();
		this.linkAndMap = mongodbBuilder.getLinkAndMap();
		this.linkOrMap = mongodbBuilder.getLinkOrMap();
		this.linkOrderMap = mongodbBuilder.getLinkOrderMap();
		this.serialnum = 0;
	}

	public void idEq(Object value) {
		linkAndMap.put("_id" + "#" + incSerialnum(), getRuleJson("$eq", value));
	}

	public void andEq(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$eq", value));
	}

	public void andNEq(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$ne", value));
	}

	public void andGt(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$gt", value));
	}

	public void andGte(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$gte", value));
	}

	public void andLt(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$lt", value));
	}

	public void andLte(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$lte", value));
	}

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

	public void andIn(String field, Collection<?> c) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$in", c));
	}

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

	public void andNIn(String field, Collection<?> c) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$nin", c));
	}

	public void andExists(String field, boolean b) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$exists", b));
	}

	public void andBetween(String field, Object gteVal, Object lteVal) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$gte", gteVal));
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$lte", lteVal));
	}

	public void orEq(String field, Object value) {
		linkOrMap.put(field + "#" + incSerialnum(), getRuleJson("$eq", value));
	}

	public void orNEq(String field, Object value) {
		linkOrMap.put(field + "#" + incSerialnum(), getRuleJson("$ne", value));
	}

	public void orGt(String field, Object value) {
		linkOrMap.put(field + "#" + incSerialnum(), getRuleJson("$gt", value));
	}

	public void orGte(String field, Object value) {
		linkOrMap.put(field + "#" + incSerialnum(), getRuleJson("$gte", value));
	}

	public void orLt(String field, Object value) {
		linkOrMap.put(field + "#" + incSerialnum(), getRuleJson("$lt", value));
	}

	public void orLte(String field, Object value) {
		linkOrMap.put(field + "#" + incSerialnum(), getRuleJson("$lte", value));
	}

	public void orderAsc(String field) {
		linkOrderMap.put(field + "#" + incSerialnum(), 1);
	}

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
