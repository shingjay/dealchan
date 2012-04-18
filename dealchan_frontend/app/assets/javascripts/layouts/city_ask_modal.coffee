Dealchan.Layouts ||= {}

class Dealchan.Layouts.CityAskModal 

	this.cityAskModal = (callback_function)->

		$.getJSON "/api/v0/cities/list.json", (data)->

			this.template = JST["templates/layouts/city_ask_modal"]
			window.dd = data
			this.fancyboxConfig = {
				content 		: @template(data)
				minHeight 		: 450
				minWidth 		: 425
				scrolling 		: no
				title			: 'Welcome'
				openEffect		: 'fade'
				closeBtn		: false
				modal           : true
			}	

			$.fancybox( @fancyboxConfig )

			callback_function()
