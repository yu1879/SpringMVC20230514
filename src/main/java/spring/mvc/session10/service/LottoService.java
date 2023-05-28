package spring.mvc.session10.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.sql.RowSet;

import org.springframework.stereotype.Service;

@Service
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

	public void update(int index) {
		lottos.set(index, getRandomLotto());
	}

	public void delete(int index) {
		lottos.remove(index);
	}

	public void update(int rowIndex, int columnIndex) {
		Set<Integer> rowSet = lottos.get(rowIndex);
		List<Integer> rowList = new ArrayList<>(rowSet);
		while (true) {
			int newNum = new Random().nextInt(39) + 1;
			if (rowList.stream().filter(n -> n.intValue() == newNum).findAny().isEmpty()) {
				rowList.set(columnIndex, newNum);
				break;
			}
		}
		lottos.set(rowIndex, new LinkedHashSet<>(rowList));
	}
}
