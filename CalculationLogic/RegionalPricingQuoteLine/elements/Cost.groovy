def productCost = api.productExtension("ProductCost")?.find()?.attribute1
return libs.MoneyUtils.ExchangeRate.convertMoney(productCost, "EUR", out.Currency as String)