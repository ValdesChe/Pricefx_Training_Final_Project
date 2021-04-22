def target = api.getDatamartRowSet("target")
def filters = [
        Filter.equal("name", "ProductCost")
]

def pxIterator = api.stream("PX", "sku", *filters) // retrieve ProductCost table !! api.find limited

def newRow
pxIterator.each {
    pxCurrentItem ->
        newRow = [
                ProductId: pxCurrentItem?.sku,
                AvgCost: pxCurrentItem?.attribute1
        ]

        target?.addRow(newRow)

        api.trace(pxCurrentItem.sku, pxCurrentItem.attribute1) // For testing
}

pxIterator?.close()