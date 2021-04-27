if(out.ListPrice == null || out.BasePrice == null){
    api.addWarning("Cannot calculate the Gross Margin -> Missing parameter(s)")
    return
}

if(out.ListPrice == 0){
    api.addWarning("Cannot calculate the Gross Margin because listPrice == 0 -> Division by 0")
    return
}

def grossMargin =  ( out.ListPrice - out.BasePrice ) / out.ListPrice

def businessUnit = api.product("BusinessUnit") as String
def filters = [
        Filter.equal("BusinessUnit", businessUnit)
]

def fields = ["attribute1", "attribute2", "attribute3"] as List
def threshold = api.findLookupTableValues("PriceStrategy", null, fields, null, *filters)
        ?.find()

if(grossMargin < (threshold?.attribute1 as BigDecimal)){
    api.criticalAlert("Critical Alert : Margin is toooo low")
}

if(grossMargin > (threshold?.attribute1 as BigDecimal) && grossMargin < (threshold?.attribute2 as BigDecimal)){
    api.redAlert("Red Alert : Margin is low")
}

if(grossMargin > (threshold?.attribute2 as BigDecimal) && grossMargin < (threshold?.attribute3 as BigDecimal)){
    api.yellowAlert("Yellow Alert : Margin is low")
}

return grossMargin