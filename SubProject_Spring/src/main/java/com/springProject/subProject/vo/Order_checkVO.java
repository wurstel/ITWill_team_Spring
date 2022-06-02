package com.springProject.subProject.vo;

public class Order_checkVO {
	private String mem_id;
	private String pd_name;
	private String pd_price;
	private String od_qty;
	private String totalprice;
	private String order_status;
	
	
	
	
	public String getMem_id() {
		return mem_id;
	}




	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}




	public String getPd_name() {
		return pd_name;
	}




	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}




	public String getPd_price() {
		return pd_price;
	}




	public void setPd_price(String pd_price) {
		this.pd_price = pd_price;
	}




	public String getOd_qty() {
		return od_qty;
	}




	public void setOd_qty(String od_qty) {
		this.od_qty = od_qty;
	}




	public String getTotalprice() {
		return totalprice;
	}




	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}




	public String getOrder_status() {
		return order_status;
	}




	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}




	@Override
	public String toString() {
		return "Order_checkDTO [mem_id=" + mem_id + ", pd_code=" + pd_name + ", pd_price=" + pd_price + ", od_qty="
				+ od_qty + ", totalprice=" + totalprice + ", order_status=" + order_status + "]";
	}
	
	
	
	
	
	
	
}




