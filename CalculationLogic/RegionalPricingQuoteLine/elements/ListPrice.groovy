
// Get customer region listPrice
def plFilters = [
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

return libs.MoneyUtils.ExchangeRate.convertMoney(resultPrice, fromCurrency , out.Currency as String)