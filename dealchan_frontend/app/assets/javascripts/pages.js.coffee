# Place all the behaviors and hooks related to the matching controller here.
# All this logic will automatically be available in application.js.
# You can use CoffeeScript in this file: http://jashkenas.github.com/coffee-script/

jQuery ->
	if not $.cookie('selected_location')	
		Dealchan.Layouts.CityAskModal.cityAskModal("#city-selection-modal")
	
	# Trigger City Selection Modal
	$("#city-selection-modal").trigger('click')

	Dealchan.Layouts.ShowDealPopover.showDealPopover(".deal img")


	## ShowMore Button Click
	# Manual work 
	deal_categories = ["Food",
						"Fitness",
						"Events",
						"Travel",
						"Shopping"]

	# Dynamically attach event handlers to click events
	for category_id in deal_categories
		$("#showMore-#{category_id}").on 'click', { cat : category_id }, (event)->
			Dealchan.Layouts.DealContainer.showMoreDeals $("#dealContainer-#{event.data.cat}"), 'http://...'
		$("#jump-to-#{category_id}").on 'click', { cat : category_id }, (event)->
			$('html,body').animate {
				scrollTop: $("#dealContainer-#{event.data.cat}").offset().top - 120
			}, 600

	$('#submit-location').on 'click', ()->
		#console.log $('#location-dropdown').val()
		$.cookie('selected_location', $('#location-dropdown').val(),  { expires: 1200 })
		$.fancybox.close()

	for category_id in deal_categories
		$("#deals-container").append(Dealchan.Pages.DealContainer.template)






