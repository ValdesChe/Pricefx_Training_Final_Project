
if(out.ListPrice == null || out.InvoicePrice == null){
    api.yellowAlert("Cannot calculate Sales Discount in %  -> missing params")
    return
}

def salesDiscountPerUnit = out.ListPrice - out.InvoicePrice

if(out.ListPrice == 0){
    api.yellowAlert("Cannot calculate Sales Discount in % because ListPrice == 0 , division by 0")
    return
}
api.trace(out.ListPrice)
api.trace(out.InvoicePrice)
api.trace(salesDiscountPerUnit)
return salesDiscountPerUnit / out.ListPrice