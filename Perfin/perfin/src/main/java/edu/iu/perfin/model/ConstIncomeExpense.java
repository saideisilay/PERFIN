package edu.iu.perfin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import edu.iu.perfin.type.IncomeExpense;
import edu.iu.perfin.type.PayloadType;
import edu.iu.perfin.type.PeriodConst;


@Entity
@Table(name = "T_PERFIN_CONSTINCOMEEXPENSE")

public class ConstIncomeExpense {
	@Id
	@Column(name = "CONSTINCEXID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PERFIN_CONSTINCOMEEXPENSE")
	@SequenceGenerator(name = "S_PERFIN_CONSTINCOMEEXPENSE", sequenceName = "S_PERFIN_CONSTINCOMEEXPENSE", allocationSize = 1, initialValue = 1)
	private Long constInExId;
	
	//kullanıcı id yi sor yapmadım
	
	@Column(name = "AMOUNT")
	private Double amount;
	
	@Column(name = "DATE")
	private String date;
	
	@Column(name="PERIODCONSTANT")
	@Enumerated(EnumType.STRING)
	private PeriodConst periodConst;
	
	@Column(name="TYPE")
	@Enumerated(EnumType.STRING)
	private PayloadType payloadType;
	
	@Column(name="INCOMEEXPENSE")
	@Enumerated(EnumType.STRING)
	private IncomeExpense incomeExpense;
	
	
	ConstIncomeExpense()
	{
		super();
	}
	
	ConstIncomeExpense(PeriodConst periodConst,PayloadType payloadType, IncomeExpense incomeExpense,Long constInExId, Double amount, String date)
	{
		super();
		this.periodConst = periodConst;
		this.payloadType = payloadType;
		this.incomeExpense = incomeExpense;
		this.constInExId = constInExId;
		this.amount = amount;
		this.date = date;
		
		
	}
	public PeriodConst getPeriodConst() {
		return periodConst;
	}

	public void setPeriodConst(PeriodConst periodConst) {
		this.periodConst = periodConst;
	}

	public PayloadType getPayloadType() {
		return payloadType;
	}

	public void setPayloadType(PayloadType payloadType) {
		this.payloadType = payloadType;
	}

	public IncomeExpense getIncomeExpense() {
		return incomeExpense;
	}

	public void setIncomeExpense(IncomeExpense incomeExpense) {
		this.incomeExpense = incomeExpense;
	}
	
	//sabitleri bıraktım bilemedim
	
	public Long getConstInExId() {
		return constInExId;
	}

	public void setConstInExId(Long constInExId) {
		this.constInExId = constInExId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
