import java.util.ArrayList

//2a
val intt: Int = 2
val doublee: Double = 2.2
val string: String = "asfd"
val inttt = 2
val doubleee = 2.2
val stringg = "asfd"

var intttt: Int = 2
var doubleeee: Double = 2.2
var stringgg: String = "asfd"
var inttttt = 2
var doubleeeee = 2.2
var stringggg = "asfd"

fun task2bc() {
    //2b
    var byt: Byte = 1
    var inT: Int = byt.toInt()
    var str: String = inT.toString()
    //2c
    println("Byte: " + byt)
    println("Int: $inT")
    println("Int: " + str)
}

//2d
const val g = 2

//2e
fun task2e() {
    var intNull: Int? = readLine()?.toIntOrNull()
    println("task3 null?:  $intNull")
}

//3a
fun task3a(vararg doubles: Double): Double {
    var sum = 0.0
    for (item in doubles)
        sum += item
    return sum
}

//3b
fun isValid(login: String, password: String): Boolean {
    fun notNull(): Boolean = !(login.isEmpty() || password.isEmpty())

    return (notNull()
            && login.contains('@')
            && !password.contains(' ')
            && !(password.length > 12)
            && !(password.length < 6))
}

//3c
enum class Holidays(val date: String) {
    Christmas("25.12.20"),
    NewYear("31.12.20")
}

fun isHoliday(date: String) {
    val error = "TASK 3C = ERROR"

    val list = date.split('.')
    if (date.isEmpty() || list.count() != 3) {
        println(error)
        return
    }
    for (item in list) {
        if (item.toIntOrNull() == null || item.length > 2) {
            println(error)
            return
        }
    }
    when (date) {
        Holidays.Christmas.date -> println("Christmas")
        Holidays.NewYear.date -> println("NewYear")
        else -> println("Simple day")
    }
}

//3d
fun doOperation(a: Int, b: Int, operation: Char): Double {
    return when (operation) {
        '+' -> (a + b).toDouble()
        '-' -> (a - b).toDouble()
        '*' -> (a * b).toDouble()
        '/' -> (a / b).toDouble()
        else -> 0.0
    }
}

//3e
fun IntArray.indexOfMax(a: IntArray): Int? {
    if (a.isEmpty()) return null

    val maxNum = a.max()
    var maxIndex = -1
    var checkRepeat = true

    a.forEachIndexed { index, value
        ->
        if (maxNum == value) {
            if (maxIndex != -1) {
                checkRepeat = false
            }
            maxIndex = index
        }
    }

    if (!checkRepeat) return null
    return maxIndex
}

//3f
fun String.coincidence(s: String): Int {
    var count = 0
    var str = s
    this.forEachIndexed { index, value
        ->
        if (str.contains(value)) {
            val ind = str.indexOf(value)
            str = str.removeRange(ind, ind + 1)
            count++
        }
    }
    return count
}

//4a
fun factorial(n: Int): Double {
    var res = 1
    for (t in n downTo 1) {
        res *= t
    }
    return res.toDouble()
}

fun factorialRec(n: Int): Double = if (n < 2) 1.0 else (n * factorialRec(n - 1))


//4b
fun isPrime(n: Int): Boolean {
    for (i in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) return false
    }
    return true
}

//5a
fun containsIn(collection: Collection<Int>): Boolean = collection.any{it < 10}

fun lambda1(i: Int): Boolean = i > 10
fun lambda2(i: Int): Boolean = i < 10

fun funcForLambda(n: Int, oper: (Int) -> Boolean){
    println("lambda result: " + oper(n))
}

fun main() {
    task2bc()
    task2e()
    println("task3a: " + task3a(2.0, 2.6, 7.7))
    println("task3b: " + isValid("as@df", "ad1111f"))
    isHoliday("31.12.20")
    println("task3d: " + doOperation(2, 6, '*'))

    val arr1 = intArrayOf(123, 213, 124, 45)
    println("task3e: " + arr1.indexOfMax(arr1))

    println("task3f: " + "laba".coincidence("bjjjjaghghg"))
    println("task4a1: " + factorial(20))
    println("task4a2: " + factorialRec(20))
    println("task4b: ")
    var primeList: ArrayList<Int> = arrayListOf()
    var primeArray: Array<Int> = Array(10, { 0 })

    var i = 0;
    for (num in 1..10000) {
        if (isPrime(num)) {
            if (i < 21)  primeList.add(num)
            else if (i < 31) primeArray[(i - 1) % 10] = num
            else break
            i++
        }
    }
    println("List: " + primeList)
    println("Array: " + primeArray.toList())

    println("task5a: ")
    println("containsIn: " + containsIn(listOf(4, 56, 51)))
    funcForLambda(6, ::lambda1)
    funcForLambda(6, ::lambda2)

    println("task5b: ")
    val listInt = mutableListOf(2, 10, 134, 4, 7, 132, 4, 34, 16, 1, 4, 51, 1436);
    listInt += 134
    println(listInt)
    println("uniq: " + LinkedHashSet(listInt).toList())
    println("it % 2 == 1: " + listInt.filter { it % 2 == 1 })
    listInt.forEach({print(" " + it)})
    println("\nisPrime: " + listInt.filter(::isPrime))
    println("find: " + listInt.find { it == 7 })
    println("groupBy: " + listInt.groupBy { it % 2 == 1 })
    println("all: " + listInt.all { it < 1500 })
    println("any: " + listInt.any { it == 7 })

    val listStr = listOf("1", "2", "3")
    val (one, two, _) = listStr
    println("destruct: " + listStr)
    println("$one $two")

    println("task5c: ")
    val map_students_results = mapOf(
        "Hlob" to 40,
        "Hlab" to 23,
        "Hleb" to 12,
        "Hlib" to 37,
        "Hleq" to 34,
        "Hloq" to 28,
        "Hluq" to 5,
    )
    println(map_students_results)

    val map_students_marks = mutableMapOf<String, Int>()
    for ((key, value) in map_students_results) {
        val mark = when (value) {
            40 -> 10
            39 -> 9
            38 -> 8
            in 35..37 -> 7
            in 32..34 -> 6
            in 29..31 -> 5
            in 25..28 -> 4
            in 22..24 -> 3
            in 19..21 -> 2
            else -> 1
        }
        map_students_marks.put(key, mark)
    }
    println(map_students_marks)
}