package org.mongodb.query;

import org.mongodb.query.core.MongodbBuilder;

public class MongoDBQuery {
	public static void main(String[] args) {
		//String query = MongodbBuilder.create().andEq("ID", 59080901).toString();
		String query = MongodbBuilder.create().andEq("CITY", "hz").toString();
		System.out.println(query);
	}
}
