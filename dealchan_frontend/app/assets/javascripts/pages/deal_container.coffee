Dealchan.Pages ||= {}

class Dealchan.Pages.DealContainer
	
	# The place to append to
	# the category_id to set
	# make query to server and populate
	this.initializeDeals = (dealsContainer, category_id)->

		$.getJSON "api/v0/deals/by_five.json?city=Penang&category=Miscellaneous", (data)->
			this.template = JST["templates/pages/deal_container"]
			
			template_data = {
				data : data
				category_id : category_id
			}

			dealsContainer.append(@template(template_data))
			#$.each data, (key,value) ->
			#	
			#	dealsContainer.append(@template(value["deal"]))