val bookedSeats = mutableListOf<Pair<Int, Int>>()
var totalSeats = 0
var rows = 0
var seatsInRow = 0
var end = false
var ticketsSold = 0
var income = 0

fun main() {
    println("Enter the number of rows:")
    rows = readln().toInt()
    println("Enter the number of seats in each row:")
    seatsInRow = readln().toInt()
    /*rows = 8
    seatsInRow = 8*/
    totalSeats = rows * seatsInRow
    while (!end) {
        println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit")
        val input = readln().toInt()
        when (input) {
            1 -> printSeats(rows, seatsInRow, bookedSeats)
            2 -> buyTicket()
            3 -> statistics()
            0 -> break
            else -> Unit
        }
    }
}
fun calcPrice(row: Int): Int {
    var price = 0
    if (totalSeats <= 60) price = 10
    else {
        price = if (row <= rows / 2) 10 else 8
    }
    return price
}

fun buyTicket() {
    println()
    var price = 0
    var row = 0
    var seat = 0
    while (true) {
        println("Enter a row number:")
        row = readln().toInt()
        println("Enter a seat number in that row:")
        seat = readln().toInt()
        if (bookedSeats.contains(Pair(row, seat))) {
            println()
            println("That ticket has already been purchased!")
            println()
        } else if (row !in 1..rows || seat !in 1..seatsInRow) {
            println()
            println("Wrong input!")
            println()
        } else {
            break
        }
    }
    println()
    if (totalSeats <= 60) price = 10
    else {
        price = if (row <= rows / 2) 10 else 8
    }
    income += price
    println("Ticket price: $$price")
    println()
    bookedSeats.add(Pair(row, seat))
    ticketsSold++
}
fun statistics() {
    println()
    var totalIncome = 0
    println("Number of purchased tickets: $ticketsSold")
    val percentage = ticketsSold.toFloat() / totalSeats.toFloat() * 100
    println("Percentage: ${String.format("%.2f", percentage)}%")
    println("Current income: $$income")
    for (row in 1..rows) {
        for (seat in 1..seatsInRow) {
            totalIncome += calcPrice(row)
        }
    }
    println("Total income: $$totalIncome")
    println()
}

fun printSeats(rows: Int, seatsInRow: Int, bookedSeats: List<Pair<Int, Int>>) {
    println()
    print("Cinema:")
    print("\n ")
    (1..seatsInRow).forEach { print(" $it") }
    println()
    for (row in 1..rows) {
        print(row)
        for (seat in 1..seatsInRow) {
            val symb = if (bookedSeats.contains(Pair(row, seat))) 'B' else 'S'
            print(" $symb")
        }
        println()
    }
    println()
}
