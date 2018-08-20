package com.tal.couponsproj.enums;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum errorType {
	BUISNESS_ERROR,
	INTERNAL_ERROR,
	GENERAL_ERROR,
	INPUT_ERROR;
}

