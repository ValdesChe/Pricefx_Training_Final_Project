if (out.Region == null) {
    api.addWarning("Cannot find a ListPrice because Region is not available.")
    return null
}

def pliResultPrice = api.pricelist(out.Region as String)
def pliCurrency = api.vLookup("Region", "Currency", out.Region as String)    // api.pricelist(region, "Currency")

api.trace("pliResultPrice", pliCurrency, pliResultPrice)

return libs.MoneyUtils.ExchangeRate.convertMoney(pliResultPrice, pliCurrency, out.Currency)

// Get customer region listPrice
/* def plFilters = [
        Filter.equal("label", out.Region)
]

def customerPListId = api.find("PL", *plFilters)?.find()?.id

//  listPrice (per unit)
def pliFilters = [
        Filter.equal("sku", out.ProductId),
        Filter.equal("pricelistId", customerPListId)
]

def pliResult = api.find("PLI", *pliFilters)?.find()

def resultPrice = pliResult?.resultPrice
def fromCurrency = pliResult?.currency

return libs.MoneyUtils.ExchangeRate.convertMoney(resultPrice, fromCurrency , out.Currency)
 */