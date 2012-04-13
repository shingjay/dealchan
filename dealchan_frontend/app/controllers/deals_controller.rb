class DealsController < ApplicationController

	respond_to :json

	def get_deals_by_five
		@deals = Deal.get_five_deals_of(:min_price => params[:min_price], 
										:max_price => params[:max_price],
										:city => params[:city],
										:category => params[:category]
										)
	end

	def get_deals_by_price_range
		@deals = Deal.get_deal_with_price_range_and_city(params[:min_price].to_f, params[:max_price].to_f, params[:city].to_i, params[:page].to_i)
	end

	def get_deals_by_category
		@deals = Deal.get_deal_with_category_and_city(params[:category].to_i, params[:city].to_i, params[:page].to_i)
	end

end
