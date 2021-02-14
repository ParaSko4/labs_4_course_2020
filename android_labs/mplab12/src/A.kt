class A {
    private lateinit var prop: String

    fun setUp() {
        prop = "100 + 200"
    }

    fun display() {
        if (::prop.isInitialized) {
            println(prop)
        }
    }

    override operator fun equals(other: Any?): Boolean {
        println("A: equals")
        return true
    }

    fun plus() = println('+')
    fun minus() = println('-')
    fun divide() = println('/')
    fun multiply() = println('*')

    fun converter(which: String): Unit? {
        return when(which){
            "+" -> plus()
            "-" -> minus()
            "/" -> divide()
            "*" -> multiply()
            else -> null
        }
    }
}