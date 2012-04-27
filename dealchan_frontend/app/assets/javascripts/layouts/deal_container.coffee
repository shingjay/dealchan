Dealchan.Layouts ||= {}

class Dealchan.Layouts.DealContainer

	this.showMoreDeals = (element, api_url, category_id, category_name)->
		
		console.log api_url

		$.getJSON api_url, (data)->
			
			this.template = JST["templates/layouts/deal_grid"]
			
			template_data = {
				data : data
				category_id : category_id
				category_name : category_name
			}
			
			element.append(@template(template_data))

			for singleDeal in data['deals']
				deal_id = singleDeal['deal']['id']
				$("#deal-id-#{deal_id}").on 'click',{ id : deal_id }, (event)->
					Dealchan.Layouts.ShowDealPopover.displayPopover event.data.id