BigDecimal totalInvoicePrice = 0.0

quote.lineItems?.findAll{
    quoteLineItem ->
        !quoteLineItem.folder // Filtering "non folder" quote
}.each {
    quoteLineItem ->
        BigDecimal invoicePrice = quoteLineItem?.outputs?.find{
            output ->
                output.resultName == "TotalInvoicePrice"
        }?.result

        if(invoicePrice == null){
            api.throwException("Unable to build workflow. Unable to calculate " +
                    " Total Invoice Price for entire quote. Missing for row " +
                    " ${quoteLineItem.sku}")
        }

        totalInvoicePrice += invoicePrice
}

def approvalLevels = api.findLookupTableValues("ApprovalLevelsRevenue")

approvalLevels.sort{
    it.name // Sort by name
}.each{
    approvalLevel ->
        def threshold = approvalLevel?.attribute1
        def userGroup = approvalLevel?.attribute2
        api.trace("Total $totalInvoicePrice", approvalLevel)
        if(totalInvoicePrice >= threshold){
            workflow.addApprovalStep(userGroup)
                    .withReasons("Total Invoice Price > "
                            + (threshold as String) + "EUR")
                    .withUserGroupApprovers(userGroup)
                    .withMinApprovalsNeeded(1)
        }
}