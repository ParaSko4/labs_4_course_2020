fun main(){
    ChessBoard.add(ChessFigure("queen", "white", 'a', 5))
    ChessBoard.add(ChessFigure("queen", "black", 'h', 5))
    ChessBoard.add(ChessFigure("town", "black", 'h', 4))
    ChessBoard.add(ChessFigure("town", "black", 'h', 6))
    ChessBoard.add(ChessFigure("pawn", "black", 'g', 5))

    ChessBoard.boardStat()

    ChessBoard.figures[3].moveTo('d', 3)

    ChessBoard.boardStat()

    val a = A()
    a.display()
    a.converter("+")
}

fun ChessFigure.delete(){
    letter = 'Z'
    number = -1
}

object ChessBoard{
    val letters = listOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h')
    var board = listOf<Map<Char, Int>>()
    var figures = listOf<ChessFigure>()
    var history = arrayListOf<String>()

    init {
        for (i in 1..8){
            var map: MutableMap<Char, Int> = mutableMapOf()
            for (let in letters){
                map.put(let, i)
            }
            board += map
        }

        println("board: ")
        for (line in board)
            println(line)
    }

    fun add(figure: ChessFigure){
        println()
        if (!figure.color.equals("red") && figures.find { it.letter == figure.letter && it.number == figure.number } == null)
            figures += figure
    }

    fun boardStat(){
        println()
        for (figure in figures){
            figure.possibleMoves()
            figure.printStat()
        }
        printLog()
    }

    private fun printLog(){
        println('\n')
        if (history.count() == 0){
            println("Logs empty")
            return
        }
        println("Log: ")
        var count = 0
        history.forEach {
            println("Step $count: " + it)
            count++
        }
    }
}