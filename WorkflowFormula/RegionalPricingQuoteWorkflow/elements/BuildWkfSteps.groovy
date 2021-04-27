if(out.Region == null){
    api.throwException("Region not found")
}
api.trace(quote)

def filters = [
        Filter.equal("Region", out.Region as String)
]

def fields = ["attribute1", "attribute2"]

def thresholds = api.findLookupTableValues("ApprovalThreshold", null, fields, "key1", *filters)

if (!thresholds){
    api.throwException("Approval threshold not found for region '${out.Region}'")
}

def totalInvoice = quote?.outputs?.find{ it.resultName == "TotalInvoicePrice"}?.result
api.trace(totalInvoice)
if(totalInvoice == null){
    api.throwException("Total Invoice price not found for the quote")
}

thresholds.each {
    threshold ->
        if(totalInvoice > (threshold.attribute2 as BigDecimal)){
            workflow.addApprovalStep(threshold.attribute1)
            .withUserGroupApprovers(threshold.attribute1)
            .withReasons("The quote needs a review from the group ${threshold.attribute1}")
        }
}


api.trace(thresholds)