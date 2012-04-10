Dealchan.Layouts ||= {}

class Dealchan.Layouts.DealContainer

	# Dynamic insertion of deals from mockdata.json
	this.showMoreDeals = (element, api_url)->
		# for counter in [1..20]
			# element.append(@dealGridTemplate)

		$.getJSON 'mockdata.json', (data)->
			#console.log data['deals']

			$.each data['deals'], (key, val)->
				# anonymous function problem here, this is a hack
				this.dealGridTemplate = JST["templates/layouts/deal_grid"]
				#console.log key, val
				element.append( @dealGridTemplate(val) )