def productGroup = api.product("ProductGroup") // Get the product group of product
def marginAdjPct =  api.vLookup("MarginAdj", productGroup)
if(!productGroup || !marginAdjPct){
    api.addWarning("Could not found the product group or Missing margin adjustment for the product")
    return
}

return marginAdjPct