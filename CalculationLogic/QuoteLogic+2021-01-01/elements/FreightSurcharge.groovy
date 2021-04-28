if(api.isDebugMode()){
    input.Customer = "CD-00005"
    input.DeliveryType = "Express"
}

def customerCountry = api.customer("Country", input.Customer)


def freightSurcharge = api.vLookup("FreightSurcharge", "FreightSurcharge", customerCountry, input.DeliveryType)

return freightSurcharge