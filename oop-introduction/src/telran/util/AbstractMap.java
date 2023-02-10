package telran.util;

import java.lang.reflect.InvocationTargetException;

public abstract class AbstractMap<K, V> implements Map<K, V> {

	protected Set<Entry<K, V>> set;

	@Override
	public V put(K key, V value) {
		return put(key, value, false);
	}

	@Override
	public V putIfAbsent(K key, V value) {
		return put(key, value, true);
	}

	private V put(K key, V value, boolean putIfAbsent) {
		V res = null;
		Entry<K, V> entry = set.get(new Entry<>(key, value));
		if (entry != null) {
			res = entry.getValue();
			if (!putIfAbsent) {
				entry.setValue(value);
			}
		} else {
			set.add(new Entry<>(key, value));
		}
		return res;
	}

	@Override
	public V get(K key) {
		V res = null;
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		if (entry != null) {
			res = entry.getValue();
		}
		return res;
	}

	@Override
	public V getOrDefault(K key, V value) {
		V res = get(key);
		return res == null? value : res;
	}

	@Override
	public boolean containsKey(K key) {
		return set.contains(new Entry(key, null));
	}

	@Override
	public boolean containsValue(V value) {
		return set.stream().anyMatch(el -> el.getValue().equals(value));
	}

	@Override
	public Collection<V> values() {

			Collection<V> res = new ArrayList<V>();
			entrySet().forEach(el -> res.add(el.getValue()));
			return res;
	}


	@SuppressWarnings("unchecked")
	@Override
	public Set<K> keySet() {
		try {
			Set<K> res = set.getClass().getConstructor().newInstance();
			set.forEach(el -> res.add(el.getKey()));
			return res;
		} catch (Exception e) {
			throw new IllegalStateException();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Entry<K, V>> entrySet() {
		try {
			Set<Entry<K, V>> res = set.getClass().getConstructor().newInstance();
			set.forEach(res::add);
			return res;
		} catch (Exception e) {
			throw new IllegalStateException();
		}

	}

	@Override
	public V remove(K key) {
		V res = get(key);
		if (res !=null) {
		 set.remove(new Entry(key, null));
		}
		return res;
	}

}
