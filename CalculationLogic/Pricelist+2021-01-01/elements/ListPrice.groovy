
if(out.BasePrice == null || out.AttributeAdjAbs == null || out.MarginAdjAbs == null){
    api.addWarning("List Price cannot be calculated: missing parameter(s).")
    return
}

BigDecimal listPrice = out.BasePrice + out.MarginAdjAbs + out.AttributeAdjAbs
def roundedListPrice = libs.SharedLib.RoundingUtils.round(listPrice, 2)

api.trace("List Price", listPrice)
api.trace("Rounded List Price", roundedListPrice)

return roundedListPrice