package ru.potatophobe.hashtable

class HashTable<K, V>(
    private val capacity: Int = 16
) {
    private val buckets: Array<MutableSet<Entry<K, V>>> = Array(capacity) { mutableSetOf() }

    fun put(key: K, value: V) {
        val entrySet = buckets[key.hash()]

        var exists = false
        for (entry in entrySet) {
            if (entry.key == key) {
                exists = true
                entry.value = value
            }
        }
        if (!exists) {
            entrySet.add(Entry(key, value))
        }
    }

    fun get(key: K): V? {
        val entrySet = buckets[key.hash()]

        var value: V? = null
        for (entry in entrySet) {
            if (entry.key == key) {
                value = entry.value
            }
        }

        return value
    }

    fun remove(key: K) {
        val entrySet = buckets[key.hash()]

        var foundEntry: Entry<K, V>? = null
        for (entry in entrySet) {
            if (entry.key == key) {
                foundEntry = entry
            }
        }

        if (foundEntry != null) {
            entrySet.remove(foundEntry)
        }
    }

    data class Entry<K, V>(val key: K, var value: V)

    private fun Any?.hash() = this?.hashCode()?.rem(capacity) ?: 0
}
