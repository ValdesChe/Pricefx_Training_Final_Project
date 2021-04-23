
if(out.BasePrice == null
        || out.MarginAdj == null
        || out.AttributeAdj == null
        || out.PackagingAdj == null
        || out.TaxTariffAdj == null){
    api.yellowAlert("Cannot calculate the list price -> Missing parameter(s)")
    return
}

return out.BasePrice + out.MarginAdj + out.AttributeAdj + out.PackagingAdj + out.TaxTariffAdj
