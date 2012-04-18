class CitiesController < ApplicationController

	def list_cities
		@cities = City.where("name != ?", 'Travel').all
	end

end
