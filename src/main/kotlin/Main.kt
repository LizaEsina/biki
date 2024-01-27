import kotlin.random.Random

fun main() {
    var secretNumber = generateSecretNumber()
    var attempts = 0

    println("Игра 'Быки и Коровы' начинается!")
    println("Угадайте секретное число.")

    do {
        attempts++
        println("Попытка № $attempts")
        val guess = readGuessNumber()

        val (bulls, cows) = calculateBullsAndCows(guess, secretNumber)
        println("Быки: $bulls, Коровы: $cows")

        if (bulls == 4) {
            println("Вы победили! Загаданное число: $secretNumber")
            return
        }

        if (attempts == 10) {
            println("Вы использовали все попытки.")
            println("Хотите продолжить игру? (Да/Нет)")
            val choice = readLine()

            if (choice == "Да") {
                attempts = 0
                secretNumber = generateSecretNumber()
                println("Новая игра начинается!")
            } else {
                println("Вы сдались. Загаданное число: $secretNumber")
                return
            }
        }

    } while (true)
}

fun generateSecretNumber(): String {
    val digits = mutableListOf<Int>()
    while (digits.size < 4) {
        val digit = Random.nextInt(10)
        if (digits.isEmpty() && digit == 0) {
            continue
        } else if (digit !in digits) {
            digits.add(digit)
        }
    }
    return digits.joinToString("")
}

fun readGuessNumber(): String {
    println("Введите четырёхзначное число:")
    var guess = readLine()
    while (guess == null || guess.length != 4 || !guess.matches(Regex("\\d+"))) {
        println("Некорректный ввод. Попробуйте ввести четырёхзначное число:")
        guess = readLine()
    }
    return guess
}

fun calculateBullsAndCows(guess: String, secret: String): Pair<Int, Int> {
    var bulls = 0
    var cows = 0

    for (i in guess.indices) {
        if (guess[i] == secret[i]) {
            bulls++
        } else if (guess[i] in secret) {
            cows++
        }
    }
    return bulls to cows
}