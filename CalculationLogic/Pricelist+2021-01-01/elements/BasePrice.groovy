// Getting the Average Cost of Product
def averageCost = api.productExtension("ProductCost")?.getAt(0)?.attribute1

if (!averageCost){
    api.addWarning("Could not get the average Cost of the product")
    return
}

return averageCost