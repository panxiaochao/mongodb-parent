package com.github.panxiaochao.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class MongodbBuilder {
	private LinkedHashMap<String, Object> linkAndMap;
	private LinkedHashMap<String, Object> linkOrMap;
	private LinkedHashMap<String, Object> linkOrderMap;
	private int serialnum;

	public MongodbBuilder() {
		this.linkAndMap = new LinkedHashMap<String, Object>();
		this.linkOrMap = new LinkedHashMap<String, Object>();
		this.linkOrderMap = new LinkedHashMap<String, Object>();
		this.serialnum = 0;
	}

	public MongodbBuilder idEq(Object value) {
		linkAndMap.put("_id" + "#" + incSerialnum(), getRuleJson("$eq", value));
		return this;
	}

	public MongodbBuilder andEq(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$eq", value));
		return this;
	}

	public MongodbBuilder andNEq(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$ne", value));
		return this;
	}

	public MongodbBuilder andGt(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$gt", value));
		return this;
	}

	public MongodbBuilder andGte(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$gte", value));
		return this;
	}

	public MongodbBuilder andLt(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$lt", value));
		return this;
	}

	public MongodbBuilder andLte(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$lte", value));
		return this;
	}

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

	public MongodbBuilder andIn(String field, Collection<?> c) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$in", c));
		return this;
	}

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

	public MongodbBuilder andNIn(String field, Collection<?> c) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$nin", c));
		return this;
	}

	public MongodbBuilder andExists(String field, boolean b) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$exists", b));
		return this;
	}

	public MongodbBuilder andBetween(String field, Object gteVal, Object lteVal) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$gte", gteVal));
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$lte", lteVal));
		return this;
	}

	public MongodbBuilder andRegex(String field, Object value) {
		linkAndMap.put(field + "#" + incSerialnum(), getRuleJson("$regex", value));
		return this;
	}

	public MongodbBuilder orEq(String field, Object value) {
		linkOrMap.put(field + "#" + incSerialnum(), getRuleJson("$eq", value));
		return this;
	}

	public MongodbBuilder orNEq(String field, Object value) {
		linkOrMap.put(field + "#" + incSerialnum(), getRuleJson("$ne", value));
		return this;
	}

	public MongodbBuilder orGt(String field, Object value) {
		linkOrMap.put(field + "#" + incSerialnum(), getRuleJson("$gt", value));
		return this;
	}

	public MongodbBuilder orGte(String field, Object value) {
		linkOrMap.put(field + "#" + incSerialnum(), getRuleJson("$gte", value));
		return this;
	}

	public MongodbBuilder orLt(String field, Object value) {
		linkOrMap.put(field + "#" + incSerialnum(), getRuleJson("$lt", value));
		return this;
	}

	public MongodbBuilder orLte(String field, Object value) {
		linkOrMap.put(field + "#" + incSerialnum(), getRuleJson("$lte", value));
		return this;
	}

	public MongodbBuilder orderAsc(String field) {
		linkOrderMap.put(field + "#" + incSerialnum(), 1);
		return this;
	}

	public MongodbBuilder orderDesc(String field) {
		linkOrderMap.put(field + "#" + incSerialnum(), -1);
		return this;
	}

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

	public LinkedHashMap<String, Object> getLinkAndMap() {
		return linkAndMap;
	}

	public void setLinkAndMap(LinkedHashMap<String, Object> linkAndMap) {
		this.linkAndMap = linkAndMap;
	}

	public LinkedHashMap<String, Object> getLinkOrMap() {
		return linkOrMap;
	}

	public void setLinkOrMap(LinkedHashMap<String, Object> linkOrMap) {
		this.linkOrMap = linkOrMap;
	}

	public LinkedHashMap<String, Object> getLinkOrderMap() {
		return linkOrderMap;
	}

	public void setLinkOrderMap(LinkedHashMap<String, Object> linkOrderMap) {
		this.linkOrderMap = linkOrderMap;
	}

	public MongodbQuery build() {
		return new MongodbQuery(this);
	}

}
