package spring.mvc.session10.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.sql.RowSet;

import org.apache.catalina.tribes.tipis.AbstractReplicatedMap.MapEntry;
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

	public Map<Integer, Long> getLottoStatistics() {
		List<Integer> nums = lottos.stream().flatMap(lotto -> lotto.stream()).collect(Collectors.toList());

		Map<Integer, Long> stat = nums.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		Map<Integer, Long> statAndSort = new LinkedHashMap<>();
		stat.entrySet().stream().sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
				.forEachOrdered(e -> statAndSort.put(e.getKey(), e.getValue()));
		return statAndSort;

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
