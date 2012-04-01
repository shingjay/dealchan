Dealchan.Layouts ||= {}

class Dealchan.Layouts.ShowDealPopover
	
	this.template = JST["templates/layouts/show_deal_popover"]

	this.fancyboxConfig = {
		content 		: @template
		minHeight 		: 400
		minWidth 		: 425
		scrolling 		: no
		title			: 'Deal Information'
		openEffect		: 'elastic'
	}

	this.showDealPopover = (element)->
		$(element).fancybox( @fancyboxConfig )