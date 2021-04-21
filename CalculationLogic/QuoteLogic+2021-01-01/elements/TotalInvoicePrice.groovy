if(out.InvoicePrice == null || out.Quantity == null){
    api.addWarning("Total Invoice cannot be calculated: missing parameter(s)")
    return
}

return out.InvoicePrice * out.Quantity