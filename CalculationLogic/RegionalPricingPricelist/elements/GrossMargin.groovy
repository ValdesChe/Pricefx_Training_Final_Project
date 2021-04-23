if(out.ListPrice == null || out.BasePrice == null){
    api.addWarning("Cannot calculate the Gross Margin -> Missing parameter(s)")
    return
}

if(out.ListPrice == 0){
    api.addWarning("Cannot calculate the Gross Margin because listPrice == 0 -> Division by 0")
    return
}

return ( out.ListPrice - out.BasePrice ) / out.ListPrice