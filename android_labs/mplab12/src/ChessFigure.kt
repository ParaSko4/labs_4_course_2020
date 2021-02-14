class ChessFigure(val name: String, colorChess: String, letterChess: Char) : IChess {
    var color: String?
    var letter: Char?
    var number: Int? = 0
    var stat: State

    override val useless: Int = 0

    class State {
        var isAttack = false
        var canAttack = false
        var canMove = false
    }

    init {
        if (name == "queen" || name == "king") {
            if (ChessBoard.figures.find {
                    it.name.equals(name)
                            && it.color.equals(colorChess)
                } != null) color = "red"
            else color = colorChess
        } else color = colorChess

        letter = letterChess
        stat = State()
    }

    constructor(name: String, colorChess: String, letterChess: Char, numberChess: Int) : this(
        name,
        colorChess,
        letterChess
    ) {
        number = numberChess
    }

    override fun moveTo(letterTo: Char, numberTo: Int) {
        stat.canAttack = false
        stat.isAttack = false
        stat.canMove = false

        possibleMoves()

        letter = letterTo
        number = numberTo
        var isKill = false

        if (stat.canAttack) {
            for (figure in ChessBoard.figures) {
                if (figure.letter == letter && figure.number == number) {
                    if (!figure.color.equals(color)) {
                        isKill = true
                        figure.delete()
                    }
                    break
                }
            }
        }

        ChessBoard.history.add(if (isKill) "[$name|$color] kill Enemy Figure -> $letter$number" else "[$name|$color] -> $letter$number")
    }

    fun printStat() {
        println("Current position: $letter$number")
        println("CanAttack: " + stat.canAttack)
        println("isAttack: " + stat.isAttack)
        println("CanMove: " + stat.canMove)
    }

    fun possibleMoves() {
        println()
        if (letter == 'Z') {
            println("Figure [$name|$color] can't move")
            return
        }
        println("Figure [$name|$color] can move to:")

        val indexOfLetter = ChessBoard.letters.indexOf(letter)

        for (li in indexOfLetter..(ChessBoard.letters.count() - 1)) {
            if (letter == ChessBoard.letters[li]) {
                for (i in number!!..8)
                    if (i != number) {
                        if (!searchFigure(letter!!, i))
                            break
                    }
                for (i in number!! downTo 1)
                    if (i != number) {
                        if (!searchFigure(letter!!, i))
                            break
                    }
            } else {
                if (!searchFigure(ChessBoard.letters[li], number!!))
                    break
            }
        }
        for (li in (indexOfLetter - 1) downTo 1) {
            if (indexOfLetter < 0) break
            if (letter == ChessBoard.letters[li]) {
                for (i in number!!..8)
                    if (i != number) {
                        if (!searchFigure(letter!!, i))
                            break
                    }
                for (i in number!! downTo 1)
                    if (i != number) {
                        if (!searchFigure(letter!!, i))
                            break
                    }
            } else {
                if (!searchFigure(ChessBoard.letters[li], number!!))
                    break
            }
        }

        println()

        if (!stat.canMove)
            println("   figure cant move")
    }

    private fun searchFigure(searchLetter: Char, searchNumber: Int): Boolean {
        var ally = false
        var enemy = false

        for (figure in ChessBoard.figures) {
            if (figure.letter == searchLetter && figure.number == searchNumber) {
                if (!figure.color.equals(color)) {
                    print("[$searchLetter$searchNumber|Enemy]")
                    enemy = true
                    stat.canAttack = true
                    with(figure) { stat.isAttack = true }
                } else {
                    print("[$searchLetter$searchNumber|Ally]")
                    ally = true
                }

                break
            }
        }

        if (!enemy && !ally) {
            print("[$searchLetter$searchNumber] ")
            stat.canMove = true
            return true
        }
        return false
    }
}