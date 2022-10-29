package com.example.csc_knu_mobile_development_project_1.core.data

data class ListUiState(
	var list: MutableList<Double> = mutableListOf(),
)

/*TODO: add cycles count*/
class SortedList(private val input: List<Double>) {
	fun value(): List<Double> {
		return this.input.sorted()
	}

	fun sortStats(): SortOpsCount {
		val stats = HashMap<SortType, Int>()
		stats.set(SortType.INSERTION, this.insertionSort())
		return stats
	}

	private fun selectionSort() {
		val items = input.toMutableList()
		var n = items.count()
		var temp: Double
		for (i in 0 until n) {
			var indexOfMin = i
			for (j in n - 1 downTo i) {
				if (items[j] < items[indexOfMin])
					indexOfMin = j
			}
			if (i != indexOfMin) {
				temp = items[i]
				items[i] = items[indexOfMin]
				items[indexOfMin] = temp
			}
		}
	}

	private fun insertionSort(): Int {
		val items = input.toMutableList()
		if (items.count() < 2) {
			return 2
		}

		var ops = 0
		for (count in 1 until items.count()) {
			ops++
			val item = items[count]
			var i = count
			while (i > 0 && item < items[i - 1]) {
				ops++
				items[i] = items[i - 1]
				i -= 1
			}
			items[i] = item
		}
		return ops
	}

	private fun quickSort(items: List<Double> = input.toList()): List<Double> {
		if (items.count() < 2) {
			return items
		}
		val pivot = items[items.count() / 2]
		val equal = items.filter { it == pivot }
		val less = items.filter { it < pivot }
		val greater = items.filter { it > pivot }
		return quickSort(less) + equal + quickSort(greater)
	}

	private fun mergeSort(items: List<Double> = input.toList()): List<Double> {
		if (items.size <= 1) {
			return items
		}
		val middle = items.size / 2
		var left = items.subList(0, middle);
		var right = items.subList(middle, items.size);
		return merge(mergeSort(left), mergeSort(right))
	}

	private fun merge(left: List<Double>, right: List<Double>): List<Double> {
		var indexLeft = 0
		var indexRight = 0
		var newList = mutableListOf<Double>()
		while (indexLeft < left.count() && indexRight < right.count()) {
			if (left[indexLeft] <= right[indexRight]) {
				newList.add(left[indexLeft])
				indexLeft++
			} else {
				newList.add(right[indexRight])
				indexRight++
			}
		}
		while (indexLeft < left.size) {
			newList.add(left[indexLeft])
			indexLeft++
		}
		while (indexRight < right.size) {
			newList.add(right[indexRight])
			indexRight++
		}
		return newList;
	}

	private fun pancakeSort(items: MutableList<Double> = input.toMutableList()): List<Double> {
		for (n in items.count() downTo 2) {
			val maxI = this.indexOfMax(items, n)
			if (maxI != n - 1) {
				if (maxI > 0) {
					pancakeFlipToStart(items, maxI)
				}
				pancakeFlipToStart(items, n - 1)
			}
		}
		return items
	}

	private fun indexOfMax(items: List<Double>, n: Int): Int {
		var index = 0
		for (i in 1 until n) {
			if (items[i] > items[index]) index = i
		}
		return index
	}

	private fun pancakeFlipToStart(items: MutableList<Double>, index: Int) {
		var i = index
		var j = 0
		while (j < i) {
			val temp = items[j]
			items[j] = items[i]
			items[i] = temp
			j++
			i--
		}
	}
}

enum class SortType(name: String) {
	PANCAKE("Pancake"),
	SELECTION("Selection"),
	INSERTION("Insertion"),
	QUICK("Quick"),
	MERGE("Merge")
}
typealias SortOpsCount = Map<SortType, Int>