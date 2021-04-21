if(out.SalesDiscountAbs == null || out.ListPrice == null){
    api.addWarning("Invoice Price cannot be calculated: missing parameter(s)")
    return
}

return out.ListPrice - out.SalesDiscountAbs