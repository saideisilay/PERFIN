package edu.iu.perfin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import edu.iu.perfin.type.IncomeExpense;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "T_PERFIN_CONSTANTS")
public class Constants {
	@Id
	@Column(name = "CONSTID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PERFIN_CONSTANTS")
	@SequenceGenerator(name = "S_PERFIN_CONSTANTS", sequenceName = "S_PERFIN_CONSTANTS", allocationSize = 1, initialValue = 1)
	private Long constID;

	public Constants() {
		super();
	}

	public Constants( Long constiD, String classific, Account account, IncomeExpense categories) {

		super();
		this.constID = constiD;
		this.classific = classific;
		this.account = account;
		this.categories = categories;
	
	}

	public Long getConstID() {
		return constID;
	}

	public void setConstID(Long constiD) {
		this.constID = constiD;
	}

	@Column(name = "CATEGORIES")
	@Enumerated(EnumType.STRING)
	private IncomeExpense categories;

	@Column(name = "CLASSIFICATION")
	private String classific;

	public String getClassific() {
		return classific;
	}

	public void setClassific(String classific) {
		this.classific = classific;
	}

	@ManyToOne
	@JoinColumn(name = "ACCOUNT")
	private Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public IncomeExpense getCategories() {
		return categories;
	}

	public void setCategories(IncomeExpense categories) {
		this.categories = categories;
	}

}
