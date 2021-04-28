import net.pricefx.common.api.InputType

if (quoteProcessor.isPostPhase()) {
    return
}


def deliveryLabels = [
        "Express" : "Express Delivery",
        "Standard" : "Standard Delivery"
]
quoteProcessor.addOrUpdateInput([
        "name": "DeliveryType",
        "label": "Delivery Type",
        "type": InputType.OPTION,
        "required" : true,
        "parameterConfig": [
                "labels" : deliveryLabels
        ],
        "valueOptions": deliveryLabels.keySet()
])


