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
		closeBtn		: false
		modal           : true
	}

	this.cityAskModal = (element)->
		$(element).fancybox( @fancyboxConfig )
