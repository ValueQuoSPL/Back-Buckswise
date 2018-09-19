package com.valuequo.buckswise.service.dto;

public class IncomeDTO {
		
		private int userid;  
		private String name;
		private String value;
		
		
		public int getUserid() {
			return userid;
		}


		public void setUserid(int userid) {
			this.userid = userid;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public IncomeDTO(String Name, String Value, int userid) {
			super();
			this.userid = userid;
			this.name = name;
			this.value = value;
		}
		
}
