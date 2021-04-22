import com.googlecode.genericdao.search.Filter

def pricelistIterator = api.stream("PL", null, Filter.equal("approvalState", "APPROVED"))
def targetDates = pricelistIterator?.collectEntries { pl -> [(pl.id): (pl.targetDate)] }
pricelistIterator?.close()
api.trace(pricelistIterator)
api.trace(targetDates)
def pricelistIds = targetDates?.keySet() as List

// TODO place your code here
// HINTS:
// - use only PLIs from Priceslists listed in variable "pricelistIds"
// - target dates of the Pricelists are in variable "targetDates"

// Target table => Datamart
def target = api.getDatamartRowSet("target")
def filter = Filter.in("pricelistId", pricelistIds)
def pricelistItemIterator = api.stream("PLI", "sku", filter)

def pricesListItemRow
pricelistItemIterator?.each {
    priceListItem ->
        pricesListItemRow = [
                ProductId: priceListItem.sku,
                TargetDate: targetDates?.getAt(priceListItem.pricelistId),
                ResultPrice: priceListItem.resultPrice,
                Currency: priceListItem.currency
        ]
        api.trace(priceListItem.sku,  priceListItem.resultPrice)

        // Add the row to datamart
        target?.addRow(pricesListItemRow)
}

pricelistIterator?.close()