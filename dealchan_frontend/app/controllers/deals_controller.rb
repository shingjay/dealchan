class DealsController < ApplicationController

	respond_to :json

	def get_deals_by_price_range
		@deals = Deal.get_deal_with_price_range_and_city(params[:min_price].to_f, params[:max_price].to_f, params[:city], params[:page].to_i)
	end

	def get_deals_by_category
		@deals = Deal.get_deal_with_category_and_city(params[:category], params[:city], params[:page].to_i)
	end

end
