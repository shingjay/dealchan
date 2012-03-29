Dealchan.Layouts ||= {}

class Dealchan.Layouts.ShowDealPopover
	
	this.template = JST["templates/layouts/show_deal_popover"]

	this.fancyboxConfig = {
		'autoSize'			: false,
		'width'         	: 450,
		'height'        	: 220,
		'openEffect'		: 'none',
		'content'			: @template,
		'padding'			: 0

	}

	this.showDealPopover = (element)->
		$(element).fancybox( @fancyboxConfig )