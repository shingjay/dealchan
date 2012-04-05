Dealchan.Layouts ||= {}

class Dealchan.Layouts.DealContainer

	this.dealGridTemplate = JST["templates/layouts/deal_grid"]

	# Manual insertion of 20 deals whenever showmore is clicked
	this.showMoreDeals = (element, api_url)->
		for counter in [1..20]
			element.append(@dealGridTemplate)

