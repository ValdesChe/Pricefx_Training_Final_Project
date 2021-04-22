import java.math.RoundingMode

/***
 * Round a given number with nbDecimals digits
 * @param number
 * @param noDecimals
 * @return
 */
BigDecimal round(BigDecimal number, int noDecimals) {
    if(number == null) {
        return null
    }
    return number.setScale(noDecimals, RoundingMode.HALF_UP)
}