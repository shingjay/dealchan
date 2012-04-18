Dealchan.Pages ||= {}

class Dealchan.Pages.DealContainer
	
	# The place to append to
	# the category_id to set
	# make query to server and populate
	this.initializeDeals = (element, api_url, category_id, category_name, callback_function)->

		$.getJSON api_url, (data)->
		
			this.template = JST["templates/pages/deal_container"]
			
			template_data = {
				data : data
				category_id : category_id
				category_name : category_name
			}
			
			element.append(@template(template_data))
			callback_function(category_id, category_name)
			
			for singleDeal in data['deals']
		
				deal_id = singleDeal['deal']['id']
		
				$("#deal-id-#{deal_id}").on 'click',{ id : deal_id }, (event)->
		
					Dealchan.Layouts.ShowDealPopover.displayPopover event.data.id