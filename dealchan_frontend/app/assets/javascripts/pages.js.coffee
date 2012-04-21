# Place all the behaviors and hooks related to the matching controller here.
# All this logic will automatically be available in application.js.
# You can use CoffeeScript in this file: http://jashkenas.github.com/coffee-script/

jQuery ->
	if not $.cookie('selected_location')	
		Dealchan.Layouts.CityAskModal.cityAskModal ()->
			$('#submit-location').on 'click', ()->
				console.log $('#location-dropdown').val()
				$.cookie('selected_location', $('#location-dropdown').val(),  { expires: 1200 })
				$.fancybox.close()
	
	# Trigger City Selection Modal

	# Sidebar implementation
	$('#jump-to-shortcut a').stop().animate {
		'marginLeft':'-85px'}, 1000
	$('#jump-to-shortcut > li').hover(
		-> $('a',$(this)).stop().animate({'marginLeft':'-2px'},200)
		-> $('a',$(this)).stop().animate({'marginLeft':'-85px'},200)
	)

	# Manual work 
	deal_categories = 
		"1":
			"label": "Activities & Events"
		"2":
			"label": "Food & Drinks"
		"3":
			"label": "Health & Beauty"
		"4":
			"label": "Shopping & Services"
		"5":
			"label": "Travel"
		"6":
			"label": "Miscellaneous"

	deal_price_ranges = 
		"1":
			"label": "RM0 - RM10", 
			"min_price":0, 
			"max_price":10
		"2":
			"label": "RM10 - RM50", 
			"min_price":10, 
			"max_price":50
		"3":
			"label": "RM50 - RM100", 
			"min_price":50, 
			"max_price":100
		"4":
			"label": "RM100 - RM500", 
			"min_price":100, 
			"max_price":500
		"5":
			"label": "RM500 - RM1000", 
			"min_price":500, 
			"max_price":1000
		"6":
			"label": "RM1000+", 
			"min_price":1000, 
			"max_price":''

	window.pageCount = [
		1,
		1,
		1,
		1,
		1,
		1,
		1
	]

	# Determine if we are sorting by price or category

	city_selected = $.cookie('selected_location')

	# We are sorting by price
	if window.page_type is 'price'
		
		console.log 'populate by price'
		
		# Dynamically attach event handlers to click events
		for sort_id, sort_object of deal_price_ranges

			pageNumber = window.pageCount[sort_id]

			initialize_api_url = "/api/v0/deals/by_price_range.json?" + 
				"page=#{pageNumber}" + 
				"&city=#{city_selected}&min_price=#{sort_object['min_price']}" + 
				"&max_price=#{sort_object['max_price']}"

			Dealchan.Pages.DealContainer.initializeDeals $("#deals-container"), initialize_api_url, sort_id, sort_object, (sort_id, sort_object)->
				$("#show-more-button-#{sort_id}").on 'click', { cat : sort_id }, (event)->
					
					console.log "pushed on show-more-button-#{sort_id}"

					window.pageCount[sort_id]++
					pageNumber = window.pageCount[sort_id]

					show_more_api_url = "/api/v0/deals/by_price_range.json?" + 
						"page=#{pageNumber}" + 
						"&city=#{city_selected}&min_price=#{sort_object['min_price']}" + 
						"&max_price=#{sort_object['max_price']}"

					Dealchan.Layouts.DealContainer.showMoreDeals $("#deal-container-#{event.data.cat} .deal-container"), show_more_api_url, sort_id, sort_object
				
				$("#jump-to-#{sort_id}").on 'click', { cat : sort_id }, (event)->
					
					console.log "jump to cat " + event.data.cat
					
					$('html,body').animate {
						scrollTop: $("#deal-container-#{event.data.cat}").offset().top - 120
					}, 600

	# We are sorting by category
	else if window.page_type is 'category'
		
		console.log 'populate by category'

		# Dynamically attach event handlers to click events
		for sort_id, sort_object of deal_categories

			pageNumber = window.pageCount[sort_id]

			initialize_api_url = "/api/v0/deals/by_category.json?" +
				"page=#{pageNumber}" + 
				"&city=#{city_selected}" + 
				"&category=#{sort_id}"

			Dealchan.Pages.DealContainer.initializeDeals $("#deals-container"), initialize_api_url, sort_id, sort_object, (sort_id, sort_object)->
				$("#show-more-button-#{sort_id}").on 'click', { cat : sort_id }, (event)->
					
					console.log "pushed on show-more-button-#{sort_id}"

					window.pageCount[sort_id]++
					pageNumber = window.pageCount[sort_id]

					show_more_api_url = "/api/v0/deals/by_category.json?" +
						"page=#{pageNumber}" + 
						"&city=#{city_selected}" + 
						"&category=#{sort_id}"

					Dealchan.Layouts.DealContainer.showMoreDeals $("#deal-container-#{event.data.cat}"), show_more_api_url, sort_id, sort_object
				
				$("#jump-to-#{sort_id}").on 'click', { cat : sort_id }, (event)->
					
					console.log "jump to cat " + event.data.cat
					
					$('html,body').animate {
						scrollTop: $("#deal-container-#{event.data.cat}").offset().top - 120
					}, 600
	