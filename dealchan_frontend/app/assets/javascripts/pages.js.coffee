# Place all the behaviors and hooks related to the matching controller here.
# All this logic will automatically be available in application.js.
# You can use CoffeeScript in this file: http://jashkenas.github.com/coffee-script/

jQuery ->
	#Dealchan.Layouts.CityAskModal.cityAskModal("#city-selection-modal")
	
	# Trigger City Selection Modal
	$("#city-selection-modal").trigger('click')

	Dealchan.Layouts.ShowDealPopover.showDealPopover(".deal img")


	## ShowMore Button Click
	$("#showMore-Food").click ()->
		Dealchan.Layouts.DealContainer.showMoreDeals $("#dealContainer-Food"), 'http://...'


	## JumpTo
	$("#jump-to-events").click ()->
		$('html,body').animate({
            scrollTop: $('#dealContainer-Events').offset().top - 120},
		300)