def businessUnit = api.product("BusinessUnit")
return api.vLookup("CompetitionStrategy", "CompetitivePositioning", businessUnit)