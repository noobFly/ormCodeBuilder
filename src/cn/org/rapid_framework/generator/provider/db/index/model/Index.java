package cn.org.rapid_framework.generator.provider.db.index.model;

import java.util.ArrayList;
import java.util.List;

import cn.org.rapid_framework.generator.provider.db.table.model.Column;

public class Index {
	private String indexName;
	private String tableName;
	private List<Column> columns = new ArrayList<Column>();
	private boolean isUnique;
	public String getIndexName() {
		return indexName;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<Column> getColumns() {
		return columns;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	public boolean isUnique() {
		return isUnique;
	}
	public void setUnique(boolean isUnique) {
		this.isUnique = isUnique;
	}
}
