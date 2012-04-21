Dealchan.Layouts ||= {}

class Dealchan.Layouts.CityAskModal 

	this.cityAskModal = (selected_location, callback_function)->

		$.getJSON "/api/v0/cities/list.json", (data)->

			this.template = JST["templates/layouts/city_ask_modal"]
			this.city_dropdown = JST["templates/layouts/city_dropdown"]

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

			$(".left-header-bar").append(@city_dropdown(data))
			$("#cities-dropdown option[value='#{selected_location}']").prop('selected', true)

			if not selected_location
				$.fancybox( @fancyboxConfig )
			callback_function()

			

