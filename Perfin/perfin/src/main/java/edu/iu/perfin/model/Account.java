package edu.iu.perfin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_PERFIN_ACCOUNT")

// KasaId ortak userlar tek kasada toplanması için var

public class Account {
	@Id
	@Column(name = "ACCOUNTID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PERFIN_ACCOUNT")
	@SequenceGenerator(name = "S_PERFIN_ACCOUNT", sequenceName = "S_PERFIN_ACCOUNT", allocationSize = 1, initialValue = 1)
	private Long accountId;

	@Column(name = "ACCOUNTNAME")
	private String accountName;

	@Column(name = "ACCOUNTDESC")
	private String accountDesc;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "REFCODE")
	private String refcode;

	public Account() {
		super();
	}

	public Account(Long accountId, String accountName, String accountDesc, String Address, String refcode) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.accountDesc = accountDesc;
		this.address = Address;
		this.refcode = refcode;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountDesc() {
		return accountDesc;
	}

	public void setAccountDesc(String accountDesc) {
		this.accountDesc = accountDesc;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRefcode() {
		return refcode;
	}

	public void setRefcode(String refcode) {
		this.refcode = refcode;
	}


}
