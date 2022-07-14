package machine

import scala.io.StdIn._

class CoffeeMachine {
  val dollar = "$"
  var water = 400
  var milk = 540
  var coffee = 120
  var cups = 9
  var money = 550


  def coffeeMachine(): Unit = {
    println(
      s"""The coffee machine has:
         |$water ml of water
         |$milk ml of milk
         |$coffee g of coffee beans
         |$cups disposable cups
         |$dollar$money of money\n\n""".stripMargin)
  }


  def action(): Unit = {
    val action = readLine("Write action (buy, fill, take, remaining, exit):\n")

    if (action.equals("buy")) {
      buy()
    } else if (action.equals("fill")) {
      fill()
    } else if (action.equals("take")) {
      take()
    } else if (action.equals("remaining")) {
      println()
      coffeeMachine()
    } else if (action.equals("exit")) {
      sys.exit(0)
    }
  }


  def buy(): Unit = {
    val choice = readLine("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:\n")

    if (choice.equals("1")) {
      espresso()
    } else if (choice.equals("2")) {
      latte()
    } else if (choice.equals("3")) {
      cappuccino()
    } else if (choice.equals("back")) {
      action()
    }
  }


  def fill(): Unit = {
    water += readLine("Write how many ml of water you want to add:\n").toInt
    milk += readLine("Write how many ml of milk you want to add:\n").toInt
    coffee += readLine("Write how many grams of coffee beans you want to add:\n").toInt
    cups += readLine("Write how many disposable cups of coffee you want to add:\n").toInt
  }


  def take(): Unit = {
    println(s"I gave you $dollar$money")
    money = 0
  }


  def espresso(): Unit = {
    if (water >= 250 && coffee >= 16) {
      makeCoffee()
      water -= 250
      coffee -= 16
      cups -= 1
      money += 4
    } else if (water < 250) {
      missingResource("water")
    } else if (coffee < 14) {
      missingResource("coffee")
    }
  }


  def latte(): Unit = {
    if (water >= 350 && milk >= 75 && coffee >= 20) {
      makeCoffee()
      water -= 350
      milk -= 75
      coffee -= 20
      cups -= 1
      money += 7
    } else if (water < 350) {
      missingResource("water")
    } else if (milk < 75) {
      missingResource("milk")
    } else if (coffee < 20) {
      missingResource("coffee")
    }
  }


  def cappuccino(): Unit = {
    if (water >= 200 && milk >= 100 && coffee >= 12) {
      makeCoffee()
      water -= 200
      milk -= 100
      coffee -= 12
      cups -= 1
      money += 6
    } else if (water < 200) {
      missingResource("water")
    } else if (milk < 100) {
      missingResource("milk")
    } else if (coffee < 12) {
      missingResource("coffee")
    }
  }


  def makeCoffee(): Unit = {
    println("I have enough resources, making you a coffee!\n\n")
  }


  def missingResource(resource: String): Unit = {
    println(s"Sorry, not enough $resource\n\n")
  }
}

object Main extends CoffeeMachine {
  def main(args:Array[String]): Unit = {
    while (true) {
      action()
    }
  }
}
