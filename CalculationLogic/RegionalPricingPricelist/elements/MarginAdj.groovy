def productGroup = api.product("ProductGroup")

def marginAdjPct = api.findLookupTableValues("MarginAdj", Filter.equal("name", productGroup))
        ?.find()?.value
if(out.BasePrice == null || marginAdjPct == null){
    api.yellowAlert("Margin Adjustment not found")
    return
}

return marginAdjPct * out.BasePrice