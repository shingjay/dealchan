Dealchan.Pages ||= {}

class Dealchan.Pages.DealContainer
	
	# The place to append to
	# the category_id to set
	# make query to server and populate
	this.initializeDeals = (dealsContainer, category_id, category_name, callback_function)->
		console.log "initializeDeals called"
		$.getJSON "api/v0/deals/by_five.json?city=3&category=#{category_id}", (data)->
			this.template = JST["templates/pages/deal_container"]

			template_data = {
				data : data
				category_id : category_id
				category_name : category_name
			}
			dealsContainer.append(@template(template_data))

			callback_function(category_id, category_name)
			#$.each data, (key,value) ->
			#	
			#	dealsContainer.append(@template(value["deal"]))


# Testing out an approach to attach deal ids to a deal below
			#console.log data['deals']

			for singleDeal in data['deals']
				deal_id = singleDeal['deal']['id']
				$("#deal-id-#{deal_id}").on 'click',{ id : deal_id }, (event)->
					Dealchan.Layouts.ShowDealPopover.displayPopover event.data.id
					#console.log event.data.id