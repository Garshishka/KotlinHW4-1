import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {

    //calculateCommision function tests

    @Test
    fun calculateComision_overLimit(){
        val payType = "Vk Pay"
        val previousPay = 0
        val trasferPay = 100_000_00

        val result = calculateComision(payType,previousPay,trasferPay)

        assertEquals(-1,result)
    }

    @Test
    fun calculateComision_VkPay(){
        val payType = "Vk Pay"
        val previousPay = 0
        val trasferPay = 1_000_00

        val result = calculateComision(payType,previousPay,trasferPay)

        assertEquals(0,result)
    }

    @Test
    fun calculateComision_Visa_lessThenLowerLimit(){
        val payType = "Visa"
        val previousPay = 0
        val trasferPay = 1_000_00

        val result = calculateComision(payType,previousPay,trasferPay)

        assertEquals(25_00,result)
    }

    @Test
    fun calculateComision_Visa_moreThenLowerLimit(){
        val payType = "Visa"
        val previousPay = 0
        val trasferPay = 100_000_00

        val result = calculateComision(payType,previousPay,trasferPay)

        assertEquals(250_00,result)
    }

    @Test
    fun calculateComision_Maestro_lessThenLowerLimit(){
        val payType = "Maestro"
        val previousPay = 0
        val trasferPay = 250_00

        val result = calculateComision(payType,previousPay,trasferPay)

        assertEquals(21_50,result)
    }

    @Test
    fun calculateComision_Maestro_moreThenHigherLimit(){
        val payType = "Maestro"
        val previousPay = 0
        val trasferPay = 100_000_00

        val result = calculateComision(payType,previousPay,trasferPay)

        assertEquals(620_00,result)
    }

    @Test
    fun calculateComision_Maestro_normal(){
        val payType = "Maestro"
        val previousPay = 0
        val trasferPay = 10_000_00

        val result = calculateComision(payType,previousPay,trasferPay)

        assertEquals(0,result)
    }


    //caPay function tests

    @Test
    fun canPay_withVkPay() {
        val payType = "Vk Pay"
        val previousPay = 0
        val trasferPay = 1_000_00

        val result = canPay(payType,previousPay,trasferPay)

        assertEquals(true,result)
    }

    @Test
    fun canPay_withVkPay_withTooMuchTransfer() {
        val payType = "Vk Pay"
        val previousPay = 0
        val trasferPay = 20_000_00

        val result = canPay(payType,previousPay,trasferPay)

        assertEquals(false,result)
    }

    @Test
    fun canPay_withVkPay_withTooMuchPrevious() {
        val payType = "Vk Pay"
        val previousPay = 39_000_00
        val trasferPay = 10_000_00

        val result = canPay(payType,previousPay,trasferPay)

        assertEquals(false,result)
    }

    @Test
    fun canPay_withOtherPay() {
        val payType = "Visa"
        val previousPay = 0
        val trasferPay = 1_000_00

        val result = canPay(payType,previousPay,trasferPay)

        assertEquals(true,result)
    }

    @Test
    fun canPay_withOtherPay_withTooMuchTransfer() {
        val payType = "Visa"
        val previousPay = 0
        val trasferPay = 160_000_00

        val result = canPay(payType,previousPay,trasferPay)

        assertEquals(false,result)
    }

    @Test
    fun canPay_withOtherPay_withTooMuchPrevious() {
        val payType = "Visa"
        val previousPay = 550_000_00
        val trasferPay = 100_000_00

        val result = canPay(payType,previousPay,trasferPay)

        assertEquals(false,result)
    }

}