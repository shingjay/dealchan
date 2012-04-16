Dealchan.Layouts ||= {}

class Dealchan.Layouts.ShowDealPopover

	this.displayPopover = (deal_id) ->

		console.log "calling api on #{deal_id}"

		$.getJSON "api/v0/deals/by_id.json?id=#{deal_id}", (data)->

			this.template = JST["templates/layouts/show_deal_popover"]

			template_data = {
				data : data
			}

			this.fancyboxConfig = {
				content 		: @template(template_data)
				maxHeight 		: 800
				maxWidth 		: 800
				scrolling 		: no
				title			: 'Deal Information'
				openEffect		: 'elastic'
			}

			$.fancybox(@fancyboxConfig)