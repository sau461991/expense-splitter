package com.example.dao;

import com.example.model.Sheet;

public interface SheetDao {
	
	public void creatSheet(Sheet sheet);

	public void openSheet(int id);
	
	public Integer addSheet(Sheet sheet);
}
