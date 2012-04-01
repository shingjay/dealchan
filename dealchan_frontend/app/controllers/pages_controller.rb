class PagesController < ApplicationController

	def index

		@container_label_1 = "Food"
		@container_label_2 = "Fitness"
		@container_label_3 = "Events"
		@container_label_4 = "Travel"
		@container_label_5 = "Shopping"

		@deal_title = "Title goes here"
		@deal_desc = "Description goes here"
		
	end

end
