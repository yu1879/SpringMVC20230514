package spring.mvc.session10.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class LottoService {
	private List<Set<Integer>> lottos = new CopyOnWriteArrayList<>();

	public Set<Integer> add() {
		Set<Integer> lotto = getRandomLotto();
		lottos.add(0, lotto);
		return lotto;
	}

	public List<Set<Integer>> querAll() {

		return lottos;
	}

	private Set<Integer> getRandomLotto() {
		Set<Integer> lotto = new LinkedHashSet<>();
		Random random = new Random();
		while (lotto.size() < 5) {
			int number = random.nextInt(39) + 1;
			lotto.add(number);
		}
		return lotto;
	}

}
