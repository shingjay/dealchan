class ApplicationController < ActionController::Base
  	protect_from_forgery
  	before_filter :load_categories

  	private

  	def load_categories
  		@deal_categories = ["Food",
							"Fitness",
							"Events",
							"Travel",
							"Shopping"]
	end


end
