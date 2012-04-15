Dealchan.Layouts ||= {}

class Dealchan.Layouts.DealContainer

	# Dynamic insertion of deals from mockdata.json
	this.showMoreDeals = (element, api_url, category_id, category_name)->
		# for counter in [1..20]
			# element.append(@dealGridTemplate)
		console.log api_url

		$.getJSON "api/v0/deals/by_five.json?city=3&category=#{category_id}", (data)->
			#console.log data['deals']
			this.template = JST["templates/layouts/deal_grid"]
			
			template_data = {
				data : data
				category_id : category_id
				category_name : category_name
			}
			element.append(@template(template_data))

# Testing out an approach above, revert back the below code if fail			
#
#			$.each data['deals'], (key, val)->
#				# anonymous function problem here, this is a hack
#				this.dealGridTemplate = JST["templates/layouts/deal_grid"]
#				#console.log key, val
#				element.append( @dealGridTemplate(val) )