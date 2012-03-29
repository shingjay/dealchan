Dealchan.Layouts ||= {}

class Dealchan.Layouts.CityAskModal 
	
	this.template = JST["templates/layouts/city_ask_modal"]

	this.fancyboxConfig = {
		'autoSize'			: false,
		'width'         	: 450,
		'height'        	: 220,
		'openEffect'		: 'none',
		'closeEffect'		: 'fade',
		'content'			: @template,
		'closeBtn'			: false,
		'closeClick'		: false,
		'modal'				: true,
		'padding'			: 0

	}

	this.cityAskModal = (element)->
		$(element).fancybox( @fancyboxConfig )