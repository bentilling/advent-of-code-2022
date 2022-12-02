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
    if (move == "A" || move == "X") {
        return Move.ROCK
    }
    if (move == "B" || move == "Y") {
        return Move.PAPER
    }
    if (move == "C" || move == "Z") {
        return Move.SCISSORS
    }

    throw Exception("Invalid move")
}

private fun convertToResult(move: String): Result {
    if (move == "X") {
        return Result.LOSE
    }
    if (move == "Y") {
        return Result.DRAW
    }
    if (move == "Z") {
        return Result.WIN
    }

    throw Exception("Invalid Result")
}

enum class Result {
    WIN {
        override fun getScoreForResult(): Int {
            return 6
        }
    },
    DRAW {
        override fun getScoreForResult(): Int {
            return 3
        }
    },
    LOSE {
        override fun getScoreForResult(): Int {
            return 0
        }
    };

    abstract fun getScoreForResult(): Int
}

enum class Move {
    ROCK {
        override fun getScoreForMove(): Int {
            return 1
        }

        override fun getResultAgainst(opponentMove: Move): Result {
            if (opponentMove == PAPER) {
                return  Result.LOSE
            }
            if (opponentMove == SCISSORS) {
                return Result.WIN
            }
            return Result.DRAW
        }

        override fun getMoveForResult(neededResult: Result): Move {
            if (neededResult == Result.WIN) {
                return PAPER
            }
            if (neededResult == Result.DRAW) {
                return ROCK
            }
            return SCISSORS
        }
    },
    PAPER {
        override fun getScoreForMove(): Int {
            return 2
        }

        override fun getResultAgainst(opponentMove: Move): Result {
            if (opponentMove == SCISSORS) {
                return  Result.LOSE
            }
            if (opponentMove == ROCK) {
                return Result.WIN
            }
            return Result.DRAW
        }

        override fun getMoveForResult(neededResult: Result): Move {
            if (neededResult == Result.WIN) {
                return SCISSORS
            }
            if (neededResult == Result.DRAW) {
                return PAPER
            }
            return ROCK
        }
    },
    SCISSORS {
        override fun getScoreForMove(): Int {
            return 3
        }

        override fun getResultAgainst(opponentMove: Move): Result {
            if (opponentMove == ROCK) {
                return  Result.LOSE
            }
            if (opponentMove == PAPER) {
                return Result.WIN
            }
            return Result.DRAW
        }

        override fun getMoveForResult(neededResult: Result): Move {
            if (neededResult == Result.WIN) {
                return ROCK
            }
            if (neededResult == Result.DRAW) {
                return SCISSORS
            }
            return PAPER
        }

    };
    abstract fun getScoreForMove(): Int
    abstract fun getResultAgainst(opponentMove: Move): Result
    abstract fun getMoveForResult(neededResult: Result): Move
}
