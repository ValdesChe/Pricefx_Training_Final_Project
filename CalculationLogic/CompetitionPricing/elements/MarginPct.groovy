if(out.ListPrice == null || out.ProductCost == null ){
    api.addWarning("Margin Percentage cannot be calculated -> Missing parameter(s)")
    return
}

if(out.ListPrice == 0){
    api.addWarning("Margin Percentage cannot be calculated because ListPrice = 0 -> division by 0")
    return
}

return (out.ListPrice - out.ProductCost) / out.ListPrice