package org.mongodb.query.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class MongodbBuilder {
	public MongodbBuilder() {
	}

	public static Builder create() {
		return new Builder();
	}

	public static class Builder {
		private Map<String, Object> operatorMap = new HashMap<String, Object>();
		private LinkedHashMap<String, Object> linkAndMap = new LinkedHashMap<String, Object>();
		private LinkedHashMap<String, Object> linkOrMap = new LinkedHashMap<String, Object>();

		/**
		 * 
		 * @Description （id=""）
		 * @param value
		 * @return
		 * @date 2019年3月14日
		 */
		public Builder idEq(Object value) {
			linkAndMap.put("$eq", getRuleJson("_id", value));
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
		public Builder andEq(String field, Object value) {
			linkAndMap.put("$eq", getRuleJson(field, value));
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
		public Builder andNEq(String field, Object value) {
			linkAndMap.put("$ne", getRuleJson(field, value));
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
		public Builder andGt(String field, Object value) {
			linkAndMap.put("$gt", getRuleJson(field, value));
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
		public Builder andGte(String field, Object value) {
			linkAndMap.put("$gte", getRuleJson(field, value));
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
		public Builder andLt(String field, Object value) {
			linkAndMap.put("$lt", getRuleJson(field, value));
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
		public Builder andLte(String field, Object value) {
			linkAndMap.put("$lte", getRuleJson(field, value));
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
		public Builder andIn(String field, Object... o) {
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
		public Builder andIn(String field, Collection<?> c) {
			linkAndMap.put("$in", getRuleJson(field, c));
			return this;
		}

		/**
		 * 
		 * @Description （key not in [value1, value2, ... valueN]）
		 * @param o
		 * @return
		 * @date 2019年3月19日
		 */
		public Builder andNIn(Object... o) {
			return andNIn(Arrays.asList(o));
		}

		/**
		 * 
		 * @Description （key not in [value1,<value2, ... valueN]）
		 * @param o
		 * @return
		 * @date 2019年3月19日
		 */
		public Builder andNIn(Collection<?> o) {
			linkAndMap.put("$nin", o);
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
		public Builder andExists(String field, boolean b) {
			linkAndMap.put("$exists", getRuleJson(field, b));
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
		public Builder orEq(String field, Object value) {
			linkOrMap.put("$eq", getRuleJson(field, value));
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
		public Builder orNEq(String field, Object value) {
			linkOrMap.put("$ne", getRuleJson(field, value));
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
		public Builder orGt(String field, Object value) {
			linkOrMap.put("$gt", getRuleJson(field, value));
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
		public Builder orGte(String field, Object value) {
			linkOrMap.put("$gte", getRuleJson(field, value));
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
		public Builder orLt(String field, Object value) {
			linkOrMap.put("$lt", getRuleJson(field, value));
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
		public Builder orLte(String field, Object value) {
			linkOrMap.put("$lte", getRuleJson(field, value));
			return this;
		}

		public String query() {
			System.out.println("query");
			return "query";
		}

		public String toString() {
			if (!linkAndMap.isEmpty()) {
				operatorMap.put("$and", linkAndMap);
			}
			if (!linkOrMap.isEmpty()) {
				operatorMap.put("$or", linkOrMap);
			}
			return JSON.toJSONString(operatorMap);
		}

		private Map<String, Object> getRuleJson(String symbol, Object value) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(symbol, value);
			return map;
		}
	}
}
