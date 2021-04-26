def ctx = api.getDatamartContext()

def dm = ctx.getDatamart("Transaction")

def query = ctx.newQuery(dm, true)
        .select("SUM(Quantity)", "qty")
        .where(
                Filter.equal("CustomerId", out.CustomerId),
                Filter.equal("ProductId", out.ProductId),
        )

return ctx.executeQuery(query)?.getData()?.find()?.qty