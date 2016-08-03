package edu.iu.perfin.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import edu.iu.perfin.type.CardType;

@Entity
@Table(name = "T_PERFIN_BANKCARD")

public class BankCard {

	@Id
	@Column(name = "BANKID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PERFIN_BANK")
	@SequenceGenerator(name = "S_PERFIN_BANK", sequenceName = "S_PERFIN_BANK", allocationSize = 1, initialValue = 1)
	private Long bankId;

	@Column(name = "CARDNUMBER")
	private String cardNumber;

	@Column(name = "CARDNAME")
	private String cardName;

	@Column(name = "CARDTYPE")
	@Enumerated(EnumType.STRING)
	private CardType cardType;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "LIMIT")
	private BigDecimal limit;

	@Column(name = "DEPT")
	private BigDecimal dept;

	public BankCard() {
		super();
	}

	public BankCard(Long bankId, String cardName, String description, BigDecimal limit, BigDecimal dept,
			CardType cardType, String cardNumber) {
		super();
		this.bankId = bankId;
		this.cardName = cardName;
		this.cardType = cardType;
		this.cardNumber = cardNumber;
		this.description = description;
		this.limit = limit;
		this.dept = dept;

	}

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getLimit() {
		return limit;
	}

	public void setLimit(BigDecimal limit) {
		this.limit = limit;
	}

	public BigDecimal getDept() {
		return dept;
	}

	public void setDept(BigDecimal dept) {
		this.dept = dept;
	}

}