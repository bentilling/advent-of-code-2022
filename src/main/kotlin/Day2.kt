fun main() {
    val input = readInput("day2/rock-paper-scissors")
    val strategiesT1 = getStrategies(input, 1)
    println(getScores(strategiesT1).sum())
    val strategiesT2 = getStrategies(input, 2)
    println(getScores(strategiesT2))
    println(getScores(strategiesT2).sum())
}

private fun getScores(strategies: List<Pair<Move, Move>>): List<Int> {
    return strategies.map {
        val (opponentMove, ourMove) = it
        ourMove.getResultAgainst(opponentMove).getScoreForResult() +
                ourMove.getScoreForMove()
    }
}

private fun getStrategies(input: List<String>,  taskNumber: Int): List<Pair<Move, Move>> {
    return input.map {
        val splitParts = it.split(' ')
        if (taskNumber == 1) {
            Pair(convertToMove(splitParts[0]), convertToMove(splitParts[1]))
        } else {
            val opponentMove = convertToMove(splitParts[0])
            val neededMove =  opponentMove.getMoveForResult(convertToResult(splitParts[1]))
            Pair(opponentMove, neededMove)
        }
    }
}

private fun convertToMove(move: String): Move {
    return when (move) {
        "X","A" -> Move.ROCK
        "Y","B" -> Move.PAPER
        "Z","C" -> Move.SCISSORS
        else -> throw Exception("Invalid Move")
    }
}

private fun convertToResult(move: String): Result {
    return when (move) {
        "X" -> Result.LOSE
        "Y" -> Result.DRAW
        "Z" -> Result.WIN
        else -> throw Exception("Invalid Result")
    }
}

enum class Result {
    WIN {
        override fun getScoreForResult() = 6
    },
    DRAW {
        override fun getScoreForResult() = 3
    },
    LOSE {
        override fun getScoreForResult() = 0
    };
    abstract fun getScoreForResult(): Int
}

enum class Move {
    ROCK {
        override fun getScoreForMove() = 1

        override fun getResultAgainst(opponentMove: Move): Result {
            return when (opponentMove) {
                PAPER -> Result.LOSE
                SCISSORS -> Result.WIN
                else -> Result.DRAW
            }
        }

        override fun getMoveForResult(neededResult: Result): Move {
            return when (neededResult) {
                Result.WIN -> PAPER
                Result.DRAW -> ROCK
                else -> SCISSORS
            }
        }
    },
    PAPER {
        override fun getScoreForMove() = 2

        override fun getResultAgainst(opponentMove: Move): Result {
            return when (opponentMove) {
                SCISSORS -> Result.LOSE
                ROCK -> Result.WIN
                else -> Result.DRAW
            }
        }

        override fun getMoveForResult(neededResult: Result): Move {
            return when (neededResult) {
                Result.WIN -> SCISSORS
                Result.DRAW -> PAPER
                else -> ROCK
            }
        }
    },
    SCISSORS {
        override fun getScoreForMove() = 3

        override fun getResultAgainst(opponentMove: Move): Result {
            return when (opponentMove) {
                ROCK -> Result.LOSE
                PAPER -> Result.WIN
                else -> Result.DRAW
            }
        }

        override fun getMoveForResult(neededResult: Result): Move {
            return when (neededResult) {
                Result.WIN -> ROCK
                Result.DRAW -> SCISSORS
                else -> PAPER
            }
        }
    };
    abstract fun getScoreForMove(): Int
    abstract fun getResultAgainst(opponentMove: Move): Result
    abstract fun getMoveForResult(neededResult: Result): Move
}
