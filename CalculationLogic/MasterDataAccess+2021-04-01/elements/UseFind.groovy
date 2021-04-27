def start = 0
def data = null
List<String> fields = ["sku", "attribute1", "attribute2"]
// Fetching products
while (data = api.find(out.Variables, start, api.getMaxFindResultsLimit(), "sku", fields)){
    start += data.size()
    data?.each {
        product ->
            def productTrace = [
                    productId: product.sku,
                    ProductGroup: product.attribute1,
                    BusinessUnit: product.attribute1
            ]
            api.trace("Row ", productTrace)
    }
}