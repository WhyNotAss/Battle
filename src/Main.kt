fun createEmptyField(size: Int = 10): Array<CharArray> {
    return Array(size) { CharArray(size) { '.' } }
}

fun printField(field: Array<CharArray>, title: String, showShips: Boolean = true, debug: Boolean = false) {
    val size = field.size

    if (debug) {
        println("=== $title (debug) ===")

        print("   ")
        print("+---".repeat(size) + "+")
        println()

        for (r in field.indices) {
            print(if (r < 10) " $r | " else "$r | ")
            for (c in field.indices) {
                val symbol = field[r][c]
                val toPrint = if (!showShips && symbol == '#') '.' else symbol
                print("$toPrint | ")
            }
            println()
            print("   ")
            print("+---".repeat(size) + "+")
            println()
        }
        return
    }

    println("=== $title ===")

    print("   ")
    for (c in field.indices) print("$c ")
    println()

    for (r in field.indices) {
        print("$r  ")
        for (c in field.indices) {
            val symbol = field[r][c]
            val toPrint = if (!showShips && symbol == '#') '.' else symbol
            print("$toPrint ")
        }
        println()
    }
}


fun printBothFields(player: Array<CharArray>, enemy: Array<CharArray>, showEnemyShips: Boolean = false) {
    if (player.size != enemy.size) {
        println("Ошибка: поля разного размера, печать невозможна.")
        return
    }
    val size = player.size

    println("=== Ваше поле ===         === Поле противника ===")

    print("   ")
    for (c in 0 until size) print("$c ")
    print("   ")
    for (c in 0 until size) print("$c ")
    println()

    for (r in 0 until size) {
        print("$r  ")
        for (c in 0 until size) print("${player[r][c]} ")

        print("   ")

        print("$r  ")
        for (c in 0 until size) {
            val symbol = enemy[r][c]
            val toPrint = if (!showEnemyShips && symbol == '#') '.' else symbol
            print("$toPrint ")
        }
        println()
    }
}

fun printFields(fields: Array<Array<CharArray>>, titles: Array<String>, showShips: BooleanArray) {
    if (fields.size != titles.size || fields.size != showShips.size) {
        println("Ошибка: количество полей, заголовков и флагов не совпадает.")
        return
    }
    if (fields.isEmpty()) {
        println("Ошибка: нет полей для печати.")
        return
    }

    val size = fields[0].size
    for (f in fields) {
        if (f.size != size) {
            println("Ошибка: поля разного размера, печать невозможна.")
            return
        }
    }

    val gap = "   "

    for (title in titles) {
        print("=== $title ===")
        print(gap)
    }
    println()

    for (i in titles.indices) {
        print("   ")
        for (c in 0 until size) print("$c ")
        print(gap)
    }
    println()

    for (r in 0 until size) {
        for (i in fields.indices) {
            val field = fields[i]
            val show = showShips[i]
            print("$r  ")
            for (c in 0 until size) {
                val symbol = field[r][c]
                val toPrint = if (!show && symbol == '#') '.' else symbol
                print("$toPrint ")
            }
            print(gap)
        }
        println()
    }
}

fun main() {
    println(">>> Часть 1: скрытие кораблей противника")

    val playerField = createEmptyField()
    playerField[3][2] = '#'
    playerField[3][3] = '#'
    playerField[3][4] = '#'
    playerField[3][5] = '#'

    val enemyField = createEmptyField()
    enemyField[5][7] = '#'
    enemyField[6][7] = '#'
    enemyField[7][7] = '#'
    enemyField[5][5] = 'X'
    enemyField[5][6] = 'O'

    printField(playerField, "Ваше поле")
    printField(enemyField, "Поле противника", showShips = false)

    println()
    println("Гибкий размер поля")

    val smallField = createEmptyField(8)
    printField(smallField, "Маленькое поле 8×8")

    val bigField = createEmptyField(12)
    printField(bigField, "Большое поле 12×12")

    val defaultField = createEmptyField()
    printField(defaultField, "Поле по умолчанию 10×10")

    println()
    println("Печать двух полей рядом")

    printBothFields(playerField, enemyField)

    println()
    println("Режим отладки")

    printField(playerField, "Ваше поле", debug = true)

    println()
    println("Печать произвольного количества полей")

    val thirdField = createEmptyField()
    thirdField[1][1] = '#'
    thirdField[8][8] = 'X'

    val fields = arrayOf(playerField, enemyField, thirdField)
    val titles = arrayOf("Ваше поле", "Противник", "Третье")
    val show = booleanArrayOf(true, false, false)

    printFields(fields, titles, show)
}