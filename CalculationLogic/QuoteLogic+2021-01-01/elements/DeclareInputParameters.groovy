if(api.isSyntaxCheck()){
    // Quantity Input
    api.integerUserEntry("Quantity")
    def quantityParam = api.getParameter("Quantity")
    // Set the label
    quantityParam.setLabel("Required Quantity")
    // Setting the validators
    quantityParam.setRequired(true)
    quantityParam.setConfigParameter("inputType", "range")
    quantityParam.setConfigParameter("from", 1)

    // Sales Discount Input
    api.userEntry("SalesDiscount")
    def salesDiscountParam = api.getParameter("SalesDiscount")
    salesDiscountParam.setLabel("Sales Discount (in %)")
    salesDiscountParam.setRequired(true)
    salesDiscountParam.setValue(0) // default value
    salesDiscountParam.setConfigParameter("inputType", "PERCENT")
    salesDiscountParam.setConfigParameter("from", 0)
    salesDiscountParam.setConfigParameter("to", 1)

    // Abort
    api.abortCalculation()
}