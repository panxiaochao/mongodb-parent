package org.mongodb.query.core;

import java.util.LinkedHashMap;

public class MongodbRestrictions {
	private LinkedHashMap<String, Object> linkMap = new LinkedHashMap<String, Object>();

	public MongodbRestrictions() {
	}

	
	/**
	 * 
	 * @Description （key>value）
	 * @param field
	 * @param value
	 * @return
	 * @date 2019年3月14日
	 */
	public MongodbRestrictions andGt(Object value) {
		linkMap.put("$gt", value);
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
	public MongodbRestrictions andGte(Object value) {
		linkMap.put("$gte", value);
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
	public MongodbRestrictions andLt(Object value) {
		linkMap.put("$lt", value);
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
	public MongodbRestrictions andLte(Object value) {
		linkMap.put("$lte", value);
		return this;
	}

	public LinkedHashMap<String, Object> getLinkedHashMap() {
		return linkMap;
	}
}
