class ApplicationController < ActionController::Base
  	protect_from_forgery

  	# executes load_categories method before any 'linkage' to PagesController
  	before_filter :load_categories

  	private

  	def load_categories
  		@deal_categories = {  "1" => "Activities & Events",
        "2" => "Food & Drinks",
        "3" => "Health & Beauty",
        "4" => "Shopping & Services",
        "5" => "Travel",
        "6" => "Miscellaneous"}
	end

end