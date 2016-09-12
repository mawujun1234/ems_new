package com.mawujun.org;

public class OrgVO extends Org {
	private String parent_id;
	private Dim dim;
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public Dim getDim() {
		return dim;
	}
	public void setDim(Dim dim) {
		this.dim = dim;
	}

}
