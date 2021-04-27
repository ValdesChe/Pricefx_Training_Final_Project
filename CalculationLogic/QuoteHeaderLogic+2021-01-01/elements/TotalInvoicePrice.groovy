if(quoteProcessor.isPrePhase()){
    return
}

List<Object> lineItems = quoteProcessor.quoteView.lineItems.findAll{ lineItem -> !lineItem.folder}

BigDecimal sum = 0.0

List<String> warnings = null
for (lineItem in lineItems){
    def itemInvoicePrice =  lineItem?.outputs?.find({output -> output.resultName == "TotalInvoicePrice"})?.result

    if(itemInvoicePrice == null){
        sum = null
        warnings = ["Unable to calculate: value for TotalInvoicePrice is " +
                                      "missing on one of the line items."]
        break
    }

    sum += itemInvoicePrice ?: 0
}

// Add output
def output = [
        resultName   : "TotalInvoicePrice",
        resultLabel  : "Total Invoice Price",
        result       : sum,
        formatType   : "MONEY_EUR",
        resultType   : "SIMPLE",
        cssProperties: "background-color:#99FFDD",
        warnings     : warnings
]
quoteProcessor.addOrUpdateOutput(output)