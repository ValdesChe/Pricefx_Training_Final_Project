def taxAdjPct = api.findLookupTableValues("TaxAdj", Filter.equal("name", out.Region))
        ?.find()?.value

if(out.BasePrice == null || taxAdjPct == null){
    api.yellowAlert("Tax Adjustment not found")
    return
}

return taxAdjPct * out.BasePrice