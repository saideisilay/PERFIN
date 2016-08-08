package edu.iu.perfin.model;

import java.math.BigDecimal;
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

@Entity
@Table(name = "T_PERFIN_RECORD")
public class RecordIncomeExpense {

	@Id
	@Column(name = "RECORDID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PERFIN_RECORD")
	@SequenceGenerator(name = "S_PERFIN_RECORD", sequenceName = "S_PERFIN_RECORD", allocationSize = 1, initialValue = 1)
	private Long recordId;

	@Column(name = "RECDATE")
	private String date;

	@Column(name = "DESCRIPT")
	private String descript;

	@Column(name = "AMOUNT")
	private BigDecimal amount;

	@Column(name = "INCOMEEXPENSE")
	@Enumerated(EnumType.STRING)
	private IncomeExpense incomeExpense;

	@ManyToOne
	@JoinColumn(name = "MAINUSERID")
	private User mainuserid;

	@Column(name = "PAYTYPE")
	@Enumerated(EnumType.STRING)
	private PayloadType payloadType;

	@ManyToOne
	@JoinColumn(name = "CATEGORYTYPE")
	private Constants constId;

	@ManyToOne
	@JoinColumn(name = "BANKS")
	private BankCard bankid;

	public RecordIncomeExpense() {
		super();
	}

	public RecordIncomeExpense(Long recordId, String date, String descript, BigDecimal amount,
			IncomeExpense incomeExpense, PayloadType payloadType, User mainuserid, BankCard bankid, Constants constId) {
		super();
		this.recordId = recordId;
		this.date = date;
		this.descript = descript;
		this.amount = amount;
		this.incomeExpense = incomeExpense; // enum
		this.payloadType = payloadType; // enum
		this.mainuserid = mainuserid; // foreign
		this.bankid = bankid; // foreign
		this.constId = constId; // foreign
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public IncomeExpense getIncomeExpense() {
		return incomeExpense;
	}

	public void setIncomeExpense(IncomeExpense incomeExpense) {
		this.incomeExpense = incomeExpense;
	}

	public PayloadType getPayloadType() {
		return payloadType;
	}

	public void setPayloadType(PayloadType payloadType) {
		this.payloadType = payloadType;
	}

	public User getMainuserid() {
		return mainuserid;
	}

	public void setMainuserid(User mainuserid) {
		this.mainuserid = mainuserid;
	}

	public Constants getConstId() {
		return constId;
	}

	public void setConstId(Constants constId) {
		this.constId = constId;
	}

	public BankCard getBankid() {
		return bankid;
	}

	public void setBankid(BankCard bankid) {
		this.bankid = bankid;
	}

}
