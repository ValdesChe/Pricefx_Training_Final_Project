def marginAdjPct = PriceListLib.marginAdj()
if(marginAdjPct == null){
    api.addWarning("Could not found the product group or Missing margin adjustment for the product")
    return
}

return marginAdjPct