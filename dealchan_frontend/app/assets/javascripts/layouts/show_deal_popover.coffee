Dealchan.Layouts ||= {}

class Dealchan.Layouts.ShowDealPopover
	
	this.template = JST["templates/layouts/show_deal_popover"]

	this.fancyboxConfig = {
		content 		: @template
		maxHeight 		: 800
		maxWidth 		: 800
		scrolling 		: no
		title			: 'Deal Information'
		openEffect		: 'elastic'
	}

	this.showDealPopover = (element)->
		$(element).fancybox( @fancyboxConfig )