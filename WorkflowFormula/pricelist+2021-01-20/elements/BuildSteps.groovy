
/**
 * Calculates expected profitability ( Margin % ) of the given Price List Item (PLI)
 *
 * @param pli Object (as Map) of Pricelist Line Item (i.e. single row of the Pricelist)
 * @return the calculated expected profitability ( Margin % ) of the line
 */
def getMarginPct(pli) {
    def basePrice = pli.calculationResults?.find { it.resultName == "BasePrice" }?.result
    def listPrice = pli.calculationResults?.find { it.resultName == "ListPrice" }?.result
    def marginPct = null
    if (listPrice && basePrice != null) marginPct = (listPrice - basePrice) / listPrice
    return marginPct
}


def priceListId = pricelist.id
api.trace(priceListId)
// Fetch the PLI
def filters = [
       Filter.equal("pricelistId", priceListId)
]

def pliIterator = api.stream("PLI", null, *filters)

workflow.addWatcherStep("Pricing Manager")
        .withUserGroupWatchers("PriceManager")
        .withReasons("The pricing manager needs to be notified about new pricelists")

def violationCount = 0
// vLookup - cached

pliIterator?.each {
    pliItem ->
        String businessUnit = api.product("BusinessUnit", pliItem.sku)
        def thresholds = api.vLookup("PriceListApprovalLevels", "MarginThreshold", businessUnit)
        api.trace("TH =", thresholds)
        if(thresholds == null){
            return
        }
        def marginPct = getMarginPct(pliItem)
        if(marginPct != null && marginPct < thresholds){
            violationCount++
        }
}

if(violationCount > 0){
    workflow.addApprovalStep("Product Manager")
            .withUserGroupApprovers("ProductManager")
            .withReasons("Pricelist has expected Margin% less than the threshold")
            .withMinApprovalsNeeded(2)
}

pliIterator?.close()