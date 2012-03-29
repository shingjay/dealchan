Dealchan.Layouts ||= {}

class Dealchan.Layouts.ShowDealPopover
	
	this.template = JST["templates/layouts/show_deal_popover"]

	this.fancyboxConfig = {
		'transitionIn'			:	'elastic',
		'transitionOut'			:	'elastic',
		'speedIn'				:	600, 
		'speedOut'				:	200, 
		'overlayShow'			:	true,
		'content'				: 	@template,
		'enableEscapeButton'	:   true,
		'showCloseButton'		:   true,
		'title'					:   'Winning!'

	}

	this.showDealPopover = (element)->
		$(element).fancybox( @fancyboxConfig )