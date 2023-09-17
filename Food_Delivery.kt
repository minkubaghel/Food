import java.util.*

data class FoodItem(val name: String, val price: Double)

data class OrderItem(val foodItem: FoodItem, val quantity: Int)

class FoodDeliveryApp {
    private val menu = mutableListOf(
        FoodItem("Burger", 5.99),
        FoodItem("Pizza", 8.99),
        FoodItem("Sushi", 12.99),
        FoodItem("Salad", 4.99)
    )

    private val orders = mutableListOf<OrderItem>()

    fun displayMenu() {
        println("Menu:")
        for ((index, item) in menu.withIndex()) {
            println("${index + 1}. ${item.name} - $${item.price}")
        }
    }

    fun placeOrder(itemIndex: Int, quantity: Int) {
        if (itemIndex >= 1 && itemIndex <= menu.size && quantity > 0) {
            val selectedFoodItem = menu[itemIndex - 1]
            val orderItem = OrderItem(selectedFoodItem, quantity)
            orders.add(orderItem)
            println("$quantity ${selectedFoodItem.name}(s) added to your order.")
        } else {
            println("Invalid selection. Please try again.")
        }
    }

    fun viewOrder() {
        if (orders.isEmpty()) {
            println("Your order is empty.")
        } else {
            println("Your Order:")
            for ((index, order) in orders.withIndex()) {
                println("${index + 1}. ${order.foodItem.name} x${order.quantity}")
            }
        }
    }

    fun calculateTotal(): Double {
        return orders.sumByDouble { it.foodItem.price * it.quantity }
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val foodDeliveryApp = FoodDeliveryApp()

    while (true) {
        println("Welcome to Food Delivery App")
        println("1. Display Menu")
        println("2. Place Order")
        println("3. View Order")
        println("4. Calculate Total")
        println("5. Exit")
        print("Enter your choice: ")

        when (scanner.nextInt()) {
            1 -> foodDeliveryApp.displayMenu()
            2 -> {
                foodDeliveryApp.displayMenu()
                print("Enter the item number you want to order: ")
                val itemIndex = scanner.nextInt()
                print("Enter the quantity: ")
                val quantity = scanner.nextInt()
                foodDeliveryApp.placeOrder(itemIndex, quantity)
            }
            3 -> foodDeliveryApp.viewOrder()
            4 -> println("Total: $${foodDeliveryApp.calculateTotal()}")
            5 -> {
                println("Thank you for using Food Delivery App. Goodbye!")
                return
            }
            else -> println("Invalid choice. Please try again.")
        }
    }
}
