fun createEmptyField(): Array<CharArray> {
    return Array(10) { CharArray(10) { '.' } }
}

fun printField(field: Array<CharArray>, title: String) {
    println("=== $title ===")
    print("   ")
    for (c in 0..9) print("$c ")
    println()
    for (r in 0..9) {
        print("$r  ")
        for (c in 0..9) print("${field[r][c]} ")
        println()
    }
}

fun printBothFields(player: Array<CharArray>, enemy: Array<CharArray>) {
    println("=== Ваше поле ===         === Поле противника ===")

    print("   ")
    for (c in 0..9) print("$c ")
    print("   ")
    for (c in 0..9) print("$c ")
    println()

    for (r in 0..9) {
        print("$r  ")
        for (c in 0..9) print("${player[r][c]} ")
        print("   ")
        print("$r  ")
        for (c in 0..9) print("${enemy[r][c]} ")
        println()
    }
}

fun main() {
    val playerField = createEmptyField()
    val enemyField = createEmptyField()

    playerField[3][2] = '#'
    playerField[3][3] = '#'
    playerField[3][4] = '#'
    playerField[3][5] = '#'

    enemyField[5][7] = '#'
    enemyField[6][7] = '#'
    enemyField[7][7] = '#'

    printBothFields(playerField, enemyField)
}