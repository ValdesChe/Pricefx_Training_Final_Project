
def regions = api.findLookupTableValues("Region", "name")?.collect{
    regionItem ->
        regionItem.name
}
api.option("Region", regions)