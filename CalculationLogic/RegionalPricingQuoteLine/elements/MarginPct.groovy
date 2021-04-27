
if(out.Margin == null || out.ListPrice == null){
    api.yellowAlert("Cannot calculate Absolute Margin  -> missing params")
    return
}

if(out.ListPrice == 0){
    api.yellowAlert("Cannot calculate Margin in % because ListPrice = 0 -> Division by 0")
    return
}


return out.Margin / out.ListPrice