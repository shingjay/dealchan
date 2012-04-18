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

		if params[:page].to_i == 1
			number_of_deals = 5
		elsif params[:page].to_i >= 2
			number_of_deals = 5
		end

		@deals = Deal.get_deal_with_price_range_and_city(params[:min_price].to_f, 
															params[:max_price].to_f, 
															params[:city].to_i, 
															params[:page].to_i,
															number_of_deals
															)
	end

	def get_deals_by_category

		# If page=1, fetch 5 max
		# If page>=2, fetch 3 for now

		if params[:page].to_i == 1
			number_of_deals = 5
		elsif params[:page].to_i >= 2
			number_of_deals = 5
		end

		@deals = Deal.get_deal_with_category_and_city(params[:category].to_i, 
														params[:city].to_i, 
														params[:page].to_i,
														number_of_deals)
	end

	def get_deal_by_id
		@deal = Deal.get_deal_with_id(params[:id].to_i).first
	end

end
