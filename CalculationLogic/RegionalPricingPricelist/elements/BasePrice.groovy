def productCost = api.productExtension("ProductCost")?.getAt(0)?.attribute1
def productCurrency = api.product("Currency")
return libs.MoneyUtils.ExchangeRate.convertMoney(productCost, productCurrency, out.Currency)