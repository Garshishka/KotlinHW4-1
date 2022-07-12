import kotlin.math.roundToInt

fun main() {
    val transferAmount = 200_00
    val paymentType = "Maestro"
    val previousPayments = 0

    val finalComision = calculateComision(paymentType, previousPayments, transferAmount)
    println (if(finalComision>=0) "комиссия будет составлять $finalComision коп."
    else "Превышены лимиты перевода")
}

fun calculateComision(paymentType:String = "Vk Pay", previousPayments: Int = 0, transferAmount: Int):Int{
    if(canPay(paymentType,previousPayments,transferAmount)) { //if transfer limits are not breached we can calculate comission
        return when (paymentType) {
            "Visa", "Мир" -> {if (transferAmount * 0.0035 < 35_00) 35_00
            else (transferAmount * 0.00_35).roundToInt()}      //0.35% with 35 rub as the minimum
            "Mastercard", "Maestro" -> {if (transferAmount in 300_00..75_000_00) 0
            else (transferAmount * 0.00_6).roundToInt() + 20_00}  //0.6%+20 rub only if not in 300-75_000 rub
            else -> 0 //mostly for VK Pay
        }
    }
    else return -1 //means we broke transfer limits
}

//Checking transfer limits
fun canPay(paymentType:String, previousPayments: Int, transferAmount: Int):Boolean{
    when (paymentType){
        "Vk Pay" ->{
            if(transferAmount>15_000_00){
                println("Не больше 15 000 руб за один перевод!")
                return false
            }
            if(transferAmount+previousPayments>40_000_00){
                println("Не больше 40 000 руб в месяц!")
                return false
            }
        }
        else -> {
            if(transferAmount>150_000_00){
                println("Не больше 150 000 руб за один перевод!")
                return false
            }
            if(transferAmount+previousPayments>600_000_00){
                println("Не больше 600 000 руб в месяц!")
                return false
            }
        }
    }
    return true
}