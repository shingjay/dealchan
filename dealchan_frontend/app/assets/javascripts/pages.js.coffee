# Place all the behaviors and hooks related to the matching controller here.
# All this logic will automatically be available in application.js.
# You can use CoffeeScript in this file: http://jashkenas.github.com/coffee-script/

jQuery ->
	if not $.cookie('selected_location')	
		Dealchan.Layouts.CityAskModal.cityAskModal("#city-selection-modal")
	
	# Trigger City Selection Modal
	$("#city-selection-modal").trigger('click')

	# Sidebar implementation
	$('#jump-to-shortcut a').stop().animate {
		'marginLeft':'-85px'}, 1000
	$('#jump-to-shortcut > li').hover(
		-> $('a',$(this)).stop().animate({'marginLeft':'-2px'},200)
		-> $('a',$(this)).stop().animate({'marginLeft':'-85px'},200)
	)

	# Manual work 
	deal_categories = 
		"1":"Activities & Events"
		"2":"Food & Drinks"
		"3":"Health & Beauty"
		"4":"Shopping & Services"
		"5":"Travel"
		"6":"Miscellaneous"

	#window.count = 0

	deal_price_ranges = 
		"1":
			"label": "RM0 - RM10", 
			"min_price":0, 
			"max_price":10
		"2":
			"label": "RM11 - RM50", 
			"min_price":11, 
			"max_price":50
		"3":
			"label": "RM51 - RM100", 
			"min_price":51, 
			"max_price":100
		"4":
			"label": "RM101 - RM500", 
			"min_price":101, 
			"max_price":500
		"5":
			"label": "RM501 - RM1000", 
			"min_price":501, 
			"max_price":1000
		"6":
			"label": "RM1001+", 
			"min_price":1001, 
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

	
	#console.log window.pageCount[0]

	# Dynamically attach event handlers to click events
	for category_id, category_name of deal_price_ranges
		Dealchan.Pages.DealContainer.initializeDeals $("#deals-container"), category_id, category_name, (category_id, category_name)->
			$("#show-more-button-#{category_id}").on 'click', { cat : category_id }, (event)->
				console.log "push to show more on show-more-button-#{category_id}"

				pageNumber = window.pageCount[category_id]
				window.pageCount[category_id]++

				Dealchan.Layouts.DealContainer.showMoreDeals $("#deal-container-#{event.data.cat}"), "/api/v0/deals/by_price_range.json?page=#{pageNumber}&city=3&min_price=0&max_price=120", category_id, category_name
			$("#jump-to-#{category_id}").on 'click', { cat : category_id }, (event)->
				console.log "jump to cat"
				console.log event.data.cat
				$('html,body').animate {
					scrollTop: $("#deal-container-#{event.data.cat}").offset().top - 120
				}, 600


	#for category_id, category_name of deal_categories
	#	Dealchan.Pages.DealContainer.initializeDeals $("#deals-container"), category_id, category_name, (category_id, category_name)->
	#		$("#show-more-button-#{category_id}").on 'click', { cat : category_id }, (event)->
	#			console.log "push to show more on show-more-button-#{category_id}"
#
	#			pageNumber = window.pageCount[category_id]
	#			window.pageCount[category_id]++
#
	#			Dealchan.Layouts.DealContainer.showMoreDeals $("#deal-container-#{event.data.cat}"), "/api/v0/deals/by_category.json?page=#{pageNumber}&city=3&category=#{event.data.cat}", category_id, category_name
	#		$("#jump-to-#{category_id}").on 'click', { cat : category_id }, (event)->
	#			console.log "jump to cat"
	#			console.log event.data.cat
	#			$('html,body').animate {
	#				scrollTop: $("#deal-container-#{event.data.cat}").offset().top - 120
	#			}, 600
	
	$('#submit-location').on 'click', ()->
		#console.log $('#location-dropdown').val()
		$.cookie('selected_location', $('#location-dropdown').val(),  { expires: 1200 })
		$.fancybox.close()