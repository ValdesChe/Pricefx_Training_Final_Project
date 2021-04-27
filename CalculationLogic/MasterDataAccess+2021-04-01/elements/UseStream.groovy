def fields = ["sku", "attribute1", "attribute2"]
def productIterator = api.stream(out.Variables, "sku", fields)

while(productIterator?.hasNext()){
    def currentProduct = productIterator.next()
    if(currentProduct.sku != null && currentProduct.attribute1 != null){
        def row = [
                sku: currentProduct.sku,
                ProductGroup: currentProduct.attribute1,
                BusinessUnit: currentProduct.attribute2,
        ]

        api.trace("Row =>", row)
    }
}

productIterator?.close()