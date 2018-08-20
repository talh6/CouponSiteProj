package com.tal.couponsproj.enums;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum actionType {
CREATE,
REMOVE,
UPDATE,
RETRIEVE_BY_ID,
RETRIEVE_BY_COMPANY,
RETRIEVE_BY_TYPE,
RETRIEVE_ALL,
LOGIN,
PURSHED;
}
