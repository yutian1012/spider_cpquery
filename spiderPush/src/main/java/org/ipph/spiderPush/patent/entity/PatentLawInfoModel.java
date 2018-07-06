package org.ipph.spiderPush.patent.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PatentLawInfoModel implements Serializable{
	private String id;
	private String lawInfo;
}
