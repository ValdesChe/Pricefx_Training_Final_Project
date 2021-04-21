if(out.InvoicePrice == null || out.Cost == null){
    api.addWarning("Pocket Margin cannot be calculated : missing parameters")
    return
}

return out.InvoicePrice - out.Cost