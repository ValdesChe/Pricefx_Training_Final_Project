def productLifeCycle = api.product("ProductLifeCycle")

def attributeAdjPct = api.findLookupTableValues("AttributeAdj", Filter.equal("name", productLifeCycle))
        ?.find()?.attribute1

if(out.BasePrice == null || attributeAdjPct == null){
    api.yellowAlert("Attribute Adjustment not found")
    return
}

return attributeAdjPct * out.BasePrice