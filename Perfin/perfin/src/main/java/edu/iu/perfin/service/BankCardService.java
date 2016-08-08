package edu.iu.perfin.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import edu.iu.perfin.model.BankCard;
import edu.iu.perfin.type.CardType;

@Service
public class BankCardService {

	public CardType toAssignCardType(String incomingCard){
	CardType type = null;
	if (incomingCard.equals("Kredi Kartı") || incomingCard.equals("kredi kartı")
			|| incomingCard.equals("kredi kart")) {
		type = CardType.CreditCard;
	} else if (incomingCard.equals("Debit kart") || incomingCard.equals("maaş kartı")
				|| incomingCard.equals("Banka kartı")) {
		type = CardType.DebitCard;
	}
	return type;
	}
	public void add(BankCard card) {
		//card number unique
				BankCard card1 = get("cardNumber", card.getCardNumber());
				if (card1 != null)
					throw new RuntimeException("YOU HAVE ALREADY RECORD CARD NUMBER");
				
				Ebean.save(card);
			}

			public void delete(BankCard BankCard) {
				Ebean.delete(BankCard);
			}

			public void update(BankCard BankCard) {
				Ebean.update(BankCard);
			}

			public BankCard get(String columnName, Object value) {
				return GeneralService.getFirstByColumn(BankCard.class, Expr.eq(columnName, value));
			}

			public List<BankCard> getAll() {
				return GeneralService.loadAll(BankCard.class);
			}
		}
