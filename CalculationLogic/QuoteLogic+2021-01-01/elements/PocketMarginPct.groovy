if(out.PocketMarginAbs == null || out.ListPrice == null){
    api.addWarning("Pocket Margin Percent cannot be calculated : missing parameters")
    return
}

if(out.ListPrice == 0){
    api.addWarning("List Price is 0 -> The pocket  Margin cannot be calculated, -> Division by 0")
    return
}

def pocketMarginPct = out.PocketMarginAbs / out.ListPrice

// Adding a business alert
if(pocketMarginPct < 0){
    api.redAlert("Pocket Margin % is too low")
}

return pocketMarginPct