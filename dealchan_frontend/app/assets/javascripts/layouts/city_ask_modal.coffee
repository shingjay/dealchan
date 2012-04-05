Dealchan.Layouts ||= {}

class Dealchan.Layouts.CityAskModal 
	
	this.template = JST["templates/layouts/city_ask_modal"]

	this.fancyboxConfig = {
		content 		: @template
		minHeight 		: 400
		minWidth 		: 425
		scrolling 		: no
		title			: 'Welcome'
		openEffect		: 'fade'
		closeBtn		: false
	}

	this.cityAskModal = (element)->
		$(element).fancybox( @fancyboxConfig )