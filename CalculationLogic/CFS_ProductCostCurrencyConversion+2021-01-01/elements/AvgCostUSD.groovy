def cost = api.currentItem()?.attribute1 //Cost of Current item

def date = api.targetDate().format("yyyy-MM-dd")

def filters = [
        Filter.equal("FromCurrency", "EUR"),
        Filter.equal("ToCurrency", "USD"),
        Filter.lessThan("ValidFrom", date),
        Filter.greaterThan("ValidTo", date)
]
def exchangeRate = api.findLookupTableValues("ExchangeRate", *filters)?.find()?.attribute2

if(exchangeRate == null){
    api.addWarning("Conversion cannot be processed -> Exchange rate not found")
    return
}

return cost != 0 ? (cost * exchangeRate) : null