package edu.iu.perfin.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;
import edu.iu.perfin.model.BankCards;

@Service
public class BankCardService {

	public void add(BankCards card) {
		
				BankCards card1 = get("cardName", card.getCardName());
				if (card1 != null)
					throw new RuntimeException("CHANGE CARD NAME");
				
				Ebean.save(card);
			}

			public void delete(BankCards bankCards) {
				Ebean.delete(bankCards);
			}

			public void update(BankCards bankCards) {
				Ebean.update(bankCards);
			}

			public BankCards get(String columnName, Object value) {
				return GeneralService.getFirstByColumn(BankCards.class, Expr.eq(columnName, value));
			}

			public List<BankCards> getAll() {
				return GeneralService.loadAll(BankCards.class);
			}
		}
