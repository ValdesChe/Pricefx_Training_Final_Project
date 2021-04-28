import net.pricefx.server.util.metamodel.Column
import net.pricefx.server.util.metamodel.Table

def ctx = api.getDatamartContext()
def dm = ctx.getDatamart("Transaction") as Table
def column = dm.getColumn("InvoiceDateYear") as Column


return ctx.dimFilterEntry("Year", column)?.getValue()