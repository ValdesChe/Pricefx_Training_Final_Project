
if(out.ListPrice == null || out.Quantity  == null ){
    api.yellowAlert("Cannot calculate Total Invoice Price  -> missing params qty or listPrice")
    return
}

return out.Quantity * out.ListPrice