class DealsController < ApplicationController

	respond_to :json, :xml

	def get_deals_by_price_range
		@deals = Deal.get_deal_with_price_range_and_city(params[:min_price].to_f, params[:max_price].to_d, "Klang Valley - KL", params[:page].to_i)

		

	end

end
