package spring.mvc.session14.validator;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.mvc.session14.entity.Stock;

public class StockValidator implements Validator {
	private static Map<String, Double> quotes = new LinkedHashMap<>();
	static {
		quotes.put("2330", 555.0);
		quotes.put("2303", 50.9);
		quotes.put("2317", 107.5);
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Stock.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Stock stock = (Stock) target;
		ValidationUtils.rejectIfEmpty(errors, "symbol", "stock.symbol.empty");
		ValidationUtils.rejectIfEmpty(errors, "price", "stock.price.empty");
		ValidationUtils.rejectIfEmpty(errors, "amount", "stock.amount.empty");

		if (quotes.get(stock.getSymbol()) == null) {
			errors.rejectValue("symbol", "stock.symbol.notfound");
		} else {
			Double previousClose = quotes.get(stock.getSymbol());
			if (stock.getPrice() < previousClose * 0.9 || stock.getPrice() > previousClose * 1.1) {
				errors.rejectValue("price", "stock.price.range",
						new Object[] { previousClose * 0.9, previousClose * 1.1 }, "stock.price.range");
			}
			if (stock.getAmount() < 1000) {
				errors.rejectValue("amount", "stock.amount.notenough");

			}
			if (stock.getAmount() % 1000 != 0) {
				errors.rejectValue("amount", "stock.amount.notenough");

			}
		}

	}

}
