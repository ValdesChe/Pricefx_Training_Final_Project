if (out.ListPrice == null || out.SalesDiscount == null){
    api.addWarning("Sales Discount cannot be calculated: missing parameter(s)")
    return
}

return out.ListPrice * out.SalesDiscount