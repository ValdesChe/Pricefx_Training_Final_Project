
if(out.InvoicePrice == null || out.Cost == null){
    api.yellowAlert("Cannot calculate Absolute Margin  -> missing params")
    return
}

return out.InvoicePrice - out.Cost