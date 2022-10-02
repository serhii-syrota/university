package com.example.csc_knu_mobile_development_project_1.core

class SortedList(private val input: List<Double>) {
	private var resCache: List<Double>? = null
	val data: List<Double>
		get() {
			this.resCache?.let {
				return it
			} ?: run {
				val res = this.data.sorted()
				this.resCache = res
				return res
			}
		}

	fun defaultSort(): List<Double> {
		return input.toList().sorted()
	}

	fun selectionSort() {
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

	fun insertionSort(): List<Double> {
		val items = input.toMutableList()
		if (items.count() < 2) {
			return items
		}
		for (count in 1 until items.count()) {
			val item = items[count]
			var i = count
			while (i > 0 && item < items[i - 1]) {
				items[i] = items[i - 1]
				i -= 1
			}
			items[i] = item
		}
		return items
	}

	fun quickSort(items: List<Double> = input.toList()): List<Double> {
		if (items.count() < 2) {
			return items
		}
		val pivot = items[items.count() / 2]
		val equal = items.filter { it == pivot }
		val less = items.filter { it < pivot }
		val greater = items.filter { it > pivot }
		return quickSort(less) + equal + quickSort(greater)
	}

	fun mergeSort(items: List<Double> = input.toList()): List<Double> {
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
}
