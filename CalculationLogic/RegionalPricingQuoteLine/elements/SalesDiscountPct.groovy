
if(out.ListPrice == null || out.InvoicePrice == null){
    api.addWarning("Cannot calculate Sales Discount in %  -> missing params")
    return
}

def salesDiscountPerUnit = out.ListPrice - out.InvoicePrice

if(out.ListPrice == 0){
    api.addWarning("Cannot calculate Sales Discount in % because ListPrice == 0 , division by 0")
    return
}

def salesDiscountPct = salesDiscountPerUnit / out.ListPrice


def businessUnit = api.product("BusinessUnit") as String
def filters = [
        Filter.equal("BusinessUnit", businessUnit)
]

def fields = ["attribute1", "attribute2"] as List
def threshold = api.findLookupTableValues("PriceStrategy", null, fields, null, *filters)
        ?.find()

if(salesDiscountPct > (threshold?.attribute2 as BigDecimal)){
    api.criticalAlert("Critical Alert : Sales Discount is too high")
}

if(salesDiscountPct < (threshold?.attribute2 as BigDecimal) && salesDiscountPct > (threshold?.attribute1 as BigDecimal)){
    api.redAlert("Red Alert : Sales Discount is high")
}

return salesDiscountPct

