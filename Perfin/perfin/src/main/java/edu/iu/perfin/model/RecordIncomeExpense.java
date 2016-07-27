package edu.iu.perfin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import edu.iu.perfin.type.IncomeExpense;
import edu.iu.perfin.type.PayloadType;
import edu.iu.perfin.type.PeriodConst;



@Entity
@Table(name = "T_PERFIN_RECORD")
public class RecordIncomeExpense {

	@Id
	@Column(name = "RECORDID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_PERFIN_RECORD")
	@SequenceGenerator(name = "T_PERFIN_RECORD", sequenceName = "T_PERFIN_RECORD", allocationSize = 1, initialValue = 1)
	private Long recordId;
	
	@Column(name = "DATE")
	private String date;
	
	@Column(name = "DESCRIPT")
	private String descript;
	
	@Column(name = "RECORDAMOUNT")
	private Double recordAmount;
	
	@Column(name="INCOMEEXPENSE")
	@Enumerated(EnumType.STRING)
	private IncomeExpense incomeExpense;
	
	@Column(name="PERIODCONST")
	@Enumerated(EnumType.STRING)
	private PeriodConst periodConst;
	

	@Column(name="TYPE")
	@Enumerated(EnumType.STRING)
	private static PayloadType payloadType;
	

	public RecordIncomeExpense()
	{
		super();
	}
	
	public RecordIncomeExpense(Long recordId,String date,String descript,Double recordAmount, IncomeExpense incomeExpense, PayloadType payloadType, PeriodConst periodConst)
	{
		super();
		this.recordId=recordId;
		this.date=date;
		this.descript=descript;
		this.recordAmount=recordAmount;
		this.incomeExpense = incomeExpense;
		RecordIncomeExpense.payloadType = payloadType;
		this.periodConst = periodConst;
		
		
		//devamı gelecek
	}
	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public Double getRecordAmount() {
		return recordAmount;
	}

	public void setRecordAmount(Double recordAmount) {
		this.recordAmount = recordAmount;
	}
	public IncomeExpense getIncomeExpense() {
		return incomeExpense;
	}

	public void setIncomeExpense(IncomeExpense incomeExpense) {
		this.incomeExpense = incomeExpense;
	}
	static public PayloadType getPayloadType() {
		return payloadType;
	}

	public void setPayloadType(PayloadType payloadType) {
		RecordIncomeExpense.payloadType = payloadType;
	}
	public PeriodConst getPeriodConst() {
		return periodConst;
	}

	public void setPeriodConst(PeriodConst periodConst) {
		this.periodConst = periodConst;
	}
}
