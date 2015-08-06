package com.j3a.assurance.utilitaires;

public class ColumnModel {

	public ColumnModel() {
		// TODO Auto-generated constructor stub
	}
	private String header;
    private String property;
    private int colIndx;

    public ColumnModel(String header, String property, int colIndx) {
        this.header = header;
        this.property = property;
    }

    public String getHeader() {
        return header;
    }

    public String getProperty() {
        return property;
    }

	public int getColIndx() {
		return colIndx;
	}

	public void setColIndx(int colIndx) {
		this.colIndx = colIndx;
	}

    
}
