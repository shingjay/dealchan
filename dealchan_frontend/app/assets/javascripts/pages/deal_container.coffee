Dealchan.Pages ||= {}

class Dealchan.Pages.DealContainer

	# The place to append to
	# the category_id to set
	# make query to server and populate
	this.initializeDeals = (element, api_url, category_id, category_name, callback_function)->

		this.template = JST["templates/pages/deal_container"]

		template_data = {
			category_id : category_id
			category_name : category_name['label']
		}

		containerTemplate = @template(template_data)

		window.tempo = containerTemplate

		element.append(containerTemplate)

		$.getJSON api_url, (data)->
		
			this.template = JST["templates/layouts/deal_grid"]
			
			template_data = {
				data : data
				category_id : category_id
				category_name : category_name['label']
			}
			
			console.log category_id
			console.log template_data
			$("#deal-container-#{category_id} .deal-container").append(@template(template_data))

			if data.deals.length is 0
				$("#deal-container-#{category_id}").hide()

			#element.append(@template(template_data))
			callback_function(category_id, category_name)
			
			for singleDeal in data['deals']
		
				deal_id = singleDeal['deal']['id']
		
				$("#deal-id-#{deal_id}").on 'click',{ id : deal_id }, (event)->
		
					Dealchan.Layouts.ShowDealPopover.displayPopover event.data.id