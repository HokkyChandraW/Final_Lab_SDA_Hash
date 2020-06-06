public class HashNode<K, V> {
	K key;
	V value;

	// Reference to the next node available
	HashNode<K, V> next;

	// Constructor
	public HashNode(K key, V value) {
		this.key = key;
		this.value = value;
	}
}