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

	public Constants(Long constID, String classification, Account caseid, IncomeExpense categories) {

		super();
		this.constID = constID;
		this.categories = categories;
		this.classification = classification;
		this.caseid = caseid;
	}

	@Column(name = "CATEGORIES")
	@Enumerated(EnumType.STRING)
	private IncomeExpense categories;

	@Column(name = "CLASSIFICATION")
	private String classification;

	@ManyToOne
	@JoinColumn(name = "CASEID")
	private Account caseid;

	public Long getConstID() {
		return constID;
	}

	public void setConstID(Long constID) {
		this.constID = constID;
	}

	public IncomeExpense getCategories() {
		return categories;
	}

	public void setCategories(IncomeExpense categories) {
		this.categories = categories;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public Account getCaseid() {
		return caseid;
	}

	public void setCaseid(Account caseid) {
		this.caseid = caseid;
	}


}
