interface IChess {
    val useless: Int

    fun moveTo(letterTo: Char, numberTo: Int)
    fun moving() = print("Chess moving!")
}