def productLifeCycle = api.product("ProductLifeCycle") // Get the product group of product
def attributeAdjPct =  api.vLookup("AttributeAdj", "AttributeAdj", productLifeCycle)
api.trace(productLifeCycle)
if(attributeAdjPct == null){
    api.addWarning("Unable to look up Attribute Adjustment with the Product Life Cycle")
    return
}

return attributeAdjPct