class ApplicationController < ActionController::Base
  	protect_from_forgery

  	# executes load_categories method before any 'linkage' to PagesController
  	before_filter :load_categories

  	private

  	def load_categories
  		@deal_categories = ["Food & Drinks",
							"Health & Beauty",
							"Activities & Events",
							"Shopping & Services",
              "Travel",
              "Miscellaneous"
            ]
	end

end
