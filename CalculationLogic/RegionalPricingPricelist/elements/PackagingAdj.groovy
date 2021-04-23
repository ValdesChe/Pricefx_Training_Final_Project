def productSize = api.product("Size")

def packagingAdjPct = api.findLookupTableValues("PackagingAdj", Filter.equal("name", productSize))
        ?.find()?.attribute1


if(out.BasePrice == null || packagingAdjPct == null){
    api.yellowAlert("Packaging Adjustment not found")
    return
}

return packagingAdjPct * out.BasePrice