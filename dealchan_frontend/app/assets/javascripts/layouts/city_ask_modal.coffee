Dealchan.Layouts ||= {}

class Dealchan.Layouts.CityAskModal 
	
	this.template = JST["templates/layouts/city_ask_modal"]

	this.fancyboxConfig = {
		content 		: @template
		minHeight 		: 450
		minWidth 		: 425
		scrolling 		: no
		title			: 'Welcome'
		openEffect		: 'fade'
	}

	this.cityAskModal = (element)->
		$(element).fancybox( @fancyboxConfig )

###
	TODO
	edit the fancybox to have some form of border

	Override somewhere
	closeBtn:'<div title="Close" class="fancybox-item fancybox-close"></div>'
	to
	closeBtn:'<div title="" class="fancybox-item fancybox-close"></div>'
###