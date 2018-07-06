package org.ipph.spiderPush.patent.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain=true)
public class PatentLawModel implements Serializable{
	private String id;
	// APP_NUMBER_
	protected String  appNumber;
	// LAW_DATE_
	protected Date lawDate;
	// LAW_STATUS_
	protected String  lawStatus;
	// LAW_INFO_
	protected String  lawInfo;
}
