package com.payno.jpa.base.entity.inherit;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "Ftp_node")
public class FtpNode extends Node{
	private String ftpServer;
	private String ftpTemplateDir;
	private String localTemplateDir;
	private String localRootDir;
	private String readyFiles;
	private String targetFiles;
	private long cacheFileLimit;
}
