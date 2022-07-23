package com.swjtu.lang;

public enum LANG {
	
    AUTO("auto"),
    JP("ja"),
    CH("zh-CN"),
    KOR("ko"),
    EN("en"),
    FRA("fr"),
    SPA("es"),
    DE("de"),
    RU("ru"),
    IT("it"),
    HW("haw"),
    PT("pt"),
    ID("id"),
    AB("ar"),
    UK("uk");
	
	private String code;
	
	LANG(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}
